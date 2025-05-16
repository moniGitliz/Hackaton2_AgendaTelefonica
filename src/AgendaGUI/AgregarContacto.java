package AgendaGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgregarContacto {
    private JPanel ventanaDeAgregar;
    private JButton agregarUsuarioButton;
    private JTextField nombreInput;
    private JTextField apellidoInput;
    private JTextField telefonoInput;
    private JLabel textoTituloAgregar;
    private JLabel textoNombre;
    private JLabel textoApellido;
    private JLabel textoTelefono;

    public AgregarContacto() {
        agregarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreUsuario = nombreInput.getText();
                String apellidoUsuario = apellidoInput.getText();
                String mensaje = "Bienvenido " + nombreUsuario + " " + apellidoUsuario;
                JOptionPane.showMessageDialog(null, mensaje);

            }
        });
    }

    public JPanel getVentanaDeAgregar(){
        return ventanaDeAgregar;
    }

}
