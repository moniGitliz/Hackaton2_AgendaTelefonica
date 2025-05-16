import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private List<Contacto> contacto = new ArrayList<>();


    //------------- CONSTRUCTORES------------- //
    public Agenda() {
    }

    public Agenda(ArrayList<Contacto> contacto) {
        this.contacto = contacto;
    }

    //------------- GETTERS & SETTERS------------- //

    public List<Contacto> getContacto() {
        return contacto;
    }

    public void setContacto(List<Contacto> contacto) {
        this.contacto = contacto;
    }

}


