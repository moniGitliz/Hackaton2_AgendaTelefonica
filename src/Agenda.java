import java.util.ArrayList;
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

    //Prueba
    public boolean existeContacto(Contacto c){
        return false;
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
    }
}


