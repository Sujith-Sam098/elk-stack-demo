package com.ust.elkstackdemo.service;

import com.ust.elkstackdemo.model.Contact;
import com.ust.elkstackdemo.repository.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ContactServiceTest {

    /**
     * Use Mockito to mock the ContactRepository object
     * <p>
     * Test cases to be generated
     * 1. addContact_WithValidContact_ShouldAddContact
     * 2. addContact_WithNullName_ShouldThrowException
     * 3. addContact_WithEmptyName_ShouldThrowException
     * 4. addContact_WithNullPhoneNumber_ShouldThrowException
     * 5. addContact_WithEmptyPhoneNumber_ShouldThrowException
     * 6. addContact_WithExistingContact_ShouldThrowException
     */

    @Mock
    ContactRepository contactRepository;

    @InjectMocks
    ContactService contactService;

    List<Contact> contacts;
    Contact contact;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        contact = new Contact("John Doe", "john.doe", "1234567890");
        contacts = List.of(contact);
    }

    @Test
    @DisplayName("Add contact with valid contact should add contact")
    void addContact_WithValidContact_ShouldAddContact() {
        when(contactRepository.getContacts()).thenReturn(contacts);
        contactService.addContact(contact.getName(), contact.getEmail(), contact.getPhoneNumber());
        verify(contactRepository).addContact(any(Contact.class));
        assertEquals(1, contactRepository.getContacts().size());
    }

    @Test
    @DisplayName("Add contact with null name should throw exception")
    void addContact_WithNullName_ShouldThrowException()
    {
        doThrow(new IllegalArgumentException("Contact name is empty")).when(contactRepository).addContact(any(Contact.class));
        IllegalArgumentException exp=assertThrows(IllegalArgumentException.class,()-> contactService.addContact(null,"john.doe","0009876543"));
        verify(contactRepository, times(1)).addContact(any(Contact.class));
        assertEquals("Contact name is empty",exp.getMessage());
    }
    @Test
    @DisplayName("Add contact with empty name should throw exception")
    void addContact_WithEmptyName_ShouldThrowException()
    {
        doThrow(new IllegalArgumentException("Contact name is empty")).when(contactRepository).addContact(any(Contact.class));
        IllegalArgumentException exp=assertThrows(IllegalArgumentException.class,()-> contactService.addContact("","john.doe","0009876543"));
        verify(contactRepository, times(1)).addContact(any(Contact.class));
        assertEquals("Contact name is empty",exp.getMessage());
    }
    @Test
    @DisplayName("Add contact with null phone number should throw exception")
    void addContact_WithNullPhoneNumber_ShouldThrowException()
    {
        doThrow(new IllegalArgumentException("Contact phone number is empty")).when(contactRepository).addContact(any(Contact.class));
        IllegalArgumentException exp=assertThrows(IllegalArgumentException.class,()-> contactService.addContact("John Doe","john.doe",null));
        verify(contactRepository, times(1)).addContact(any(Contact.class));
        assertEquals("Contact phone number is empty",exp.getMessage());
    }
    @Test
    @DisplayName("Add contact with empty phone number should throw exception")
    void addContact_WithEmptyPhoneNumber_ShouldThrowException()
    {
        doThrow(new IllegalArgumentException("Contact phone number is empty")).when(contactRepository).addContact(any(Contact.class));
        IllegalArgumentException exp=assertThrows(IllegalArgumentException.class,()-> contactService.addContact("John Doe","john.doe",""));
        verify(contactRepository, times(1)).addContact(any(Contact.class));
        assertEquals("Contact phone number is empty",exp.getMessage());
    }
    @Test
    @DisplayName("addContact_WithExistingContact_ShouldThrowException")
    void addContact_WithExistingContact_ShouldThrowException()
    {
        // Contact existingContact = new Contact("John Doe", "john.doe", "1234567890");
        doThrow(new IllegalArgumentException("Contact already exists")).when(contactRepository).addContact(any(Contact.class));

       IllegalArgumentException thrownException = assertThrows(IllegalArgumentException.class, () -> contactService.addContact("John Doe", "john.doe", "1234567890"));
        assertEquals("Contact already exists", thrownException.getMessage());
        
        verify(contactRepository, times(1)).addContact(any(Contact.class));
    }

    @Test
    @DisplayName("removeContactByPhoneNumber_ShouldRemoveContactSuccessfully")
    void removeContactByPhoneNumber_ShouldRemoveContactSuccessfully() {
        // Arrange
        String phoneNumber = "1234567890";

        // Act
        contactService.removeContactByPhoneNumber(phoneNumber);

        // Assert
        verify(contactRepository, times(1)).removeContactByPhoneNumber(phoneNumber);
    }

    @Test
    @DisplayName("getContacts_ShouldReturnAllContacts")
    void getContacts_ShouldReturnAllContacts() {
        // Arrange
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("John Doe", "john.doe@example.com", "1234567890"));
        when(contactRepository.getContacts()).thenReturn(contacts);

        // Act
        List<Contact> result = contactService.getContacts();

        // Assert
        assertEquals(contacts, result);
        verify(contactRepository, times(1)).getContacts();
    }

    @Test
    @DisplayName("getContactsByPhoneNumber_ShouldReturnContactsWithPhoneNumber")
    void getContactsByPhoneNumber_ShouldReturnContactsWithPhoneNumber() {
        // Arrange
        String phoneNumber = "1234567890";
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("John Doe", "john.doe@example.com", phoneNumber));
        when(contactRepository.getContactsByPhoneNumber(phoneNumber)).thenReturn(contacts);

        // Act
        List<Contact> result = contactService.getContactsByPhoneNumber(phoneNumber);

        // Assert
        assertEquals(contacts, result);
        verify(contactRepository, times(1)).getContactsByPhoneNumber(phoneNumber);
    }

    @Test
    @DisplayName("getContactsByName_ShouldReturnContactsWithName")
    void getContactsByName_ShouldReturnContactsWithName() {
        // Arrange
        String name = "John Doe";
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact(name, "john.doe@example.com", "1234567890"));
        when(contactRepository.getContactsByName(name)).thenReturn(contacts);

        // Act
        List<Contact> result = contactService.getContactsByName(name);

        // Assert
        assertEquals(contacts, result);
        verify(contactRepository, times(1)).getContactsByName(name);
    }
}