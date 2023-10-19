package seedu.address.model.record;

import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class DateTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTime((String) null));
        assertThrows(NullPointerException.class, () -> new DateTime((LocalDateTime) null));
    }

    @Test
    public void constructor_invalidAge_throwsIllegalArgumentException() {
        String invalidDateTime = "1800 20102023";
        assertThrows(DateTimeParseException.class, () -> new DateTime(invalidDateTime));
    }

}
