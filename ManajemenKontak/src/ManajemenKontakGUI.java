import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ManajemenKontakGUI extends JFrame {
    private JTextField nameField, phoneField, emailField;
    private JTable contactTable;
    private DefaultTableModel tableModel;
    private final String FILE_NAME = "src/contacts.txt";

    public ManajemenKontakGUI() {
        setTitle("📇 Aplikasi Manajemen Kontak");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Form Kontak"));

        JLabel nameLabel = new JLabel("Nama");
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        nameField = new JTextField();
        formPanel.add(nameLabel);
        formPanel.add(nameField);

        JLabel phoneLabel = new JLabel("Telepon");
        phoneLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        phoneField = new JTextField();
        formPanel.add(phoneLabel);
        formPanel.add(phoneField);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        emailField = new JTextField();
        formPanel.add(emailLabel);
        formPanel.add(emailField);

        JButton addButton = new JButton("➕ Tambah Kontak");
        addButton.setBackground(new Color(0, 153, 76));
        addButton.setForeground(Color.WHITE);
        formPanel.add(addButton);

        JButton saveButton = new JButton("💾 Simpan ke File");
        saveButton.setBackground(new Color(0, 102, 204));
        saveButton.setForeground(Color.WHITE);
        formPanel.add(saveButton);

        // Table panel
        tableModel = new DefaultTableModel(new Object[]{"Nama", "Telepon", "Email"}, 0);
        contactTable = new JTable(tableModel);
        contactTable.setRowHeight(24);
        contactTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        contactTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(contactTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Daftar Kontak"));

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton editButton = new JButton("✏️ Edit Kontak");
        JButton deleteButton = new JButton("🗑️ Hapus Kontak");

        editButton.setBackground(new Color(255, 204, 0));
        deleteButton.setBackground(new Color(204, 0, 0));
        editButton.setForeground(Color.BLACK);
        deleteButton.setForeground(Color.WHITE);

        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        add(formPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        loadContacts();

        // Action listeners
        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();
            if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
                tableModel.addRow(new Object[]{name, phone, email});
                nameField.setText("");
                phoneField.setText("");
                emailField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            }
        });

        deleteButton.addActionListener(e -> {
            int selected = contactTable.getSelectedRow();
            if (selected >= 0) {
                tableModel.removeRow(selected);
            } else {
                JOptionPane.showMessageDialog(this, "Pilih kontak yang ingin dihapus.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            }
        });

        editButton.addActionListener(e -> {
            int selected = contactTable.getSelectedRow();
            if (selected >= 0) {
                nameField.setText(tableModel.getValueAt(selected, 0).toString());
                phoneField.setText(tableModel.getValueAt(selected, 1).toString());
                emailField.setText(tableModel.getValueAt(selected, 2).toString());
                tableModel.removeRow(selected);
            } else {
                JOptionPane.showMessageDialog(this, "Pilih kontak yang ingin diedit.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            }
        });

        saveButton.addActionListener(e -> saveContacts());
    }

    private void loadContacts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    tableModel.addRow(new Object[]{parts[0], parts[1], parts[2]});
                }
            }
        } catch (IOException e) {
            System.out.println("File tidak ditemukan. Akan dibuat saat menyimpan.");
        }
    }

    private void saveContacts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String name = tableModel.getValueAt(i, 0).toString();
                String phone = tableModel.getValueAt(i, 1).toString();
                String email = tableModel.getValueAt(i, 2).toString();
                writer.write(name + ";" + phone + ";" + email);
                writer.newLine();
            }
            JOptionPane.showMessageDialog(this, "Kontak berhasil disimpan!", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan kontak.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ManajemenKontakGUI().setVisible(true));
    }
}
