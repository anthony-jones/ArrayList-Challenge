package com.acejones;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static MobilePhone mobilePhone = new MobilePhone("609 555 5555");

    public static void main(String[] args) {
        boolean quit = false;
        startPhone();
        printInstructions();
        int choice = 0;

        while (!quit) {
            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0:
                    printInstructions();
                    break;
                case 1:
                    viewAllContacts();
                    break;
                case 2:
                    addNewContact();
                    break;
                case 3:
                    updateContact();
                    break;
                case 4:
                    removeContact();
                    break;
                case 5:
                    searchForContact();
                    break;
                case 6:
                    quit = true;
                    break;
            }
        }
    }

    public static void printInstructions() {
        System.out.println("\nPress ");
        System.out.println("\t 0 - View Menu");
        System.out.println("\t 1 - View all contacts");
        System.out.println("\t 2 - Add new contact");
        System.out.println("\t 3 - Update contact");
        System.out.println("\t 4 - Remove a contact");
        System.out.println("\t 5 - Search for a contact");
        System.out.println("\t 6 - Exit");
    }

    public static void viewAllContacts() {
        mobilePhone.printContactList();
    }

    public static void addNewContact() {
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("Enter number: ");
        String number = scanner.nextLine();
        Contact newContact = Contact.createContact(name, number);
        if (mobilePhone.addNewContact(newContact)) {
            System.out.println("New contact added: " + name + " - " + number);
        } else {
            System.out.println("Can't add " + name + ", already on file.");
        }

    }

    public static void updateContact() {
        System.out.println("Enter the contact you would like to update: ");
        String oldName = scanner.nextLine();
        Contact existingContact = mobilePhone.queryContact(oldName);
        if (existingContact == null) {
            System.out.println("Contact not found.");
        } else {
            System.out.println("Enter new contact name: ");
            String newName = scanner.nextLine();
            System.out.println("Enter new contact phone number: ");
            String newNumber = scanner.nextLine();
            Contact newContact = Contact.createContact(newName, newNumber);
            if (mobilePhone.updateContact(existingContact, newContact)) {
                System.out.println("Successfully updated record.");
            } else {
                System.out.println("Error updating record");
            }
        }
    }

    public static void removeContact() {
        System.out.println("Enter the contact you would like to remove: ");
        String name = scanner.nextLine();
        Contact existingContact = mobilePhone.queryContact(name);
        if (existingContact == null) {
            System.out.println("Contact not found.");
        } else {
            if (mobilePhone.removeContact(existingContact)) {
                System.out.println(name + " has been removed.");
            } else {
                System.out.println("Error removing " + name);
            }
        }
    }

    public static void searchForContact() {
        System.out.println("Enter the contact you would like to search for: ");
        String name = scanner.nextLine();
        Contact existingContact = mobilePhone.queryContact(name);
        if (existingContact == null) {
            System.out.println("Contact not found.");
        } else {
            System.out.println("Name: " + existingContact.getName() + "'s phone number is " + existingContact.getPhoneNumber());
        }
    }

    private static void startPhone() {
        System.out.println("Starting phone...");
    }

}
