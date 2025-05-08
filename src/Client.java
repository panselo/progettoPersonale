import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.PrintWriter;
//import com.fasterxml.jackson.databind.ObjectMapper;

public class Client {
    private PrintWriter out;
    private BufferedReader in;
    private String name;
    //ObjectMapper mapper = new ObjectMapper();

    void run(String name) {
        try{
            Socket clientSocket = new Socket("localhost", 9999);
            this.out = new PrintWriter(clientSocket.getOutputStream());
            this.out.flush();
            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.name = name;

            sendMessage("{\n"
                    + "\"type\": \"GET\",\n"
                    + "\"req\": \"getAppointmentsList\"\n"
                    + "}"
            );
            //sendMessage(mapper.writeValueAsString(new Messaggio("getAppointmentsList")));

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
        for(int i = 0; i<5; i++){
            Client c = new Client();
            c.run("client-" + Integer.toString(i));
        }
    }
}