package agenda.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Agenda {
    private List<Contacto> contacto = new ArrayList<>();
    private int maximo;

    //------------- CONSTRUCTORES------------- //
    public Agenda() {
        this.maximo = 10;
    }

    public Agenda(int maximo) {
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

    public void anadirContacto(Contacto c) {
        if (c.getNombre() == null || c.getNombre().isBlank() || c.getApellido() == null || c.getApellido().isBlank()) {
            System.out.println("No se puede agregar. El nombre o apellido del contacto no está definido.");
        } else if (agendaLlena()) {
            System.out.println("Tu lista de contactos está llena.");
        } else if (existeContacto(c)) {
            System.out.println("No se puede añadir. El contacto ya existe.");
        } else {
            contacto.add(c);
            System.out.println("Contacto añadido.");
        }
    }

    public void modificarTelefono(String nombre, String apellido, int nuevoTelefono) {
        for (Contacto c : contacto) {
            if (c.getNombre().equalsIgnoreCase(nombre) && c.getApellido().equalsIgnoreCase(apellido)) {
                c.setTelefono(nuevoTelefono);
                System.out.println("Número modificado.");
                return;
            }
        }
        System.out.println("Contacto no encontrado.");
    }


    public void buscaContacto(String nombre, String apellido) {
        for (Contacto c : contacto) {
            if (c.getNombre().equalsIgnoreCase(nombre) && c.getApellido().equalsIgnoreCase(apellido)) {
                System.out.println("Teléfono: " + c.getTelefono());
                return;
            }
        }
        System.out.println("Contacto no encontrado");
    }

    public void espacioLibres() {
        int libres = maximo - contacto.size();
        System.out.println("quedan" + " " + libres + " " + "espacios libres");

    }

    public void listarContactos() {
        if (contacto.size() > 0) {
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
        } else {
            System.out.println("No has agregado ningun contacto.");
        }

    }


    public boolean eliminarContacto(String nombre, String apellido) {
        // 1) Recorrer la lista
        for (int i = 0; i < contacto.size(); i++) {
            Contacto c = contacto.get(i);
            // 2) ¿Coincide nombre y apellido? (ignorando mayúsculas)
            if (c.getNombre().equalsIgnoreCase(nombre) && c.getApellido().equalsIgnoreCase(apellido)) {
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
            System.out.println("✅ agenda.model.Contacto eliminado con éxito.\n");
        } else {
            System.out.println("⚠️  No se encontró el contacto.\n");
        }
    }
}


