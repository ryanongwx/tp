package seedu.address.testutil;

import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.UserPrefsStorage;

/**
 * A mock version of the StorageManager used for testing.
 * It allows the testing classes to access a mock storage for testing purposes,
 * without interacting with the actual storage layer.
 */
public class MockStorageManager extends StorageManager {

    private final MockStorage mockStorage = new MockStorage();

    /**
     * Constructs a {@code MockStorageManager} with the given
     * {@code AddressBookStorage} and {@code UserPrefsStorage}.
     *
     * @param addressBookStorage the address book storage used by this manager.
     * @param userPrefsStorage   the user preferences storage used by this manager.
     */
    public MockStorageManager(AddressBookStorage addressBookStorage, UserPrefsStorage userPrefsStorage) {
        super(addressBookStorage, userPrefsStorage);
    }

    /**
     * Gets the storage associated with this manager.
     *
     * @return the mock storage.
     */
    public Storage getStorage() {
        return mockStorage;
    }

    /**
     * Gets the mock storage used by this manager.
     *
     * @return the mock storage.
     */
    public MockStorage getMockStorage() {
        return mockStorage;
    }
}
