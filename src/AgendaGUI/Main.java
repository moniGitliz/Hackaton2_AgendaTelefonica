package AgendaGUI;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        agenda.getContacto().add(new Contacto("Carlos", "Perez", 123456789));
        agenda.getContacto().add(new Contacto("Ana", "Gomez", 987654321));
        agenda.getContacto().add(new Contacto("Bruno", "Lopez", 456789123));
        

        JFrame frame=new JFrame("AgendaGUI.Agenda Telefónica");
        //instancio interfaz
        nuevo gui= new nuevo(agenda);
        //agrego mi panel al frame
         frame.setContentPane(gui.getPanel());
        //configuro estilos

        frame.setSize(500,500); // Ajusta el tamaño al contenido
        frame.setVisible(true);
    }
}