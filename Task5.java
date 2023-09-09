import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Contact {
    private String name;
    private String phoneNumber;
    private String emailAddress;

    public Contact(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNumber + ", Email: " + emailAddress;
    }
}
class AddressBook {
    private ArrayList<Contact> contacts = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(String name) {
        contacts.removeIf(contact -> contact.getName().equalsIgnoreCase(name));
    }

    public Contact searchContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null;
    }

    public void displayAllContacts() {
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(contacts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            contacts = (ArrayList<Contact>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void run() {
        while (true) {
            System.out.println("\nAddress Book System");
            System.out.println("1. Add Contact");
            System.out.println("2. Remove Contact");
            System.out.println("3. Search Contact");
            System.out.println("4. Display All Contacts");
            System.out.println("5. Save Contacts to File");
            System.out.println("6. Load Contacts from File");
            System.out.println("7. Exit");
            System.out.print("Enter your choice (1/2/3/4/5/6/7): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter contact name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter email address: ");
                    String emailAddress = scanner.nextLine();
                    Contact newContact = new Contact(name, phoneNumber, emailAddress);
                    addContact(newContact);
                    System.out.println("Contact added successfully.");
                    break;
                case 2:
                    System.out.print("Enter the name of the contact to remove: ");
                    String removeName = scanner.nextLine();
                    removeContact(removeName);
                    System.out.println("Contact removed successfully.");
                    break;
                case 3:
                    System.out.print("Enter the name of the contact to search: ");
                    String searchName = scanner.nextLine();
                    Contact foundContact = searchContact(searchName);
                    if (foundContact != null) {
                        System.out.println("Contact found: " + foundContact);
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;
                case 4:
                    System.out.println("All Contacts:");
                    displayAllContacts();
                    break;
                case 5:
                    System.out.print("Enter the filename to save contacts: ");
                    String saveFileName = scanner.next();
                    saveToFile(saveFileName);
                    System.out.println("Contacts saved to file.");
                    break;
                case 6:
                    System.out.print("Enter the filename to load contacts from: ");
                    String loadFileName = scanner.next();
                    loadFromFile(loadFileName);
                    System.out.println("Contacts loaded from file.");
                    break;
                case 7:
                    System.out.println("Exiting Address Book System. Thank you!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}

public class Task5 {
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        addressBook.run();
    }
}
