package seedu.address.model.person;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BloodTypeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new BloodType(null));
    }

    @Test
    public void constructor_invalidAge_throwsIllegalArgumentException() {
        String invalidBloodType = "C";
        assertThrows(IllegalArgumentException.class, () -> new BloodType(invalidBloodType));
    }

    @Test
    public void isValidBloodType() {
        // null tag name
        assertThrows(NullPointerException.class, () -> BloodType.isValidBloodType(null));
    }
}
