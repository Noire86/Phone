import sun.security.mscapi.CPublicKey;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    public void printAll(){
        System.out.println("*********************************");
        for(int i = 0; i < contacts.size(); i++){

            System.out.printf("Contact #%d Name: %s Number: %s%n", i + 1, contacts.get(i).getName(), contacts.get(i).getPhoneNumber());
        }
        System.out.println("*********************************");
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
