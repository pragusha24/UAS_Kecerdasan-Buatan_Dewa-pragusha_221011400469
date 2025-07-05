import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactManager {
    private ArrayList<Contact> contacts = new ArrayList<>();
    private final String FILE_NAME = "contacts.txt";

    public void loadContacts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    contacts.add(new Contact(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("File tidak ditemukan, membuat file baru...");
        }
    }

    public void saveContacts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Contact c : contacts) {
                writer.write(c.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data.");
        }
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        System.out.println("Kontak ditambahkan.");
    }

    public void editContact(String name, Contact newContact) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getName().equalsIgnoreCase(name)) {
                contacts.set(i, newContact);
                System.out.println("Kontak berhasil diedit.");
                return;
            }
        }
        System.out.println("Kontak tidak ditemukan.");
    }

    public void deleteContact(String name) {
        contacts.removeIf(c -> c.getName().equalsIgnoreCase(name));
        System.out.println("Kontak dihapus jika ditemukan.");
    }

    public void displayContacts() {
        if (contacts.isEmpty()) {
            System.out.println("Belum ada kontak.");
        } else {
            for (Contact c : contacts) {
                System.out.println(c);
            }
        }
    }
}
