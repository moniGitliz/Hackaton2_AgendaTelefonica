package agenda.model;

import java.util.Scanner;

public class Main {

    // ───────────────────────── Colores ANSI ─────────────────────────
    private static final boolean COLORES = true;      // pon false si tu consola no soporta ANSI
    private static final String RESET  = COLORES ? "\u001B[0m"  : "";
    private static final String CYAN   = COLORES ? "\u001B[36m" : "";
    private static final String GREEN  = COLORES ? "\u001B[32m" : "";
    private static final String YELLOW = COLORES ? "\u001B[33m" : "";
    private static final String RED    = COLORES ? "\u001B[31m" : "";
    private static final String BLUE   = COLORES ? "\u001B[34m" : "";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Agenda agenda   = new Agenda();

        while (true) {
            // ───── Menú principal ─────
            System.out.println(BLUE + "┌──────────────────────────────────────────┐" + RESET);
            System.out.println(BLUE +   CYAN + "     📒BIENVENIDO A SU AGENDA TELÉFONICA" + BLUE   + RESET);
            System.out.println(BLUE + "└──────────────────────────────────────────┘" + RESET);

            System.out.println(CYAN + "1. ➕ Añadir contacto");
            System.out.println("2. 🔎 Ver si existe un contacto");
            System.out.println("3. 📜 Listar contactos");
            System.out.println("4. 🧭 Buscar contacto (muestra teléfono)");
            System.out.println("5. ❌ Eliminar contacto");
            System.out.println("6. ✏️  Modificar teléfono");
            System.out.println("7. 📦 Ver si la agenda está llena");
            System.out.println("8. 📂 Mostrar espacios libres");
            System.out.println("9. 🚪 Salir" + RESET);
            System.out.print(YELLOW + "\nSelecciona una opción (1-9): " + RESET);

            int opcion;
            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException ex) {
                System.out.println(RED + "❗ Número inválido. Intenta de nuevo.\n" + RESET);
                continue;
            }

            String nombre, apellido;
            int telefono;

            switch (opcion) {
                case 1 -> { // ➕ Añadir
                    System.out.print("Nombre: ");
                    nombre = scanner.nextLine().trim();
                    System.out.print("Apellido: ");
                    apellido = scanner.nextLine().trim();
                    System.out.print("Teléfono (solo números): ");
                    try {
                        telefono = Integer.parseInt(scanner.nextLine().trim());
                        agenda.anadirContacto(new Contacto(nombre, apellido, telefono));
                    } catch (NumberFormatException ex) {
                        System.out.println(RED + "❗ El teléfono debe ser numérico.\n" + RESET);
                    }
                }
                case 2 -> { // 🔎 Existe
                    System.out.print("Nombre: ");   nombre = scanner.nextLine().trim();
                    System.out.print("Apellido: "); apellido = scanner.nextLine().trim();
                    boolean existe = agenda.existeContacto(new Contacto(nombre, apellido, 0));
                    System.out.println(existe ? GREEN + "✅ ¡El contacto SÍ existe!\n"
                            : RED   + "⚠️  No se encontró ese contacto.\n" + RESET);
                }
                case 3 -> { // 📜 Listar
                    System.out.println("\n" + CYAN + "📋 Tus contactos:");
                    agenda.listarContactos();
                    System.out.println();
                }
                case 4 -> { // 🧭 Buscar
                    System.out.print("Nombre: ");   nombre = scanner.nextLine().trim();
                    System.out.print("Apellido: "); apellido = scanner.nextLine().trim();
                    agenda.buscaContacto(nombre, apellido);
                    System.out.println();
                }
                case 5 -> { // ❌ Eliminar
                    System.out.print("Nombre: ");   nombre = scanner.nextLine().trim();
                    System.out.print("Apellido: "); apellido = scanner.nextLine().trim();
                    boolean ok = agenda.eliminarContacto(nombre, apellido);
                    System.out.println(ok ? GREEN + "🗑️  Contacto eliminado.\n"
                            : RED   + "⚠️  No se encontró ese contacto.\n" + RESET);
                }
                case 6 -> { // ✏️ Modificar
                    System.out.print("Nombre: ");   nombre = scanner.nextLine().trim();
                    System.out.print("Apellido: "); apellido = scanner.nextLine().trim();
                    System.out.print("Nuevo teléfono: ");
                    try {
                        telefono = Integer.parseInt(scanner.nextLine().trim());
                        agenda.modificarTelefono(nombre, apellido, telefono);
                    } catch (NumberFormatException ex) {
                        System.out.println(RED + "❗ El teléfono debe ser numérico.\n" + RESET);
                    }
                }
                case 7 -> { // 📦 Agenda llena
                    System.out.println(agenda.agendaLlena()
                            ? RED + "🚫 La agenda está llena.\n"
                            : GREEN + "✅ Aún tienes espacio disponible.\n" + RESET);
                }
                case 8 -> { // 📂 Espacios libres
                    agenda.espacioLibres();
                    System.out.println();
                }
                case 9 -> { // 🚪 Salir
                    System.out.println(GREEN + "👋 ¡Hasta luego! Tus datos se conservarán en memoria mientras la app esté activa." + RESET);
                    scanner.close();
                    return;
                }
                default -> System.out.println(RED + "❗ Opción inválida. Elige un número de 1 a 9.\n" + RESET);
            }
        }
    }
}