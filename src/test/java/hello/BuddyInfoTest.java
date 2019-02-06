package hello;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BuddyInfoTest {

    private BuddyInfo buddyInfo;
    private static String TEST_NAME = "NAME";
    private static String TEST_ADDRESS = "ADDR";
    private static String TEST_PHONE = "PHONE";

    @Before
    public void setUp() {
        buddyInfo = new BuddyInfo(TEST_NAME, TEST_ADDRESS, TEST_PHONE);
    }

    @Test
    public void getName() {
        assertEquals(TEST_NAME, buddyInfo.getName());
    }

    @Test
    public void setName() {
        String newName = "New Name";
        buddyInfo.setName(newName);
        assertEquals(newName, buddyInfo.getName());
    }

    @Test
    public void getAddress() {
        assertEquals(TEST_ADDRESS, buddyInfo.getAddress());
    }

    @Test
    public void setAddress() {
        String newAddr = "New Address";
        buddyInfo.setAddress(newAddr);
        assertEquals(newAddr, buddyInfo.getAddress());
    }

    @Test
    public void getPhoneNumber() {
        assertEquals(TEST_PHONE, buddyInfo.getPhoneNumber());
    }

    @Test
    public void setPhoneNumber() {
        String newPhone = "New Phone";
        buddyInfo.setPhoneNumber(newPhone);
        assertEquals(newPhone, buddyInfo.getPhoneNumber());
    }

    @Test
    public void testToString() {
        String buddyStr = buddyInfo.toString();
        assertTrue(buddyStr.contains(buddyInfo.getName()));
        assertTrue(buddyStr.contains(buddyInfo.getAddress()));
        assertTrue(buddyStr.contains(buddyInfo.getPhoneNumber()));
    }


    @Test
    public void testEquals() {
        BuddyInfo buddyInfoEqualsWithoutId = new BuddyInfo(buddyInfo.getName(), buddyInfo.getAddress(), buddyInfo.getPhoneNumber());
        BuddyInfo buddyInfoNotEquals = new BuddyInfo(buddyInfo.getName(), "", "");

        assertEquals(buddyInfo, buddyInfoEqualsWithoutId);
        assertNotEquals(buddyInfo, buddyInfoNotEquals);
    }
}