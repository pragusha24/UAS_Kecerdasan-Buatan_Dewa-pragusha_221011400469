import javax.swing.*;
import java.awt.event.*;

public class FormSederhana extends JFrame {
    private JLabel labelNama;
    private JTextField textNama;
    private JButton buttonTampil;
    private JTextArea textAreaOutput;

    public FormSederhana() {
        setTitle("Form Sederhana");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        labelNama = new JLabel("Nama:");
        labelNama.setBounds(20, 20, 100, 25);
        add(labelNama);

        textNama = new JTextField();
        textNama.setBounds(100, 20, 200, 25);
        add(textNama);

        buttonTampil = new JButton("Tampilkan");
        buttonTampil.setBounds(100, 60, 100, 30);
        add(buttonTampil);

        textAreaOutput = new JTextArea();
        textAreaOutput.setBounds(20, 110, 300, 80);
        add(textAreaOutput);

        buttonTampil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nama = textNama.getText();
                textAreaOutput.setText("Halo, " + nama + "!\nSelamat datang di aplikasi Java Swing.");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FormSederhana().setVisible(true);
        });
    }
}
