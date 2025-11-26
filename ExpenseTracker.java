import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.util.Vector;

public class ExpenseTracker extends JFrame {

    private JTextField nameField, amountField, categoryField;
    private DefaultTableModel tableModel;
    private final String CSV_FILE = "expenses.csv";

    public ExpenseTracker() {
        setTitle("Expense Tracker - CSV Version");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // UI Components
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        panel.add(new JLabel("Expense Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Amount:"));
        amountField = new JTextField();
        panel.add(amountField);

        panel.add(new JLabel("Category:"));
        categoryField = new JTextField();
        panel.add(categoryField);

        JButton addBtn = new JButton("Add Expense");
        panel.add(addBtn);

        add(panel, BorderLayout.NORTH);

        // Table
        tableModel = new DefaultTableModel(new String[]{"Name", "Amount", "Category"}, 0);
        JTable table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Load existing CSV data
        loadExpenses();

        // Add button action
        addBtn.addActionListener(e -> addExpense());
    }

    private void addExpense() {
        String name = nameField.getText();
        String amount = amountField.getText();
        String category = categoryField.getText();

        if (name.isEmpty() || amount.isEmpty() || category.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields");
            return;
        }

        // Add to table
        tableModel.addRow(new String[]{name, amount, category});

        // Save to CSV
        saveToCSV(name, amount, category);

        // Clear fields
        nameField.setText("");
        amountField.setText("");
        categoryField.setText("");

        JOptionPane.showMessageDialog(this, "Expense Added!");
    }

    private void saveToCSV(String name, String amount, String category) {
        try (FileWriter fw = new FileWriter(CSV_FILE, true)) {
            fw.write(name + "," + amount + "," + category + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadExpenses() {
        try {
            if (!Files.exists(Paths.get(CSV_FILE))) {
                return;
            }
            BufferedReader br = new BufferedReader(new FileReader(CSV_FILE));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                tableModel.addRow(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ExpenseTracker().setVisible(true));
    }
}
