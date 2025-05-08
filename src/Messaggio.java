import java.util.HashMap;

public class Messaggio {
    private String type;
    private String req;
    private HashMap<String, String> param;

    public Messaggio() {
        this.type = null;
        this.req = null;
        this.param = new HashMap<String, String>();
    }
    public Messaggio(String msg) {
        this.type = "GET";
        this.req = msg;
        this.param = new HashMap<String, String>();
    }

    //tutti i get e set dei vari attributi
}
