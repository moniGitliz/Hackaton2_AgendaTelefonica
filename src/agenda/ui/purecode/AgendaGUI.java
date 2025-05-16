package agenda.ui.purecode;

import agenda.model.Agenda;
import agenda.model.Contacto;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

/**
 * GUI Swing “puro código” para la Agenda Telefónica.
 *
 *  • CRUD completo (Añadir, Buscar, Modificar, Eliminar)
 *  • Búsqueda por nombre o apellido: resalta TODAS las coincidencias en la tabla
 */
public class AgendaGUI extends JFrame {

    /* ---------- MODELO ---------- */
    private final Agenda agenda;              // se inyecta en el constructor
    private ContactoTableModel modeloTabla;   // puente Lista ↔ JTable

    /* ---------- VISTA ---------- */
    private JTable      tabla;
    private JTextField  txtNombre;
    private JTextField  txtApellido;
    private JTextField  txtTelefono;
    private JTextField  txtBuscar;
    private JButton     btnAñadir;
    private JButton     btnModificar;
    private JButton     btnEliminar;
    private JButton     btnBuscar;

    /* ---------- CONSTRUCTOR ---------- */
    public AgendaGUI(Agenda agenda) {
        super("Agenda Telefónica");
        this.agenda = agenda;
        Image icono = Toolkit.getDefaultToolkit()
                .getImage(getClass().getResource("/agenda/ui/purecode/iconoAgenda.png"));;
        setIconImage(icono);
        initComponents();
    }


    /* ---------- CONFIGURA LA INTERFAZ ---------- */
    private void initComponents() {
        setLayout(new BorderLayout());

        /* === 1) TABLA EN EL CENTRO === */
        modeloTabla = new ContactoTableModel(agenda.getContacto());
        tabla       = new JTable(modeloTabla);
        tabla.setRowHeight(22);
        tabla.setAutoCreateRowSorter(true);          // clic en cabecera ordena
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) tabla.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        JTableHeader header = tabla.getTableHeader();
        Font  boldHeader    = header.getFont().deriveFont(Font.BOLD);
        header.setFont(boldHeader);

        /* === 2) PANEL INFERIOR: CAMPOS + BÚSQUEDA + BOTONES === */
        JPanel panelSur = new JPanel(new BorderLayout());

        // 2a. Campos de alta / modificación
        JPanel panelCampos = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtNombre   = new JTextField(8);
        txtApellido = new JTextField(8);
        txtTelefono = new JTextField(6);
        txtTelefono.addActionListener(e -> btnAñadir.doClick());
        panelCampos.add(new JLabel("Nombre:"));
        panelCampos.add(txtNombre);
        panelCampos.add(new JLabel("Apellido:"));
        panelCampos.add(txtApellido);
        panelCampos.add(new JLabel("Teléfono:"));
        panelCampos.add(txtTelefono);
        getRootPane().setDefaultButton(btnAñadir);

        // 2b. Campo de búsqueda
        panelCampos.add(Box.createHorizontalStrut(15));    // separador visual
        panelCampos.add(new JLabel("Buscar:"));
        txtBuscar = new JTextField(10);
        panelCampos.add(txtBuscar);

