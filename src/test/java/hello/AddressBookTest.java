package hello;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddressBookTest {
    private AddressBook addressBook;
    private BuddyInfo fakeBuddyOne;
    private BuddyInfo fakeBuddyTwo;

    @Before
    public void setUp() {
        addressBook = new AddressBook();

        fakeBuddyOne = new BuddyInfo("Fake Buddy 1", "", "");
        fakeBuddyTwo = new BuddyInfo("Fake Buddy 2", "", "");
    }

    @Test
    public void addBuddy() {
        assertEquals(0, addressBook.size());
        addressBook.addBuddy(fakeBuddyOne);
        addressBook.addBuddy(fakeBuddyTwo);
        assertEquals(2, addressBook.size());
    }

    @Test
    public void removeBuddy() {
        // Test both removeBuddy method overloads
        // Add both application.model to address book
        addressBook.addBuddy(fakeBuddyOne);
        addressBook.addBuddy(fakeBuddyTwo);
        assertEquals(2, addressBook.size());

        addressBook.removeBuddy(fakeBuddyOne);
        assertEquals(1, addressBook.size());

        addressBook.removeBuddy(0);
        assertEquals(0, addressBook.size());
    }

    @Test
    public void size() {
        assertEquals(0, addressBook.size());

        addressBook.addBuddy(fakeBuddyOne);
        assertEquals(1, addressBook.size());

        addressBook.addBuddy(fakeBuddyOne);
        assertEquals(2, addressBook.size());
    }

    @Test
    public void testToString() {
        // Add application.model to address book
        addressBook.addBuddy(fakeBuddyOne);
        addressBook.addBuddy(fakeBuddyTwo);

        // Ensure that toString has all buddy toString results
        String addrStr = addressBook.toString();
        assertTrue(addrStr.contains(fakeBuddyOne.toString()));
        assertTrue(addrStr.contains(fakeBuddyTwo.toString()));
    }

    @Test
    public void testEquals() {
        AddressBook addressBookEqualsWithoutId = new AddressBook();
        AddressBook addressBookEmpty = new AddressBook();
        AddressBook addressBookNotEquals = new AddressBook();

        // Add application.model to address book
        addressBook.addBuddy(fakeBuddyOne);
        addressBook.addBuddy(fakeBuddyTwo);

        // Add application.model to equivalent address book
        addressBookEqualsWithoutId.addBuddy(fakeBuddyOne);
        addressBookEqualsWithoutId.addBuddy(fakeBuddyTwo);

        // Add application.model to non-equivalent address book
        addressBookNotEquals.addBuddy(fakeBuddyOne);

        assertEquals(addressBook, addressBookEqualsWithoutId);
        assertNotEquals(addressBook, addressBookNotEquals);
        assertNotEquals(addressBook, addressBookEmpty);

    }

}