import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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


    public void listarContactos() {
        for (int i = 0; i < contacto.size() - 1; i++) {
            for (int j = 0; j < contacto.size() - i - 1; j++) {
                String nombre1 = contacto.get(j).getNombre();
                String nombre2 = contacto.get(j + 1).getNombre();

                if (nombre1.compareToIgnoreCase(nombre2) > 0) {
                    Contacto reasig_nom = contacto.get(j);
                    contacto.set(j, contacto.get(j + 1));
                    contacto.set(j + 1, reasig_nom);
                }
            }
        }
        // Imprimir contactos ordenados
        for (Contacto c : contacto) {
            System.out.println(c.getNombre() + " " + c.getApellido() + " - " + c.getTelefono());
        }
    }

}


