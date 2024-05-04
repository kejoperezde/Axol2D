import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TSVariable extends JFrame {

    private JTable table;

    public TSVariable(DefaultTableModel model) {
        initComponents(model);
    }

    private void initComponents(DefaultTableModel model) {
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane);
        
        setTitle("Variables");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cerrar solo esta ventana
        setLocationRelativeTo(null);
    }
}
