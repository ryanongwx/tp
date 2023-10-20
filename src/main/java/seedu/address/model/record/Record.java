package seedu.address.model.record;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.shared.DateTime;

/**
 * Record of condition of a patient and date and time in which a patient visits the doctor
 */
public class Record {

    private final List<Condition> conditions = new ArrayList<>();
    private final DateTime dateTime;

    /**
     * Constructs a record object
     */

    public Record(DateTime dateTime, List<Condition> conditions) {
        requireAllNonNull(dateTime, conditions);
        this.dateTime = dateTime;
        this.conditions.addAll(conditions);
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public List<Condition> getConditions() {
        return Collections.unmodifiableList(conditions);
    }
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(dateTime, conditions);
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Record)) {
            return false;
        }

        Record otherRecord = (Record) other;
        return dateTime.equals(otherRecord.dateTime)
                && conditions.equals(otherRecord.conditions);
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("dateTime", dateTime)
                .add("conditions", conditions)
                .toString();
    }

}
