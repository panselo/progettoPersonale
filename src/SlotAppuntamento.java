import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
}
