package seedu.address.model.shared;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTime(null));
    }

    @Test
    public void constructor_invalidDateTime_throwsIllegalArgumentException() {
        String invalidDateTime = "";
        assertThrows(IllegalArgumentException.class, () -> new DateTime(invalidDateTime));
    }

    @Test
    public void isValidDateTime() {
        // null DateTime
        assertThrows(NullPointerException.class, () -> DateTime.isValidDateTime(null));

        // in21-10-2001 1900
        assertFalse(DateTime.isValidDateTime("")); // empty string
        assertFalse(DateTime.isValidDateTime(" ")); // spaces only
        assertFalse(DateTime.isValidDateTime("^")); // only non-alphanumeric characters
        assertFalse(DateTime.isValidDateTime("21-10-2001 1900h")); // contains alphabet characters
        assertFalse(DateTime.isValidDateTime("21-10-2001 1900*")); // contains non-alphanumeric characters
        assertFalse(DateTime.isValidDateTime("1900 21-10-2001")); // wrong format
        assertFalse(DateTime.isValidDateTime("21/10/2001 1900")); // wrong format
        assertFalse(DateTime.isValidDateTime("21-10-2001")); // missing time
        assertFalse(DateTime.isValidDateTime("1900")); // missing date

        // 21-10-2001 1900
        assertTrue(DateTime.isValidDateTime("21-10-2001 1900")); // alphabets only
    }

    @Test
    public void equals() {
        DateTime dateTime = new DateTime("21-10-2001 1900");

        // same values -> returns true
        assertTrue(dateTime.equals(new DateTime("21-10-2001 1900")));

        // same object -> returns true
        assertTrue(dateTime.equals(dateTime));

        // null -> returns false
        assertFalse(dateTime.equals(null));

        // different types -> returns false
        assertFalse(dateTime.equals(5.0f));

        // different values -> returns false
        assertFalse(dateTime.equals(new DateTime("22-10-2001 1900")));
    }
}
