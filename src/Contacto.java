public class Contacto {
    private String nombre;
    private String apellido;
<<<<<<< HEAD
    private int numero;

=======
    private int telefono;


    //------------- CONSTRUCTORES------------- //


    public Contacto() {
    }

    public Contacto(String nombre, String apellido, int telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    //------------- GETTERS & SETTERS------------- //

    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
>>>>>>> main
}
