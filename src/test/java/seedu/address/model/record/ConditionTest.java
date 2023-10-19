package seedu.address.model.record;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class ConditionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Condition(null));
    }

}
