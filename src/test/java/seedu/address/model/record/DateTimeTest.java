package seedu.address.model.record;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DateTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTime((String) null));
        assertThrows(NullPointerException.class, () -> new DateTime((LocalDateTime) null));
    }
    @Test
    public void constructor_invalidDateTime_throwsIllegalArgumentException() {
        String invalidDateTime = "1800 20102023";
        assertThrows(IllegalArgumentException.class, () -> new DateTime(invalidDateTime));
    }
    @Test
    public void isValidDateTime_success() {
        String validDateTime = "09092023 1800";
        assertTrue(DateTime.isValidDateTime(validDateTime));
    }
    @Test
    public void isValidDateTime_failure_throwsIllegalArgumentException() {
        String invalidDateTime = "1800 20102023";
        assertFalse(DateTime.isValidDateTime(invalidDateTime));
    }
}
