import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class SlotAppuntamento{
    private static final String DatetimePattern = "yyyy-MM-dd HH:mm";

    private LocalDateTime inizio;
    private LocalDateTime fine;
    private String nomePrenotazione;

    public SlotAppuntamento(String inizio, String fine, String nomePrenotazione) {
        this.inizio = LocalDateTime.parse(inizio, DateTimeFormatter.ofPattern(DatetimePattern));
        this.fine = LocalDateTime.parse(fine, DateTimeFormatter.ofPattern(DatetimePattern));
        this.nomePrenotazione = nomePrenotazione;
    }

    public boolean Libero(){
        return this.nomePrenotazione == null;
    }

    public void Prenota(String nomePrenotazione){
        this.nomePrenotazione = nomePrenotazione;
    }

    @Override
    public String toString(){
        return this.inizio.toString().replaceFirst("T", " ") + ","
                + this.fine.toString().replaceFirst("T", " ") + ","
                + this.nomePrenotazione;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SlotAppuntamento)) return false;
        SlotAppuntamento that = (SlotAppuntamento) o;
        return Objects.equals(inizio, that.inizio) && Objects.equals(fine, that.fine);
    }

    public static SlotAppuntamento toSlotAppuntamento(String str){
        String[] splitStr = str.split(",");
        return new SlotAppuntamento(splitStr[0], splitStr[1], splitStr[2]);
    }
}
