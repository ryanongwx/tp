package seedu.address.testutil;

import java.nio.file.Path;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import seedu.address.storage.JsonAddressBookStorage;

/**
 * A mock implementation of the {@code JsonAddressBookStorage} class that uses
 * an invalid secret key
 * for encryption. This class is used for testing scenarios where the secret key
 * is incorrect.
 * <p>
 * This class overrides the {@code encrypt} method to use a predefined invalid
 * secret key and initialization vector.
 * </p>
 */
public class MockInvalidKeyJsonAddressBookStorage extends JsonAddressBookStorage {
    private static final String MOCK_INIT_VECTOR = "a5s8d2e9w4z6x3c7";
    private static final String MOCK_SECRET_KEY = "IncorrectSizeKeyljkh";

    /**
     * Constructs a new instance of {@code MockInvalidKeyJsonAddressBookStorage}
     * with the specified file path.
     *
     * @param filePath The path to the storage file.
     */
    public MockInvalidKeyJsonAddressBookStorage(Path filePath) {
        super(filePath);
    }

    /**
     * Overrides the encryption method to use the mock secret key and initialization
     * vector.
     *
     * @param value The string value to be encrypted.
     * @return The encrypted value as a Base64 encoded string, or {@code null} if an
     *         error occurs.
     */
    @Override
    protected String encrypt(String value) {
        // Use the mock key and init vector
        try {
            IvParameterSpec iv = new IvParameterSpec(MOCK_INIT_VECTOR.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(MOCK_SECRET_KEY.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
