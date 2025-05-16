import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Agenda {
    private List<Contacto> contacto = new ArrayList<>();
    private int maximo;

    //------------- CONSTRUCTORES------------- //
    public Agenda() {
        this.maximo = 10;
    }

    public Agenda(int maximo){
        this.maximo = maximo;
    }

    public Agenda(ArrayList<Contacto> contacto, int maximo) {
        this.contacto = contacto;
        this.maximo = maximo;
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


    //------------- METODOS ------------- //

  
    public boolean existeContacto(Contacto c) {
        return contacto.contains(c);
    }
    public boolean agendaLlena() {
        return contacto.size() >= maximo;
    }

    public void añadirContacto(Contacto c){
        if (Objects.equals(c.getNombre(), "") || c.getNombre() == null || Objects.equals(c.getApellido(), "") || c.getApellido() == null){
            System.out.println("No se puede agregar. El nombre o apellido del contacto no está definido.");
        } else {
            if (contacto.size() < getMaximo()){
                if (existeContacto(c)){
                    System.out.println("No se puede añadir. El contacto ya existe.");
                } else {
                    contacto.add(c);
                    System.out.println("Contacto añadido.");
                }
            } else {
                System.out.println("Tu lista de contactos está llena.");
            }
        }
    }

    public void modificarTelefono(String nombre, String apellido, String nuevoTelefono){
        boolean contactoEncontrado = false;
        for (Contacto contactoModificar : contacto){
            if(contactoModificar.getNombre().equalsIgnoreCase(nombre) && contactoModificar.getApellido().equalsIgnoreCase(apellido)){
                contactoModificar.setTelefono(Integer.parseInt(nuevoTelefono));
                System.out.println("Número modificado.");
                contactoEncontrado = true;
            }
        }
        if (!contactoEncontrado){
            System.out.println("Contacto no encontrado.");
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


