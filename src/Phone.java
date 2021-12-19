import sun.security.mscapi.CPublicKey;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Phone {
    private String myNumber;
    private String operator;
    private List<Contact> contacts;

    public Phone(String myNumber, String operator) {
        this.myNumber = myNumber;
        this.operator = operator;
        this.contacts = new ArrayList<>();
    }

    public void addContact(String name, String phoneNumber) {
        if (!ifExists(name)) {
            System.out.println("Adding new contact: " + name + " with number " + phoneNumber);
            contacts.add(new Contact(name, phoneNumber));
        } else {
            System.out.println("Contact " + getContactByName(name).getName() + " already exists!");
        }

    }

    public void removeContact(String name) {
        if (ifExists(name)) {
            System.out.println("Removing contact: " + getContactByName(name).getName());
            contacts.remove(getContactByName(name));
        } else {
            System.out.println("Cannot remove the non-existing contact!");
        }
    }

    public void editName(String name, String newName) {

        Contact contact = getContactByName(name);

        if (ifExists(name)) {
            int index = contacts.indexOf(contact);
            contacts.set(index, new Contact(newName, contact.getPhoneNumber()));

        } else {
            System.out.println("Failed to edit contact " + name + " : this contact does not exist!");
        }
    }

    public void editNumber(String name, String newNumber) {
        Contact contact = getContactByName(name);
        if (ifExists(name)) {
            int index = contacts.indexOf(contact);
            contacts.set(index, new Contact(name, newNumber));

        } else {
            System.out.println("Failed to edit contact " + name + " : this contact does not exist!");
        }
    }


    public String printContact(String name) {
        Contact contact = getContactByName(name);
        if (contact != null) {
            return "Name: " + contact.getName() + "\nPhone number: " + contact.getPhoneNumber();
        }
        return "Contact " + name + " was not found!";
    }

    public void printAll() {
        System.out.println("*********************************");
        for (int i = 0; i < contacts.size(); i++) {

            System.out.printf("Contact #%d Name: %s Number: %s%n", i + 1, contacts.get(i).getName(), contacts.get(i).getPhoneNumber());
        }
        System.out.println("*********************************");
    }

    public void printMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean isMenuActive = true;
        String input;


        while (isMenuActive) {
            System.out.println("This is the main menu. Please provide the number of the needed menu section:\n" +
                    "1. Print all contacts\n" +
                    "2. Add new contact\n" +
                    "3. Update existing contact\n" +
                    "4. Remove contact\n" +
                    "5. Quit menu");

            input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.println("Printing all contacts:");
                    printAll();
                    break;
                case "2":
                    System.out.println("Please input the contact name:");
                    String name = scanner.next();
                    System.out.println("Please input the phone number:");
                    String phoneNumber = scanner.next();
                    addContact(name, phoneNumber);
                    break;
                case "3":
                    System.out.println("Please select the contact you wish to edit:");
                    String contactName = scanner.next();
                    if (!ifExists(contactName)) {
                        System.out.println("This contact does not exist, try again");
                        break;
                    }
                    System.out.println("What option do you want to edit? 1. Name 2. Phone number");
                    int var = scanner.nextInt();

                    switch (var) {
                        case 1:
                            System.out.println("Please enter a new name for the contact:");
                            String newName = scanner.next();
                            editName(contactName, newName);
                            break;
                        case 2:
                            System.out.println("Please enter a new number for the contact:");
                            String newNumber = scanner.next();
                            editNumber(contactName, newNumber);
                            break;
                        default:
                            System.out.println("Please enter a value 1 or 2");
                    }
                    break;
                case "4":
                    System.out.println("Please enter the contact name you wish to remove:");
                    String removingContact = scanner.next();
                    removeContact(removingContact);
                    break;
                case "5":
                    System.out.println("Exiting menu...");
                    isMenuActive = false;
                    break;
                default:
                    System.out.println("Please enter the correct menu value!");
            }
        }
    }

    private Contact getContactByName(String name) {
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null;
    }

    private Contact getContactByNumber(String phoneNumber) {
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            if (contact.getPhoneNumber().equals(phoneNumber)) {
                return contact;
            }
        }
        return null;
    }


    private boolean ifExists(String contactName) {
        if (getContactByName(contactName) != null) {
            return true;
        }
        return false;
    }


}
