package com.ust.elkstackdemo.repository;

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

}