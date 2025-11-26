import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Expense {
    String category;
    double amount;

    Expense(String category, double amount) {
        this.category = category;
        this.amount = amount;
    }
}

public class ExpenseTracker extends JFrame {

    ArrayList<Expense> expenses = new ArrayList<>();

    JTextField amountField;
    JComboBox<String> categoryBox;
    JTextArea reportArea;

    public ExpenseTracker() {
        setTitle("Personal Expense Tracker");
        setSize(400, 500);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel title = new JLabel("Expense Tracker", SwingConstants.CENTER);
        title.setBounds(100, 10, 200, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title);

        JLabel catLabel = new JLabel("Category:");
        catLabel.setBounds(40, 70, 100, 30);
        add(catLabel);

        categoryBox = new JComboBox<>(new String[]{"Food", "Travel", "Shopping", "Bills", "Others"});
        categoryBox.setBounds(150, 70, 150, 30);
        add(categoryBox);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(40, 120, 100, 30);
        add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(150, 120, 150, 30);
        add(amountField);

        JButton addBtn = new JButton("Add Expense");
        addBtn.setBounds(120, 170, 150, 30);
        add(addBtn);

        reportArea = new JTextArea();
        JScrollPane pane = new JScrollPane(reportArea);
        pane.setBounds(40, 220, 300, 200);
        add(pane);

        JButton reportBtn = new JButton("Show Report");
        reportBtn.setBounds(120, 430, 150, 30);
        add(reportBtn);

        addBtn.addActionListener(e -> addExpense());
        reportBtn.addActionListener(e -> showReport());
    }

    void addExpense() {
        String category = (String) categoryBox.getSelectedItem();
        double amount = Double.parseDouble(amountField.getText());

        expenses.add(new Expense(category, amount));
        JOptionPane.showMessageDialog(this, "Expense Added!");
        amountField.setText("");
    }

    void showReport() {
        double total = 0;
        StringBuilder sb = new StringBuilder("---- Expense Report ----\n");

        for (Expense e : expenses) {
            sb.append(e.category).append(": ₹").append(e.amount).append("\n");
            total += e.amount;
        }

        sb.append("\nTotal: ₹").append(total);
        reportArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        new ExpenseTracker().setVisible(true);
    }
}
