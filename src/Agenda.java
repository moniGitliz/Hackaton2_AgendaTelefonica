import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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


    //------------- MÉTODO 9  ELIMINAR CONTACTO ------------- //
    public boolean eliminarContacto(String nombreBuscado, String apellidoBuscado) {
        // 1) Recorrer la lista
        for (int i = 0; i < contacto.size(); i++) {
            Contacto c = contacto.get(i);
            // 2) ¿Coincide nombre y apellido? (ignorando mayúsculas)
            if (c.getNombre().equalsIgnoreCase(nombreBuscado) && c.getApellido().equalsIgnoreCase(apellidoBuscado)) {
                // 3) Eliminar y salir
                contacto.remove(i);
                return true; // eliminado con éxito
            }
        }
        return false; // 4) No se encontró
    }

    public void eliminarContactoInteractivo(Scanner sc) {
        // 1) Pedir al usuario la informacion del contacto que desea eliminar
        System.out.print("\nPor favor ingrese el  nombre a eliminar: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Apellido: ");
        String apellido = sc.nextLine().trim();

        // 2) Validar que nombre y apellido no queden vacíos
        if (nombre.isEmpty() || apellido.isEmpty()) {
            System.out.println("⚠️  Nombre y apellido no pueden estar vacíos.\n");
            return;
        }

        // 3) Llamamos al método eliminarContacto y mostramos resultado
        boolean eliminado = eliminarContacto(nombre, apellido);
        if (eliminado) {
            System.out.println("✅ Contacto eliminado con éxito.\n");
        } else {
            System.out.println("⚠️  No se encontró el contacto.\n");
        }
    }

    //------------- FIN MÉTODO 9 ------------- //






}


