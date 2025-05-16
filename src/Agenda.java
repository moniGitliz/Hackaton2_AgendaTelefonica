import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private List<Contacto> contacto = new ArrayList<>();
    private int maximo;


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

    public int getMaximo() {
        return maximo;
    }

    public void setMaximo(int maximo) {
        this.maximo = maximo;
    }


    public void buscaContacto(String nombreCompleto) {
        for (Contacto contacto : contacto) {
            String nombreYApellido = contacto.getNombre() + " " + contacto.getApellido();
            if (nombreYApellido.equalsIgnoreCase(nombreCompleto.trim())) {
                System.out.println("Teléfono: " + contacto.getTelefono());
                return;
            }
        }
        System.out.println("Contacto, no encontrado");
    }

    public void espacioLibres(){
        int libres = maximo - contacto.size();
        System.out.println("quedan" + " " + libres + " " + "espacios libres");
    }
}


