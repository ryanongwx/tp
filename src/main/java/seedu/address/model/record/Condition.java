package seedu.address.model.record;

import seedu.address.model.person.Allergy;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Condition {

    public static final String MESSAGE_CONSTRAINTS =
            "Condition should be alphamnumeric";

    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String condition;

    /**
     * Constructs a {@code Condition}.
     *
     * @param condition A valid condition name.
     */
    public Condition(String condition) {
        requireNonNull(condition);
        checkArgument(isValidCondition(condition), MESSAGE_CONSTRAINTS);
        this.condition = condition;
    }

    public static boolean isValidCondition(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Condition)) {
            return false;
        }

        Condition otherCondition = (Condition) other;
        return condition.equals(otherCondition.condition);
    }

    @Override
    public int hashCode() {
        return condition.hashCode();
    }

    @Override
    public String toString() {
        return "[" + condition + "]";
    }


}
