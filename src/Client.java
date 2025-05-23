import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Client {
    private PrintWriter out;
    private BufferedReader in;
    private String name;
    private ArrayList<SlotAppuntamento> listaAppuntamenti = new ArrayList<>();
    ObjectMapper mapper = new ObjectMapper();
    Scanner scanner = new Scanner(System.in);

    void run(String name) {
        try{
            Socket clientSocket = new Socket("localhost", 9999);
            this.out = new PrintWriter(clientSocket.getOutputStream());
            this.out.flush();
            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.name = name;

            int sceltaOpzione;
            do {
                System.out.print(
                        "\nBENVENUTO IN PANSE BARBER SHOP" +
                        "\nScegli un'opzione:" +
                                "\n1) Lista appuntamenti" +
                                "\n2) Prenota appuntamento" +
                                "\n3) Concludi prenotazione" +
                                "\n> "
                );
                sceltaOpzione = Integer.parseInt(scanner.next());
                System.out.println();
                switch(sceltaOpzione) {
                    case 1:
                        sendMessage(mapper.writeValueAsString(new Messaggio(true, "GET", "getAppointmentsList", new HashMap<>())));
                        this.listaAppuntamenti = Parrucchiere.toListaAppuntamenti(mapper.readValue(in.readLine(), Messaggio.class).getResp());
                        for(int i = 0; i < this.listaAppuntamenti.size(); i++){
                            System.out.println(Integer.toString(i+1) + ") " + this.listaAppuntamenti.get(i));
                        }
                        break;
                    case 2:
                        sendMessage(mapper.writeValueAsString(new Messaggio(true, "GET", "getAppointmentsList", new HashMap<>())));
                        this.listaAppuntamenti = Parrucchiere.toListaAppuntamenti(mapper.readValue(in.readLine(), Messaggio.class).getResp());

                        int sceltaAppuntamento;
                        do{
                            System.out.println("\nScegli un appuntamento:");
                            for(int i = 0; i < this.listaAppuntamenti.size(); i++){
                                System.out.println(Integer.toString(i+1) + ") " + this.listaAppuntamenti.get(i));
                            }
                            System.out.print("\n> ");
                            sceltaAppuntamento = Integer.parseInt(scanner.next());
                            System.out.println();
                            if(sceltaAppuntamento < 1 || sceltaAppuntamento > this.listaAppuntamenti.size()){
                                System.err.println("[ERRORE] Appuntamento non valido, riprova.");
                            }
                        }while(sceltaAppuntamento < 1 || sceltaAppuntamento > this.listaAppuntamenti.size());

                        HashMap<String, String> params = new HashMap<>();
                        params.put("slot", this.listaAppuntamenti.get(sceltaAppuntamento - 1).toString());
                        params.put("name", this.name);

                        sendMessage(mapper.writeValueAsString(new Messaggio(true, "PUT", "setBook", params)));
                        System.out.println(mapper.readValue(in.readLine(), Messaggio.class).getResp());
                        break;
                    case 3:
                        System.out.println("Client > Chiusura ordine");
                        sendMessage(mapper.writeValueAsString(new Messaggio(true, "PUT", "confirmBook", new HashMap<>())));
                        break;
                    default:
                        System.err.println("[ERRORE] Opzione non valida, riprova.");
                }
                System.out.println();
            } while (sceltaOpzione != 3);



        } catch (UnknownHostException e) {
            System.out.println("Connessione non riuscita.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    void sendMessage(String request){
        try{
            this.out.println(request);
            this.out.flush();
            System.out.println("client>" + request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
//        for(int i = 0; i<1; i++){
//            Client c = new Client();
//            c.run("client-" + Integer.toString(i));
//        }
        Random rand = new Random();
        Client c = new Client();
        c.run("client-" + Integer.toString(rand.nextInt(100)));
    }
}