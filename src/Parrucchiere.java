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

    //Non usato perche usato il toString in quanto ci serviva una corretta formattazione
    public synchronized ArrayList<SlotAppuntamento> getListaAppuntamenti(){
        ArrayList<SlotAppuntamento> slotLiberi = new ArrayList<SlotAppuntamento>();
        for (SlotAppuntamento slot : this.slotAppuntamenti){
            if (slot.Libero()){
                System.out.println("DEBUG: " +
                        "\n  slot = " + slot +
                        "\n  slotsStatus = " + slot.Libero()
                );
                slotLiberi.add(slot);
            }
        }
        return slotLiberi;
    }

    public synchronized boolean prenota(SlotAppuntamento slot, String nome){
        System.out.println("DEBUG: " +
                "\n  slot = " + slot +
                "\n  name = " + nome
        );
        int indexSlot = this.slotAppuntamenti.indexOf(slot);
        System.out.println("DEBUG:" +
                "\n  slots = " + this.slotAppuntamenti.toString() +
                "\n  indexSlot = " + indexSlot
        );
        if (indexSlot != -1 && this.slotAppuntamenti.get(indexSlot).Libero()) {
            System.out.println("DEBUG:" +
                    "\n  slotsStatus = LIBERO"
            );
            SlotAppuntamento gettedSlot = this.slotAppuntamenti.get(indexSlot);
            gettedSlot.Prenota(nome);
            this.slotAppuntamenti.set(indexSlot, gettedSlot);
            System.out.println("DEBUG:" +
                    "\n  slotsStatus = " + this.slotAppuntamenti.get(indexSlot).Libero() +
                    "\n  edited = " + gettedSlot
            );
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        String str = "";
        for(SlotAppuntamento slot: this.slotAppuntamenti){
            str += slot.toString() + ";";
        }
        return str;
    }

    public String freeToString(){
        String str = "";
        for(SlotAppuntamento slot: this.getListaAppuntamenti()){
            str += slot.toString() + ";";
        }
        return str;
    }

    public static ArrayList<SlotAppuntamento> toListaAppuntamenti(String str){
        ArrayList<SlotAppuntamento> slot = new ArrayList<SlotAppuntamento>();
        for(String subStr: str.split(";")){
            slot.add(SlotAppuntamento.toSlotAppuntamento(subStr));
        }
        return slot;
    }

    //modifica


}

