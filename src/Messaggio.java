import java.util.HashMap;

public class Messaggio {
    private String type;
    private String req;
    private String resp;
    private HashMap<String, String> param;

    public Messaggio() {
        this.type = null;
        this.req = null;
        this.resp = null;
        this.param = new HashMap<String, String>();
    }
    public Messaggio(boolean isRequest, String type, String msg, HashMap<String, String> param) {
        if(isRequest){
            this.type = type;
            this.req = msg;
            this.resp = null;
        }else{
            this.type = null;
            this.req = null;
            this.resp = msg;
        }
        this.param = new HashMap<String, String>(param);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReq() {
        return req;
    }

    public void setReq(String req) {
        this.req = req;
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    public HashMap<String, String> getParam() {
        return param;
    }

    public void setParam(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "Messaggio{" +
                "\n  type='" + type + '\'' +
                ",\n  req='" + req + '\'' +
                ",\n  resp='" + resp + '\'' +
                ",\n  param=" + param +
                '}';
    }
}
