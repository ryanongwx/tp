package seedu.address.model.record;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class ConditionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Condition(null));
    }
    @Test
    public void isValidCondition_success() {
        String validCondition = "Allergic reaction";
        assertTrue(Condition.isValidCondition(validCondition));
    }
    @Test
    public void isValidCondtion_failure() {
        String inValidCondition = "+";
        assertFalse(Condition.isValidCondition(inValidCondition));
    }

}
