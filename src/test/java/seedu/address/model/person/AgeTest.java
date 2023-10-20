package seedu.address.model.person;


import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AgeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Age(null));
    }

    @Test
    public void constructor_invalidAge_throwsIllegalArgumentException() {
        Integer invalidAge = -1;
        assertThrows(IllegalArgumentException.class, () -> new Age(invalidAge));

    }

    @Test
    public void isValidAge() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Age.isValidAge(null));
    }

}
