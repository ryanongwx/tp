package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Optional;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonAddressBookStorage implements AddressBookStorage {

    protected static final String INIT_VECTOR = "a5s8d2e9w4z6x3c7";
    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);
    private static final String SECRET_KEY = "Xp2s5v8y/B?E(H+M";

    private Path filePath;

    /**
     * Constructs a new JsonAddressBookStorage instance with a given filePath.
     *
     * @param filePath The path to the file.
     */
    public JsonAddressBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Encrypts the given string using AES encryption.
     *
     * @param value The string to be encrypted.
     * @return The encrypted string.
     */
    protected String encrypt(String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Decrypts the given encrypted string using AES decryption.
     *
     * @param encrypted The string to be decrypted.
     * @return The decrypted string.
     */
    private String decrypt(String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Returns the file path where the AddressBook is stored.
     *
     * @return The file path.
     */
    public Path getAddressBookFilePath() {
        return filePath;
    }

    /**
     * Reads the AddressBook from the default file path.
     *
     * @return An Optional containing the AddressBook if it exists, or empty
     *         otherwise.
     * @throws DataLoadingException if there was an error reading the AddressBook.
     */
    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException {
        return readAddressBook(filePath);
    }

    /**
     * Reads the AddressBook from the specified file path.
     *
     * @param filePath The path to the file.
     * @return An Optional containing the AddressBook if it exists, or empty
     *         otherwise.
     * @throws DataLoadingException if there was an error reading the AddressBook.
     */
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        if (!FileUtil.isFileExists(filePath)) {
            return Optional.empty();
        }

        try {
            String encryptedData = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
            String decryptedData = decrypt(encryptedData);

            JsonSerializableAddressBook jsonAddressBook = JsonUtil.fromJsonString(
                    decryptedData, JsonSerializableAddressBook.class);

            if (jsonAddressBook == null) {
                return Optional.empty();
            }

            return Optional.of(jsonAddressBook.toModelType());

        } catch (Exception e) {
            throw new DataLoadingException(e);
        }
    }

    /**
     * Saves the AddressBook to the default file path.
     *
     * @param addressBook The AddressBook to be saved.
     * @throws IOException if there was an error writing to the file.
     */
    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, filePath);
    }

    /**
     * Saves the AddressBook to the specified file path.
     *
     * @param addressBook The AddressBook to be saved.
     * @param filePath    The path to the file.
     * @throws IOException if there was an error writing to the file.
     */
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        String jsonData = JsonUtil.toJsonString(new JsonSerializableAddressBook(addressBook));
        String encryptedData = encrypt(jsonData);

        Files.write(filePath, encryptedData.getBytes(StandardCharsets.UTF_8));
    }

}
