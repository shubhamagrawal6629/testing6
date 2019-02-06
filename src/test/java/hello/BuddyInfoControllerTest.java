package hello;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public class BuddyInfoControllerTest {

    private static final String BUDDIES_ROOT_PATH = "buddies";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private BuddyInfoRepository buddyInfoRepository;

    private BuddyInfo buddyInfo;
    private BuddyInfo buddyInfoNew;

    private ObjectMapper mapper;

    @Before
    public void setUp() {
        buddyInfo = new BuddyInfo("test_name", "test_addr", "test_phone");
        buddyInfoNew = new BuddyInfo("test_name_new", "test_addr_new", "test_phone_new");

        mapper = new ObjectMapper();

        // Store buddy in database
        buddyInfo = buddyInfoRepository.save(buddyInfo);
    }

    @After
    public void tearDown() {
        buddyInfoRepository.deleteAll();
    }

    @Test
    public void createBuddy() throws Exception {
        String buddyJsonStr = mapper.writeValueAsString(buddyInfoNew);

        mvc.perform(MockMvcRequestBuilders.post("/" + BUDDIES_ROOT_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(buddyJsonStr))
                .andExpect(status().isCreated());

        // We expect to find one more address book in the repository
        Assert.assertEquals(1, buddyInfoRepository.findByName(buddyInfoNew.getName()).size());
    }

    @Test
    public void deleteAddressBook() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/" + BUDDIES_ROOT_PATH + "/" + buddyInfo.getId()))
                .andExpect(status().isNoContent());
    }

}
