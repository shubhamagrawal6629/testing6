package hello;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AddressBook {

    @Id
    @GeneratedValue
    private Long id = null;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderColumn
    private List<BuddyInfo> buddyInfoList;

    /**
     * Creates a new address book
     */
    public AddressBook() {
        this.buddyInfoList = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    /**
     * @param id Set the ID for database application.persistence
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return The buddy info list
     */
    public List<BuddyInfo> getBuddyInfoList() {
        return buddyInfoList;
    }

    /**
     * Set the buddy info list
     *
     * @param buddyInfoList
     */
    public void setBuddyInfoList(List<BuddyInfo> buddyInfoList) {
        this.buddyInfoList = buddyInfoList;
    }

    /**
     * Add a buddy to the address book
     *
     * @param buddyInfo The buddy info to add. If this is null, nothing is added to the address book.
     */
    public void addBuddy(BuddyInfo buddyInfo) {
        if (buddyInfo == null)
            return;

        this.buddyInfoList.add(buddyInfo);
    }

    /**
     * Remove a buddy at a given index
     *
     * @param buddyInfo The buddy info object to be removed. Does nothing if the object does not exist.
     */
    public void removeBuddy(BuddyInfo buddyInfo) {
        this.buddyInfoList.remove(buddyInfo);
    }

    /**
     * Remove a buddy at a given index
     *
     * @param index The index of the buddy to be removed
     */
    public void removeBuddy(int index) {
        this.buddyInfoList.remove(index);
    }

    /**
     * @return The size of the buddy list
     */
    public int size() {
        return this.buddyInfoList.size();
    }

    /**
     * @return The string representation of the address book
     */
    public String toString() {
        return buddyInfoList.toString();
    }

    /**
     * Checks two address books for equivalence
     *
     * @param o The other address book
     * @return True if both address books are equivalent
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof AddressBook))
            return false;

        AddressBook otherBook = (AddressBook) o;

        // Delegate equals to the array list if the object has no ID
        return this.buddyInfoList.equals(otherBook.buddyInfoList);
    }
}
