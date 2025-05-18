package AgendaGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class nuevo {
    private JButton boton;
    private JTextArea agregar;
    private JPanel Panel;
    private Agenda agenda;

    public nuevo(Agenda agenda) {
        this.agenda = agenda;
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregar.append(agenda.listarContactos());
            }
        });
    }
    public JPanel getPanel() {
        return Panel;
    }
}
