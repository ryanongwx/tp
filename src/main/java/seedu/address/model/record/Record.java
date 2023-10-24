package seedu.address.model.record;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.EditRecordCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.shared.DateTime;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;

/**
 * Record of condition of a patient and date and time in which a patient visits
 * the doctor
 */
public class Record {

    private final List<Condition> conditions = new ArrayList<>();
    private final DateTime dateTime;
    private Path filePath;
    private final Integer personIndex;

    /**
     * Constructs a record object
     */

    public Record(DateTime dateTime, List<Condition> conditions, Path filePath, Integer personIndex) {
        requireAllNonNull(dateTime, conditions);
        this.dateTime = dateTime;
        this.conditions.addAll(conditions);
        this.filePath = filePath;
        this.personIndex = personIndex;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public int getPersonIndex() {
        return personIndex;
    }

    public List<Condition> getConditions() {
        return Collections.unmodifiableList(conditions);
    }

    public Path getFilePath() {
        return filePath; // Getter for the file path
    }

    // Set the filePath. This is the new setter method
    public void setFilePath(Path filePath, int displayedIndex) {
        requireAllNonNull(filePath); // Ensure the provided filePath is not null
        this.filePath = filePath;
        Model model = ModelManager.getInstance();
        EditRecordCommand.EditRecordDescriptor editRecordDescriptor = new EditRecordCommand.EditRecordDescriptor();
        editRecordDescriptor.setFilePath(filePath);
        Command command = new EditRecordCommand(Index.fromZeroBased(personIndex),
                Index.fromZeroBased(displayedIndex - 1),
                editRecordDescriptor);
        try {
            command.execute(model);
            Storage storage = StorageManager.getInstance();
            storage.saveAddressBook(model.getAddressBook());
        } catch (Exception c) {
            c.printStackTrace();
        }
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(dateTime, conditions, filePath, personIndex);
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
                && Objects.equals(filePath, otherRecord.filePath)
                && personIndex.equals(otherRecord.personIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("dateTime", dateTime)
                .add("conditions", conditions)
                .add("filePath", filePath)
                .add("personIndex", personIndex)
                .toString();
    }

}
