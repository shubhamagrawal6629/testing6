package hello;

import javax.persistence.*;

@Entity
public class BuddyInfo {

    @Id
    @GeneratedValue
    private Long id = null;

    private String name;
    private String address;
    private String phoneNumber;

    /**
     * Create a new Buddy Info
     *
     * @param name        The name of the buddy
     * @param address     The address of the buddy
     * @param phoneNumber The phone number of the buddy
     */
    public BuddyInfo(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public BuddyInfo() {
        this("", "", "");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return The name of the buddy
     */
    public String getName() {
        return name;
    }

    /**
     * @return The address of the buddy
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return The number of the buddy
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set the name of the buddy
     *
     * @param name The new name
     */
    public void setName(String name) {
        if (name == null)
            return;

        this.name = name;
    }

    /**
     * Set the address of the buddy
     *
     * @param address The new address
     */
    public void setAddress(String address) {
        if (address == null)
            return;

        this.address = address;
    }

    /**
     * Set the phone number of the buddy
     *
     * @param phoneNumber The new number
     */
    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null)
            return;

        this.phoneNumber = phoneNumber;
    }

    /**
     * @return The string representation of the object
     */
    @Override
    public String toString() {
        // Print the ID if it is not null
        String idStr = this.id != null ? "[ID: " + this.id + "]" : "";
        return "Buddy " + idStr + ": " + this.name + " -> Address: " + this.address + " -> Phone: " + this.phoneNumber;
    }

    /**
     * Compares with the object IDs if non-null. Otherwise, the class information is compared (name, etc.)
     *
     * @param o The other object to check for equivalence
     * @return True if both objects are equivalent.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof BuddyInfo))
            return false;

        BuddyInfo otherBuddy = (BuddyInfo) o;

        return this.name.equals(otherBuddy.name)
                && this.phoneNumber.equals(otherBuddy.phoneNumber)
                && this.address.equals(otherBuddy.address);

    }
}
