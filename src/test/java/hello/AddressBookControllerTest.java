package hello;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AddressBookControllerTest {

    private static final String ADDRESS_BOOKS_ROOT_PATH = "addressBooks";
    private static final String BUDDIES_ROOT_PATH = "buddies";
    private static final String BUDDY_INFO_LIST_PATH = "buddyInfoList";
    private static final String URI_LIST_CONTENT_TYPE = "text/uri-list";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private BuddyInfoRepository buddyInfoRepository;

    @Autowired
    private AddressBookRepository addressBookRepository;

    private BuddyInfo buddyInfo;
    private AddressBook addressBook;

    @Before
    public void setUp() {
        buddyInfo = new BuddyInfo("test_name", "test_addr", "test_phone");
        addressBook = new AddressBook();

        // Store buddy in database
        buddyInfoRepository.save(buddyInfo);
        addressBookRepository.save(addressBook);
    }

    @After
    public void tearDown() {
        addressBookRepository.deleteAll();
        buddyInfoRepository.deleteAll();
    }

    @Test
    public void createAddressBook() throws Exception {
        long count = addressBookRepository.count();

        mvc.perform(MockMvcRequestBuilders.post("/" + ADDRESS_BOOKS_ROOT_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isCreated());


        // We expect to find one more address book in the repository
        Assert.assertEquals(count + 1, addressBookRepository.count());
    }

    @Test
    public void deleteAddressBook() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/" + ADDRESS_BOOKS_ROOT_PATH + "/" + addressBook.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    public void addBuddy() throws Exception {
        // Get the uri for our test buddy
        String requestURL = mvc.perform(MockMvcRequestBuilders.get("/" + BUDDIES_ROOT_PATH + "/" + buddyInfo.getId()))
                .andReturn().getRequest().getRequestURL().toString();

        mvc.perform(MockMvcRequestBuilders.put("/" + ADDRESS_BOOKS_ROOT_PATH + "/" + addressBook.getId() + "/" + BUDDY_INFO_LIST_PATH)
                .contentType(URI_LIST_CONTENT_TYPE)
                .content(requestURL))
                .andExpect(status().isNoContent());

        addressBook = addressBookRepository.findById(addressBook.getId()).orElse(null);
        Assert.assertNotNull(addressBook);
        Assert.assertEquals(1, addressBook.size());
        Assert.assertTrue(addressBook.getBuddyInfoList().contains(buddyInfo));
    }

    @Test
    public void removeBuddy() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/" + ADDRESS_BOOKS_ROOT_PATH + "/" + addressBook.getId() + "/" + BUDDY_INFO_LIST_PATH + "/" + buddyInfo.getId()))
                .andExpect(status().isNoContent());

        addressBook = addressBookRepository.findById(addressBook.getId()).orElse(null);
        Assert.assertNotNull(addressBook);
        Assert.assertEquals(0, addressBook.size());
    }
}
