# Agenda Telefónica 📒

Pequeña aplicación de escritorio, escrita en **Java 17** (código 100 % Swing puro),
que permite gestionar una agenda de contactos:

| Función | Detalles |
|---------|----------|
| ➕ **Añadir** | Nombre, Apellido y Teléfono (numérico). Tope de contactos configurable (por defecto 10). |
| 🔍 **Buscar** | Resalta todas las filas cuyo **nombre o apellido** contengan la cadena escrita. |
| ✏ **Modificar** | Permite actualizar el teléfono de la fila seleccionada. |
| ❌ **Eliminar** | Borra la fila seleccionada (confirmación visual). |
| ↩ **Enter = Añadir** | El botón **Añadir** es el botón por defecto del `JFrame`. |


Tabla ordenable (click en cabecera) + scroll automático.  
Toda la lógica de negocio está aislada en `agenda.model` y se puede usar
desde consola (`MenuCLI`), tests o GUI.

---


## Requisitos

| Herramienta | Versión |
|-------------|---------|
| JDK         | 17 o superior |
| Maven \*    | 3.8+ _(opcional, sólo si deseas empaquetar con `mvn package`)_ |

---

## Estructura del proyecto
agenda/
├─ model/
│  └─Contacto, Agenda (lógica)
├─ ui/ 
    └─ purecode/ ← AgendaGUI, ContactoTableModel (Swing)


---

## Compilación y ejecución rápidas (sin Maven)

```bash
# 1. Compilar
javac -d out $(git ls-files | grep '\.java$')

# 2. Ejecutar
java -cp out agenda.ui.purecode.AgendaGUI
