package seedu.address.model.record;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.shared.DateTime;

public class DateTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTime(null));
    }
    @Test
    public void constructor_invalidDateTime_throwsIllegalArgumentException() {
        String invalidDateTime = "1800 20102023";
        assertThrows(IllegalArgumentException.class, () -> new DateTime(invalidDateTime));
    }
    @Test
    public void isValidDateTime_success() {
        String validDateTime = "09-09-2023 1800";
        assertTrue(DateTime.isValidDateTime(validDateTime));
    }
    @Test
    public void isValidDateTime_failure_throwsIllegalArgumentException() {
        String invalidDateTime = "1800 20102023";
        assertFalse(DateTime.isValidDateTime(invalidDateTime));
    }
}
