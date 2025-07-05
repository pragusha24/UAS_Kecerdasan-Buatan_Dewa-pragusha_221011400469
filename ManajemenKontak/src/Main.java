import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ContactManager manager = new ContactManager();
        Scanner input = new Scanner(System.in);
        int choice;
        manager.loadContacts();

        do {
            System.out.println("\n=== Aplikasi Manajemen Kontak ===");
            System.out.println("1. Tambah Kontak");
            System.out.println("2. Edit Kontak");
            System.out.println("3. Hapus Kontak");
            System.out.println("4. Tampilkan Semua Kontak");
            System.out.println("5. Simpan & Keluar");
            System.out.print("Pilih: ");
            choice = input.nextInt();
            input.nextLine(); // konsumsi newline

            switch (choice) {
                case 1:
                    System.out.print("Nama: ");
                    String name = input.nextLine();
                    System.out.print("No Telepon: ");
                    String phone = input.nextLine();
                    System.out.print("Email: ");
                    String email = input.nextLine();
                    manager.addContact(new Contact(name, phone, email));
                    break;

                case 2:
                    System.out.print("Masukkan nama kontak yang ingin diedit: ");
                    String oldName = input.nextLine();
                    System.out.print("Nama Baru: ");
                    String newName = input.nextLine();
                    System.out.print("No Telepon Baru: ");
                    String newPhone = input.nextLine();
                    System.out.print("Email Baru: ");
                    String newEmail = input.nextLine();
                    manager.editContact(oldName, new Contact(newName, newPhone, newEmail));
                    break;

                case 3:
                    System.out.print("Masukkan nama kontak yang ingin dihapus: ");
                    String deleteName = input.nextLine();
                    manager.deleteContact(deleteName);
                    break;

                case 4:
                    manager.displayContacts();
                    break;

                case 5:
                    manager.saveContacts();
                    System.out.println("Data disimpan. Keluar...");
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }

        } while (choice != 5);

        input.close();
    }
}
