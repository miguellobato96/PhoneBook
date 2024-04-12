import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Phonebook {
    private List<Contact> contacts;
    private static final String CSV_FILE_PATH = "contacts.csv";

    // Constructor of the Phonebook class
    public Phonebook() {
        this.contacts = new ArrayList<>();
        loadCSV(); // Loads contacts from the CSV file when instantiating the phonebook
    }

    // Adds a new contact to the phonebook
    public void addContact(Contact contact) {
        if (!contacts.contains(contact)) {
            contacts.add(contact);
            saveCSV(); // Saves the phonebook to the CSV file after adding a contact
            System.out.println("Contact added: " + contact);
        } else {
            System.out.println("Contact already exists in the list.");
        }
    }

    // Removes a contact from the phonebook
    public void removeContact(Contact contact) {
        contacts.remove(contact);
        saveCSV(); // Saves the phonebook to the CSV file after removing a contact
        System.out.println("Contact removed: " + contact);
    }

    // Lists all contacts in the phonebook
    public void listContacts() {
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    // Searches for a contact based on a specific attribute and value
    public Contact searchContact(String attribute, String value) {
        for (Contact contact : contacts) {
            switch (attribute.toLowerCase()) {
                case "name":
                    if (contact.getName().equalsIgnoreCase(value)) {
                        return contact;
                    }
                    break;
                case "phone":
                    if (contact.getPhone().equalsIgnoreCase(value)) {
                        return contact;
                    }
                    break;
                case "email":
                    if (contact.getEmail().equalsIgnoreCase(value)) {
                        return contact;
                    }
                    break;
                case "address":
                    if (contact.getAddress().equalsIgnoreCase(value)) {
                        return contact;
                    }
                    break;
                default:
                    System.out.println("Invalid attribute. Choose between name, phone, email, or address.");
            }
        }
        return null;
    }

    // Modifies the data of a specific contact
    public void modifyContact(String name, Scanner scanner) {
        Contact contact = searchContact("name", name);
        if (contact != null) {
            System.out.println("Choose the attribute to modify:");
            System.out.println("1. Name");
            System.out.println("2. Phone");
            System.out.println("3. Email");
            System.out.println("4. Address");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("New Name: ");
                    String newName = scanner.nextLine();
                    contact.setName(newName);
                    System.out.println("Name modified to " + newName);
                    break;
                case 2:
                    System.out.print("New Phone: ");
                    String newPhone = scanner.nextLine();
                    contact.setPhone(newPhone);
                    System.out.println("Phone modified to " + newPhone);
                    break;
                case 3:
                    System.out.print("New Email: ");
                    String newEmail = scanner.nextLine();
                    contact.setEmail(newEmail);
                    System.out.println("Email modified to " + newEmail);
                    break;
                case 4:
                    System.out.print("New Address: ");
                    String newAddress = scanner.nextLine();
                    contact.setAddress(newAddress);
                    System.out.println("Address modified to " + newAddress);
                    break;
                default:
                    System.out.println("Invalid option.");
            }

            saveCSV(); // Saves the phonebook to the CSV file after modifying a contact
        } else {
            System.out.println("Contact not found.");
        }
    }

    // Method to save the phonebook to the CSV file
    private void saveCSV() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE_PATH))) {
            for (Contact contact : contacts) {
                writer.println(contact.getName() + "," + contact.getPhone() + "," + contact.getEmail() + "," + contact.getAddress());
            }
            System.out.println("Phonebook saved to " + CSV_FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error saving CSV: " + e.getMessage());
        }
    }

    // Method to load the phonebook from the CSV file
    private void loadCSV() {
        File file = new File(CSV_FILE_PATH);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length == 4) {
                        Contact contact = new Contact(data[0], data[1], data[2], data[3]);
                        contacts.add(contact);
                    }
                }
                System.out.println("Phonebook loaded from " + CSV_FILE_PATH);
            } catch (IOException e) {
                System.err.println("Error loading CSV: " + e.getMessage());
            }
        } else {
            System.out.println("Creating new CSV file: " + CSV_FILE_PATH);
        }
    }

    // Main method that starts the program
    public static void main(String[] args) {
        Phonebook phonebook = new Phonebook();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            // Main menu
            System.out.println("Menu:");
            System.out.println("1. Add Contact");
            System.out.println("2. Remove Contact");
            System.out.println("3. List Contacts");
            System.out.println("4. Search Contact");
            System.out.println("5. Modify Contact");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Add a new contact
                    System.out.print("Contact Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Contact Phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Contact Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Contact Address: ");
                    String address = scanner.nextLine();
                    phonebook.addContact(new Contact(name, phone, email, address));
                    break;
                case 2:
                    // Remove a contact
                    System.out.print("Name of Contact to be removed: ");
                    String nameToRemove = scanner.nextLine();
                    phonebook.removeContact(phonebook.searchContact("name", nameToRemove));
                    break;
                case 3:
                    // List all contacts
                    phonebook.listContacts();
                    break;
                case 4:
                    // Search for a contact
                    String attributeToSearch;
                    do {
                        System.out.print("Attribute to search by ('name', 'phone', 'email', 'address'): ");
                        attributeToSearch = scanner.nextLine();
                        if (attributeToSearch.equalsIgnoreCase("name") || 
                            attributeToSearch.equalsIgnoreCase("phone") || 
                            attributeToSearch.equalsIgnoreCase("email") || 
                            attributeToSearch.equalsIgnoreCase("address")) {
                            break;
                        } else {
                            System.out.println("Invalid attribute. Choose between name, phone, email, or address.");
                        }
                    } while (true);

                    System.out.print("Enter the " + attributeToSearch + " of the Contact to search for: ");
                    String valueToSearch = scanner.nextLine();

                    Contact foundContact = phonebook.searchContact(attributeToSearch, valueToSearch);
                    if (foundContact != null) {
                        System.out.println("Contact found: " + foundContact);
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;
                case 5:
                    // Modify a contact
                    System.out.print("Name of Contact to be modified: ");
                    String nameToModify = scanner.nextLine();
                    phonebook.modifyContact(nameToModify, scanner);
                    break;
                case 0:
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

        } while (choice != 0);

        scanner.close();
    }
}
