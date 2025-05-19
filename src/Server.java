import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private Parrucchiere gino = new Parrucchiere();

    void run(){
        try{
            ServerSocket serverSocket = new ServerSocket(9999, 10);
            while(true){
                ServerThread thread = new ServerThread();
                thread.start(serverSocket.accept(), gino);
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public static void main(String[] args) {
        Server s = new Server ();
        s.run();
    }
}
