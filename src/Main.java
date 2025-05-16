import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Agenda agenda = new Agenda();
        int opcion;

        while (true) {
            System.out.println("1.  Añadir contacto");
            System.out.println("2. Ver si existe un contacto");
            System.out.println("3. Listar contactos");
            System.out.println("4. Buscar contacto");
            System.out.println("5. Eliminar contacto");
            System.out.println("6. Modificar teléfono");
            System.out.println("7. Ver si la agenda está llena");
            System.out.println("8. Ver espacios libres");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese el apellido: ");
                    String apellido = scanner.nextLine();
                    System.out.print("Ingrese el numero de teléfono: ");
                    int telefono = scanner.nextInt();
                    try {
                        Contacto nuevo = new Contacto(nombre, apellido, telefono);
                        agenda.añadirContacto(nuevo);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Ingrese el nombre: ");
                    nombre = scanner.nextLine();
                    System.out.print("Ingrese el apellido: ");
                    apellido = scanner.nextLine();
                    Contacto buscar = new Contacto(nombre, apellido, 0);
                    System.out.println(agenda.existeContacto(buscar) ? "Existe." : "No existe.");
                    break;
                case 3:
                    agenda.listarContactos();
                    break;
                case 4:
                    System.out.print(" Ingrese el nombre: ");
                    nombre = scanner.nextLine();
                    System.out.print("Ingrese el apellido: ");
                    apellido = scanner.nextLine();
                    agenda.buscaContacto(nombre, apellido);
                    break;
                case 5:
                    System.out.print(" Ingrese el nombre: ");
                    nombre = scanner.nextLine();
                    System.out.print("Ingrese el apellido: ");
                    apellido = scanner.nextLine();
                    agenda.eliminarContacto(new Contacto(nombre, apellido, 0));
                    break;
                case 6:
                    System.out.print("Ingrese el nombre: ");
                    nombre = scanner.nextLine();
                    System.out.print("Ingrese el apellido: ");
                    apellido = scanner.nextLine();
                    System.out.print("Ingrese el nuevo teléfono: ");
                    telefono = scanner.nextInt();
                    agenda.modificarTelefono(nombre, apellido, telefono);
                    break;
                case 7:
                    System.out.println(agenda.agendaLlena() ? "La agenda está llena." : "Aún hay espacio.");
                    break;
                case 8:
                    System.out.println("Espacios libres: " + agenda.espacioLibre());
                    break;
                case 9:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }

    }
}
