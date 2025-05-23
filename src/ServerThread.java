import java.net.Socket;
import java.io.*;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
public class ServerThread implements Runnable{

    Socket clientConnection = null;
    private PrintWriter out;
    private BufferedReader in;
    Parrucchiere parrucchiere;
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public void run() {
        boolean exit = false;
        do{
            try {

                String input = in.readLine();
                System.out.println("server<" + input);
                Messaggio msg = mapper.readValue(input, Messaggio.class);
                String req = msg.getReq();

                //System.out.println("req: " + req);
                Messaggio resp;
                switch(req) {
                    case "getAppointmentsList":
                        resp = new Messaggio(false, null, parrucchiere.toString(), new HashMap<>());
                        sendMessage(mapper.writeValueAsString(resp));
                        break;
                    case "setBook":
                        boolean esito = parrucchiere.prenota(SlotAppuntamento.toSlotAppuntamento(msg.getParam().get("slot")), msg.getParam().get("name"));
                        if(esito){
                            resp = new Messaggio(false, null, "Booking successfull" , new HashMap<>());
                        }else{
                            resp = new Messaggio(false, null, "Booking failed", new HashMap<>());
                        }
                        sendMessage(mapper.writeValueAsString(resp));
                        break;
                    case "confirmBook":
                        sendMessage(mapper.writeValueAsString(new Messaggio(false, null, "Confirmed." , new HashMap<>())));
                        exit = true;
                        break;
                    default:
                        System.err.println("[ERRORE] Richiesta non valida.");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }while(!exit);
    }

    public void start(Socket socket, Parrucchiere parrucchiere) {

        try {
            this.parrucchiere = parrucchiere;
            this.clientConnection = socket;
            this.out = new PrintWriter(clientConnection.getOutputStream());
            this.out.flush();
            this.in = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));

            this.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    void sendMessage(String request){
        try{
            this.out.println(request);
            this.out.flush();
            System.out.println("server>" + request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
