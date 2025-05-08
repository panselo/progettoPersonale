import java.net.Socket;
import java.io.*;

public class ServerThread implements Runnable{

    Socket clientConnection = null;
    private PrintWriter out;
    private BufferedReader in;
    private Parrucchiere parrucchiere;


    @Override
    public void run() {
        try {
            System.out.println("server<" + in.readLine());
            Messaggio msg = mapper.readValue(in.readLine(), Messaggio.class);
            String req = msg.getReq();
            switch(req) {
                case "getAppointmentsList":
                    sendMessage(mapper.writeValueAsString(parrucchiere.getListaAppuntamenti()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start(Socket socket) {

        try {
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
            System.out.println("client>" + request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
