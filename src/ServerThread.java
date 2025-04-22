import java.net.Socket;
import java.io.*;

public class ServerThread implements Runnable{

    Socket clientConnection = null;
    private PrintWriter out;
    private BufferedReader in;


    @Override
    public void run() {
        try {
            System.out.println("server<" + in.readLine());
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
}
