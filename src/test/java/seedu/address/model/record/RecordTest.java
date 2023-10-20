package seedu.address.model.record;

import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.shared.DateTime;

public class RecordTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Record(null, null));
        assertThrows(NullPointerException.class, () -> new Record(new DateTime("09102023 1800"), null));
        List<Condition> validConditions = new ArrayList<>(Arrays.asList(new Condition("Fever"), new Condition("Cold")));
        assertThrows(NullPointerException.class, () -> new Record(null, validConditions));
    }
}
