package seedu.address.model.record;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRecords.FEVER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.shared.DateTime;

public class RecordTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Record(null, null, null));
        assertThrows(NullPointerException.class, () -> new Record(new DateTime("09-10-2023 1800"),
                null, null));
        List<Condition> validConditions = new ArrayList<>(Arrays.asList(new Condition("Fever"), new Condition("Cold")));
        List<Medication> validMedications = new ArrayList<>(Arrays.asList(new Medication("Tylenol"),
                new Medication("Ibuprofen")));
        assertThrows(NullPointerException.class, () -> new Record(null, validConditions, validMedications));
    }

    @Test
    public void toString_success() {
        String expected = Record.class.getCanonicalName()
                + "{dateTime=" + FEVER.getDateTime()
                + ", conditions=" + FEVER.getConditions()
                + ", medications=" + FEVER.getMedications()
                + "}";

        assertEquals(expected, FEVER.toString());
    }
}
