package seedu.address.model.record;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.shared.DateTime;

/**
 * Record of condition of a patient and date and time in which a patient visits
 * the doctor along with an associated file path.
 */
public class Record {

    private final List<Condition> conditions = new ArrayList<>();
    private final DateTime dateTime;
    private Path filePath; // This should now be mutable

    /**
     * Constructs a record object
     */
    public Record(DateTime dateTime, List<Condition> conditions, Path filePath) {
        requireAllNonNull(dateTime, conditions);
        this.dateTime = dateTime;
        this.conditions.addAll(conditions);
        this.filePath = filePath;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public List<Condition> getConditions() {
        return Collections.unmodifiableList(conditions);
    }

    public Path getFilePath() {
        return filePath; // Getter for the file path
    }

    // Set the filePath. This is the new setter method
    public void setFilePath(Path filePath) {
        requireAllNonNull(filePath); // Ensure the provided filePath is not null
        this.filePath = filePath;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, conditions, filePath);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Record)) {
            return false;
        }

        Record otherRecord = (Record) other;
        return dateTime.equals(otherRecord.dateTime)
                && conditions.equals(otherRecord.conditions)
                && Objects.equals(filePath, otherRecord.filePath); // Use Objects.equals() which handles nulls
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("dateTime", dateTime)
                .add("conditions", conditions)
                .add("filePath", filePath)
                .toString();
    }
}
