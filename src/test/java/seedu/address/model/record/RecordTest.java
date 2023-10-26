package seedu.address.model.record;

import static seedu.address.testutil.Assert.assertThrows;

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
}
