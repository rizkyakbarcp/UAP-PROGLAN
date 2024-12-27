import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class LaundryApp extends JFrame {

    private JTextField nameField, weightField;
    private JComboBox<String> typeComboBox;
    private JTable table;
    private DefaultTableModel tableModel;

    public LaundryApp() {
        setTitle("Aplikasi Laundry");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        // Panel input
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input Pesanan"));

        JLabel nameLabel = new JLabel("Nama Pelanggan:");
        JLabel weightLabel = new JLabel("Berat Cucian (kg):");
        JLabel typeLabel = new JLabel("Jenis Cucian:");

        nameField = new JTextField();
        weightField = new JTextField();
        typeComboBox = new JComboBox<>(new String[]{"Biasa", "Delicate", "Besar (Bedcover, Selimut)"});

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(weightLabel);
        inputPanel.add(weightField);
        inputPanel.add(typeLabel);
        inputPanel.add(typeComboBox);

        JButton addButton = new JButton("Tambah Pesanan");
        inputPanel.add(new JLabel()); // Empty space
        inputPanel.add(addButton);

        // Tabel untuk daftar pesanan
        tableModel = new DefaultTableModel(new String[]{"Nama", "Jenis", "Berat (kg)", "Harga/kg", "Total"}, 0);
        table = new JTable(tableModel);

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder("Daftar Pesanan"));

        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton printButton = new JButton("Cetak Struk");
        JButton saveButton = new JButton("Simpan Data");
        JButton clearButton = new JButton("Hapus Semua");

        buttonPanel.add(printButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(clearButton);

        // Tambahkan listener untuk tombol Tambah Pesanan
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOrder();
            }
        });

        // Tambahkan listener untuk tombol Cetak Struk
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printReceipt();
            }
        });

        // Tambahkan listener untuk tombol Simpan Data
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveData();
            }
        });

        // Tambahkan listener untuk tombol Hapus Semua
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearOrders();
            }
        });

        // Layout utama
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addOrder() {
        try {
            String name = nameField.getText();
            double weight = Double.parseDouble(weightField.getText());
            String type = (String) typeComboBox.getSelectedItem();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nama pelanggan tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Tentukan harga per kg berdasarkan jenis cucian
            double pricePerKg;
            switch (type) {
                case "Delicate":
                    pricePerKg = 8000;
                    break;
                case "Besar (Bedcover, Selimut)":
                    pricePerKg = 10000;
                    break;
                default:
                    pricePerKg = 5000;
            }

            double total = weight * pricePerKg;

            tableModel.addRow(new Object[]{name, type, weight, pricePerKg, total});

            // Clear input fields
            nameField.setText("");
            weightField.setText("");
            typeComboBox.setSelectedIndex(0);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Mohon masukkan data yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearOrders() {
        tableModel.setRowCount(0);
    }

    private void printReceipt() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("=== STRUK LAUNDRY ===\n");
        receipt.append("Tanggal: ").append(new Date()).append("\n\n");

        double grandTotal = 0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String name = (String) tableModel.getValueAt(i, 0);
            String type = (String) tableModel.getValueAt(i, 1);
            double weight = (double) tableModel.getValueAt(i, 2);
            double price = (double) tableModel.getValueAt(i, 3);
            double total = (double) tableModel.getValueAt(i, 4);

            receipt.append("Nama: ").append(name)
                    .append("\nJenis: ").append(type)
                    .append("\nBerat: ").append(weight).append(" kg")
                    .append("\nHarga/kg: Rp").append(price)
                    .append("\nTotal: Rp").append(total).append("\n\n");

            grandTotal += total;
        }

        receipt.append("GRAND TOTAL: Rp").append(grandTotal).append("\n");
        receipt.append("======================\n");

        // Tampilkan struk di dialog
        JTextArea textArea = new JTextArea(receipt.toString());
        textArea.setEditable(false);
        JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "Struk Laundry", JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveData() {
        try (FileWriter writer = new FileWriter("laundry_data.txt", true)) {
            writer.write("=== DATA LAUNDRY ===\n");
            writer.write("Tanggal: " + new Date() + "\n\n");

            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String name = (String) tableModel.getValueAt(i, 0);
                String type = (String) tableModel.getValueAt(i, 1);
                double weight = (double) tableModel.getValueAt(i, 2);
                double price = (double) tableModel.getValueAt(i, 3);
                double total = (double) tableModel.getValueAt(i, 4);

                writer.write("Nama: " + name + "\n");
                writer.write("Jenis: " + type + "\n");
                writer.write("Berat: " + weight + " kg\n");
                writer.write("Harga/kg: Rp" + price + "\n");
                writer.write("Total: Rp" + total + "\n\n");
            }

            writer.write("======================\n\n");
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan ke file laundry_data.txt!", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menyimpan data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LaundryApp app = new LaundryApp();
            app.setVisible(true);
        });
    }
}
