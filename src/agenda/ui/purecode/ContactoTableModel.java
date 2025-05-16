package agenda.ui.purecode;
import agenda.model.*;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ContactoTableModel extends AbstractTableModel {
    private final List<Contacto> datos;              // lista que ya existe en agenda.model.Agenda
    private final String[] columnas = {"Nombre", "Apellido", "Teléfono"};

    public ContactoTableModel(List<Contacto> datos) {
        this.datos = datos;
    }

    // --- Métodos mínimos que exige AbstractTableModel ---
    @Override public int getRowCount()                    { return datos.size(); }
    @Override public int getColumnCount()                 { return columnas.length; }
    @Override public String getColumnName(int col)        { return columnas[col]; }

    @Override
    public Object getValueAt(int row, int col) {
        Contacto c = datos.get(row);
        return switch (col) {
            case 0 -> c.getNombre();
            case 1 -> c.getApellido();
            case 2 -> c.getTelefono();
            default -> "";
        };
    }

}

