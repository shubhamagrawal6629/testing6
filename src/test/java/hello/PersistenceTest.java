package hello;



import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PersistenceTest {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private BuddyInfoRepository buddyInfoRepository;

    private BuddyInfo buddyOne;
    private BuddyInfo buddyTwo;
    private AddressBook addressBook;


    @Before
    public void setUp()
    {
        addressBookRepository.deleteAll();
        buddyInfoRepository.deleteAll();

        // Creating two buddies
        buddyOne = new BuddyInfo("Buddy One", "Addr1", "123");
        buddyTwo = new BuddyInfo("Buddy Two", "Addr2", "1234");

        // Create the address book
        addressBook = new AddressBook();

        addressBook.addBuddy(buddyOne);
        addressBook.addBuddy(buddyTwo);

        addressBook = addressBookRepository.save(addressBook);
    }

    @Test
    public void testAddressBookCascadePersistence() {

        List<AddressBook> results = (List<AddressBook>) addressBookRepository.findAll();
        assertEquals(1, results.size());

        AddressBook addressBookResult = results.get(0);

        // Address book should be equivalent to the initial address book object
        assertEquals(addressBook, addressBookResult);
    }

    @Test
    public void testAddressBookFindByBuddyInfo() {

        List<AddressBook> results = addressBookRepository.findByBuddyInfoListContaining(buddyOne);
        assertEquals(1, results.size());

        AddressBook addressBookResult = results.get(0);

        // Address book should be equivalent to the initial address book object
        assertEquals(addressBook, addressBookResult);
    }

    @Test
    public void testBuddyFindByName() {
        List<BuddyInfo> results = buddyInfoRepository.findByName(buddyOne.getName());

        assertEquals(1, results.size());
        assertTrue(results.contains(buddyOne));
    }

    /*@Test
    public void testBuddyFindByAddress() {
        List<BuddyInfo> results = buddyInfoRepository.findByAddress(buddyOne.getAddress());

        assertEquals(1, results.size());
        assertTrue(results.contains(buddyOne));
    }*/

    @Test
    public void testBuddyFindByPhoneNumber() {
        List<BuddyInfo> results = buddyInfoRepository.findByPhoneNumber(buddyOne.getPhoneNumber());

        assertEquals(1, results.size());
        assertTrue(results.contains(buddyOne));
    }


    @Test
    public void testBuddyPersistence() {
        List<BuddyInfo> results = (ArrayList<BuddyInfo>) buddyInfoRepository.findAll();

        assertEquals(2, results.size());
        assertTrue(results.contains(buddyOne));
        assertTrue(results.contains(buddyTwo));

    }
}
