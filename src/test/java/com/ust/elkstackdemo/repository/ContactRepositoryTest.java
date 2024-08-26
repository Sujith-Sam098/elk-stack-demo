package com.ust.elkstackdemo.repository;

import com.ust.elkstackdemo.model.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactRepositoryTest {
    /**
     * Test cases to be generated
     *
     * 1. addContact_WithValidContact_ShouldAddContact
     * 2. addContact_WithNullName_ShouldThrowException
     * 3. addContact_WithEmptyName_ShouldThrowException
     * 4. addContact_WithNullPhoneNumber_ShouldThrowException
     * 5. addContact_WithEmptyPhoneNumber_ShouldThrowException
     * 6. addContact_WithExistingContact_ShouldThrowException
     * 7. getContacts_WithNoContacts_ShouldReturnEmptyList
     * 8. getContacts_WithContacts_ShouldReturnAllContacts
     * 9. getContactsByPhoneNumber_WithExistingPhoneNumber_ShouldReturnContact
     * 10. getContactsByPhoneNumber_WithNonExistingPhoneNumber_ShouldReturnEmptyList
     * 11. getContactsByName_WithExistingName_ShouldReturnContact
     * 12. getContactsByName_WithNonExistingName_ShouldReturnEmptyList
     * 13. removeContactByPhoneNumber_WithExistingPhoneNumber_ShouldRemoveContact
     * 14. removeContactByPhoneNumber_WithNonExistingPhoneNumber_ShouldThrowException
     * 15. removeContactByPhoneNumber_WithLongerPhoneNumber_ShouldThrowException
     * 16. removeContactByPhoneNumber_WithNullPhoneNumber_ShouldThrowException
     * 17. removeContactByPhoneNumber_WithNonNumericPhoneNumber_ShouldThrowException
     * 18. getContactsByPhoneNumber_WithNonNumericPhoneNumber_ShouldThrowException
     * 19. getContactsByName_WithNullName_ShouldThrowException
     * 20. getContactsByName_WithEmptyName_ShouldThrowException
     *
     * Instructions:
     * 1. While checking for exceptions, ensure the correct exception object is throw
     * and the exception message is correct.
     *
     * 2. Use the DisplayName annotation to give a meaningful name to the test case.
     *
     * 3. Use the @BeforeEach annotation to initialize the ContactRepository object.
     *
     * 4. Ensure code coverage of the ContactRepository class is 100%.
     */

    private ContactRepository contactRepository;
    private Contact validContact;
    private Contact contactWithNullName;
    private Contact contactWithEmptyName;
    private Contact contactWithNullPhoneNumber;
    private Contact contactWithEmptyPhoneNumber;
    private Contact contactWithExistingContact;
    private Contact contactForRemoval;

    @BeforeEach
    void setUp() {
        contactRepository = new ContactRepository();
        validContact = new Contact("Sam", "sss@gmail.com", "9899112379");
        contactWithNullName = new Contact(null, "sss@gmail.com", "9899112379");
        contactWithEmptyName = new Contact("", "sss@gmail.com", "9899112379");
        contactWithNullPhoneNumber = new Contact("Sam", "sss@gmail.com", null);
        contactWithEmptyPhoneNumber = new Contact("Sam", "sss@gmail.com", "");
        contactWithExistingContact = new Contact("Sam", "sss@gmail.com", "9899112379");
        contactForRemoval = new Contact("Alice", "alice@example.com", "1234567890");
    }

    @Test
    @DisplayName("1. addContact_WithValidContact_ShouldAddContact")
    void addContact_WithValidContact_ShouldAddContact() {
        contactRepository.addContact(validContact);
        List<Contact> contacts = contactRepository.getContacts();
        assertTrue(contacts.contains(validContact), "Contact should be added to the repository");
    }

    @Test
    @DisplayName("2. addContact_WithNullName_ShouldThrowException")
    void addContact_WithNullName_ShouldThrowException() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            contactRepository.addContact(contactWithNullName);
        });
        assertEquals("Contact name is empty", thrown.getMessage());
    }

    @Test
    @DisplayName("3. addContact_WithEmptyName_ShouldThrowException")
    void addContact_WithEmptyName_ShouldThrowException() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            contactRepository.addContact(contactWithEmptyName);
        });
        assertEquals("Contact name is empty", thrown.getMessage());
    }

    @Test
    @DisplayName("4. addContact_WithNullPhoneNumber_ShouldThrowException")
    void addContact_WithNullPhoneNumber_ShouldThrowException() {
        contactWithNullPhoneNumber = new Contact("Sam", "sss@gmail.com", null);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            contactRepository.addContact(contactWithNullPhoneNumber);
        });
        assertEquals("Contact phone number is empty", thrown.getMessage());
    }

    @Test
    @DisplayName("5. addContact_WithEmptyPhoneNumber_ShouldThrowException")
    void addContact_WithEmptyPhoneNumber_ShouldThrowException() {
        contactWithEmptyPhoneNumber = new Contact("Sam", "sss@gmail.com", "");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            contactRepository.addContact(contactWithEmptyPhoneNumber);
        });
        assertEquals("Contact phone number is empty", thrown.getMessage());
    }

    @Test
    @DisplayName("6. addContact_WithExistingContact_ShouldThrowException")
    void addContact_WithExistingContact_ShouldThrowException() {
        contactRepository.addContact(validContact);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            contactRepository.addContact(contactWithExistingContact);
        });
        assertEquals("Contact already exists", thrown.getMessage());
    }

    @Test
    @DisplayName("7. getContacts_WithNoContacts_ShouldReturnEmptyList")
    void getContacts_WithNoContacts_ShouldReturnEmptyList() {
        List<Contact> contacts = contactRepository.getContacts();
        assertTrue(contacts.isEmpty(), "Contact list should be empty");
    }

    @Test
    @DisplayName("8. getContacts_WithContacts_ShouldReturnAllContacts")
    void getContacts_WithContacts_ShouldReturnAllContacts() {
        contactRepository.addContact(validContact);
        contactRepository.addContact(contactForRemoval);
        List<Contact> contacts = contactRepository.getContacts();
        assertEquals(2, contacts.size(), "Contact list should contain all added contacts");
        assertTrue(contacts.contains(validContact), "Contact list should contain validContact");
        assertTrue(contacts.contains(contactForRemoval), "Contact list should contain contactForRemoval");
    }

    @Test
    @DisplayName("9. getContactsByPhoneNumber_WithExistingPhoneNumber_ShouldReturnContact")
    void getContactsByPhoneNumber_WithExistingPhoneNumber_ShouldReturnContact() {
        contactRepository.addContact(validContact);
        List<Contact> contacts = contactRepository.getContactsByPhoneNumber("9899112379");
        assertFalse(contacts.isEmpty(), "Contact list should not be empty");
        assertTrue(contacts.contains(validContact), "Contact list should contain validContact");
    }

    @Test
    @DisplayName("10. getContactsByPhoneNumber_WithNonExistingPhoneNumber_ShouldReturnEmptyList")
    void getContactsByPhoneNumber_WithNonExistingPhoneNumber_ShouldReturnEmptyList() {
        List<Contact> contacts = contactRepository.getContactsByPhoneNumber("0000000000");
        assertTrue(contacts.isEmpty(), "Contact list should be empty");
    }

    @Test
    @DisplayName("11. getContactsByName_WithExistingName_ShouldReturnContact")
    void getContactsByName_WithExistingName_ShouldReturnContact() {
        contactRepository.addContact(validContact);
        List<Contact> contacts = contactRepository.getContactsByName("Sam");
        assertFalse(contacts.isEmpty(), "Contact list should not be empty");
        assertTrue(contacts.contains(validContact), "Contact list should contain validContact");
    }

    @Test
    @DisplayName("12. getContactsByName_WithNonExistingName_ShouldReturnEmptyList")
    void getContactsByName_WithNonExistingName_ShouldReturnEmptyList() {
        List<Contact> contacts = contactRepository.getContactsByName("NonExistingName");
        assertTrue(contacts.isEmpty(), "Contact list should be empty");
    }

    @Test
    @DisplayName("13. removeContactByPhoneNumber_WithExistingPhoneNumber_ShouldRemoveContact")
    void removeContactByPhoneNumber_WithExistingPhoneNumber_ShouldRemoveContact() {
        contactRepository.addContact(contactForRemoval);
        contactRepository.removeContactByPhoneNumber("1234567890");
        List<Contact> contacts = contactRepository.getContacts();
        assertFalse(contacts.contains(contactForRemoval), "Contact list should not contain contactForRemoval");
    }

    @Test
    @DisplayName("14. removeContactByPhoneNumber_WithNonExistingPhoneNumber_ShouldThrowException")
    void removeContactByPhoneNumber_WithNonExistingPhoneNumber_ShouldThrowException() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            contactRepository.removeContactByPhoneNumber("0000000000");
        });
        assertEquals("No contact with phone number: 0000000000", thrown.getMessage());
    }

    @Test
    @DisplayName("15. removeContactByPhoneNumber_WithLongerPhoneNumber_ShouldThrowException")
    void removeContactByPhoneNumber_WithLongerPhoneNumber_ShouldThrowException() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            contactRepository.removeContactByPhoneNumber("12345678901");
        });
        assertEquals("Invalid phone number", thrown.getMessage());
    }

    @Test
    @DisplayName("16. removeContactByPhoneNumber_WithNullPhoneNumber_ShouldThrowException")
    void removeContactByPhoneNumber_WithNullPhoneNumber_ShouldThrowException() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            contactRepository.removeContactByPhoneNumber(null);
        });
        assertEquals("Invalid phone number", thrown.getMessage());
    }

    @Test
    @DisplayName("17. removeContactByPhoneNumber_WithNonNumericPhoneNumber_ShouldThrowException")
    void removeContactByPhoneNumber_WithNonNumericPhoneNumber_ShouldThrowException() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            contactRepository.removeContactByPhoneNumber("abcd1234");
        });
        assertEquals("Phone number is not a number", thrown.getMessage());
    }

    @Test
    @DisplayName("18. getContactsByPhoneNumber_WithNonNumericPhoneNumber_ShouldThrowException")
    void getContactsByPhoneNumber_WithNonNumericPhoneNumber_ShouldThrowException() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            contactRepository.getContactsByPhoneNumber("abcd1234");
        });
        assertEquals("Phone number is not a number", thrown.getMessage());
    }

    @Test
    @DisplayName("19. getContactsByName_WithNullName_ShouldThrowException")
    void getContactsByName_WithNullName_ShouldThrowException() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            contactRepository.getContactsByName(null);
        });
        assertEquals("Name is null or empty.", thrown.getMessage());
    }

    @Test
    @DisplayName("20. getContactsByName_WithEmptyName_ShouldThrowException")
    void getContactsByName_WithEmptyName_ShouldThrowException() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            contactRepository.getContactsByName("");
        });
        assertEquals("Name is null or empty.", thrown.getMessage());
    }
}