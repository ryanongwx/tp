package seedu.address.testutil;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.storage.Storage;

/**
 * A mock version of the Storage used for testing.
 * It allows the testing classes to access a mock Storage for testing purposes,
 * without interacting with the actual Storage layer.
 */
public class MockStorage implements Storage {
    private boolean saveAddressBookCalled = false;

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) {
        saveAddressBookCalled = true;
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveAddressBook'");
    }

    public boolean wasSaveAddressBookCalled() {
        return saveAddressBookCalled;
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataLoadingException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readAddressBook'");
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readAddressBook'");
    }

    @Override
    public Path getUserPrefsFilePath() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserPrefsFilePath'");
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readUserPrefs'");
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveUserPrefs'");
    }

    @Override
    public Path getAddressBookFilePath() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAddressBookFilePath'");
    }

    // Implement other methods as necessary
}
