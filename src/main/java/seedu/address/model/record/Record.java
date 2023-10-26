package seedu.address.model.record;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.shared.DateTime;

/**
 * Record of condition of a patient and date and time in which a patient visits the doctor
 */
public class Record {

    private final List<Condition> conditions = new ArrayList<>();
    private final List<Medication> medications = new ArrayList<>();
    private final DateTime dateTime;

    /**
     * Constructs a record object
     */

    public Record(DateTime dateTime, List<Condition> conditions, List<Medication> medications) {
        requireAllNonNull(dateTime, conditions);
        this.dateTime = dateTime;
        this.conditions.addAll(conditions);
        this.medications.addAll(medications);
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public List<Condition> getConditions() {
        return Collections.unmodifiableList(conditions);
    }
    public List<Medication> getMedications() {
        return Collections.unmodifiableList(medications);
    }
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(dateTime, conditions, medications);
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
                && conditions.equals(otherRecord.conditions)
                && medications.equals(otherRecord.medications);
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("dateTime", dateTime)
                .add("conditions", conditions)
                .add("medications", medications)
                .toString();
    }

}