        // 2c. Botones de acción
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnAñadir    = new JButton("\u2795  Añadir");          // ➕
        btnModificar = new JButton("\u270F\uFE0F  Modificar"); // ✏️
        btnEliminar  = new JButton("\uD83D\uDDD1\uFE0F  Eliminar"); // 🗑️
        btnBuscar    = new JButton("\uD83D\uDD0D  Buscar");    // 🔍
        panelBotones.add(btnAñadir);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnBuscar);

        // Unión de subpaneles
        panelSur.add(panelCampos,  BorderLayout.NORTH);
        panelSur.add(panelBotones, BorderLayout.SOUTH);
        add(panelSur, BorderLayout.SOUTH);

        /* === 3) LISTENERS === */

        // ---------- AÑADIR contacto ----------
        btnAñadir.addActionListener(e -> {
            String n = txtNombre.getText().trim();
            String a = txtApellido.getText().trim();
            String t = txtTelefono.getText().trim();

            if (n.isEmpty() || a.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nombre y apellido son obligatorios");
                return;
            }
            try {
                int tel = Integer.parseInt(t);
                Contacto nuevo = new Contacto(n, a, tel);

                if (agenda.agendaLlena()) {
                    JOptionPane.showMessageDialog(this, "⚠️  La agenda está llena");
                    return;
                }
                if (agenda.existeContacto(nuevo)) {
                    JOptionPane.showMessageDialog(this, "⚠️  Contacto duplicado");
                    return;
                }

                agenda.anadirContacto(nuevo);   // método void (no devuelve boolean)
                limpiarCampos();
                txtBuscar.setText("");           // limpia búsqueda previa
                tabla.clearSelection();          // quita selección anterior
                refrescarTabla();
            } catch (NumberFormatException ex1) {
                JOptionPane.showMessageDialog(this, "Teléfono debe ser numérico");
            }
        });

        // ---------- ELIMINAR contacto ----------
        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona un contacto en la tabla");
                return;
            }
            // convertRowIndexToModel → soporta tabla ordenada
            int filaModelo = tabla.convertRowIndexToModel(fila);
            Contacto c = agenda.getContacto().get(filaModelo);
            agenda.eliminarContacto(c.getNombre(), c.getApellido());
            txtBuscar.setText("");
            tabla.clearSelection();
            refrescarTabla();
        });

        // ---------- MODIFICAR teléfono ----------
        btnModificar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona un contacto en la tabla");
                return;
            }
            String nuevoTelStr = JOptionPane.showInputDialog(this, "Nuevo teléfono:");
            if (nuevoTelStr == null) return;  // cancelado
            try {
                int nuevoTel = Integer.parseInt(nuevoTelStr.trim());
                int filaModelo = tabla.convertRowIndexToModel(fila);
                Contacto c = agenda.getContacto().get(filaModelo);
                agenda.modificarTelefono(c.getNombre(), c.getApellido(), nuevoTel);
                txtBuscar.setText("");
                tabla.clearSelection();
                refrescarTabla();
            } catch (NumberFormatException ex1) {
                JOptionPane.showMessageDialog(this, "Teléfono inválido");
            }
        });

        // ---------- BUSCAR: resalta TODAS las coincidencias ----------
        btnBuscar.addActionListener(e -> seleccionarCoincidencias());
        txtBuscar.addActionListener(e -> seleccionarCoincidencias());

        /* === 4) CONFIGURACIÓN DE LA VENTANA === */
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();                      // ajusta tamaño al contenido
        setLocationRelativeTo(null); // centra en pantalla
    }

    /* ---------- HELPERS ---------- */

    // Refresca el modelo de la tabla
    private void refrescarTabla() {
        modeloTabla.fireTableDataChanged();
    }

    // Limpia los campos de alta / modificación
    private void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
    }

    /**
     * Selecciona (resalta) todas las filas cuyo nombre o apellido contienen
     * el texto del campo de búsqueda. Soporta tabla ordenada / reordenada.
     * Si no encuentra coincidencias muestra un mensaje al usuario.
     */
    private void seleccionarCoincidencias() {
        String q = txtBuscar.getText().trim().toLowerCase();
        ListSelectionModel sel = tabla.getSelectionModel();
        sel.clearSelection();                           // limpia selección anterior

        if (q.isEmpty()) return;                        // cadena vacía → nada que buscar

        int matches = 0;
        for (int i = 0; i < agenda.getContacto().size(); i++) {
            Contacto c = agenda.getContacto().get(i);
            if (c.getNombre().toLowerCase().contains(q) ||
                    c.getApellido().toLowerCase().contains(q)) {

                int viewRow = tabla.convertRowIndexToView(i);  // por si la tabla está ordenada
                sel.addSelectionInterval(viewRow, viewRow);    // resalta la fila
                tabla.scrollRectToVisible(tabla.getCellRect(viewRow, 0, true));
                matches++;
            }
        }
        if (matches == 0) {
            JOptionPane.showMessageDialog(this, "⚠️  Sin coincidencias");
        }
    }

    /* ---------- MÉTODO MAIN (entry-point) ---------- */
    public static void main(String[] args) {
        Agenda agenda = new Agenda();      // modelo con tope de 10 entradas
        SwingUtilities.invokeLater(() -> new AgendaGUI(agenda).setVisible(true));
    }
}
