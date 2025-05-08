import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Parrucchiere {
    private ArrayList<SlotAppuntamento> slotAppuntamenti = new ArrayList<SlotAppuntamento>();

    public Parrucchiere() {
        this.slotAppuntamenti.add(new SlotAppuntamento("2025-04-28 08:00", "2025-04-28 08:30",null));
        this.slotAppuntamenti.add(new SlotAppuntamento("2025-04-28 08:30", "2025-04-28 09:00",null));
        this.slotAppuntamenti.add(new SlotAppuntamento("2025-04-28 09:00", "2025-04-28 09:30",null));
        this.slotAppuntamenti.add(new SlotAppuntamento("2025-04-28 09:30", "2025-04-28 10:00",null));
        this.slotAppuntamenti.add(new SlotAppuntamento("2025-04-28 10:00", "2025-04-28 10:30",null));
        this.slotAppuntamenti.add(new SlotAppuntamento("2025-04-28 10:30", "2025-04-28 11:00",null));
    }

    public synchronized ArrayList<SlotAppuntamento> getListaAppuntamenti(){
        ArrayList<SlotAppuntamento> slotLiberi = new ArrayList<SlotAppuntamento>();
        for (SlotAppuntamento slot : this.slotAppuntamenti){
            if (slot.Libero()){
                slotLiberi.add(slot);
            }
        }
        return slotLiberi;
    }

    public synchronized boolean prenota(SlotAppuntamento slot, String nome){
        int indexSlot = this.slotAppuntamenti.indexOf(slot);
        if (indexSlot != -1 && this.slotAppuntamenti.get(indexSlot).Libero()) {
            this.slotAppuntamenti.get(indexSlot).Prenota(nome);
            return true;
        }
        return false;
    }

    //modifica


}

