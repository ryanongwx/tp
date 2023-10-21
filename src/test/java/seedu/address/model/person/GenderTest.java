package seedu.address.model.person;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GenderTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Gender(null));
    }

    @Test
    public void constructor_invalidAge_throwsIllegalArgumentException() {
        String invalidGender = "C";
        assertThrows(IllegalArgumentException.class, () -> new Gender(invalidGender));
    }

    @Test
    public void isValidGender() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Gender.isValidGender(null));
    }
}
