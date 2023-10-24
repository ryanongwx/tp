package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONDITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.record.Condition;
import seedu.address.model.record.Record;
import seedu.address.model.record.UniqueRecordList;
import seedu.address.model.shared.DateTime;

/**
 * Edits the details of an existing record of a Person in the address book.
 */
public class EditRecordCommand extends Command {

    public static final String COMMAND_WORD = "editrecord";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a patient's record in the address book "
            + "by the patient's index number and the record's index number.\n"
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: PATIENT'S INDEX/RECORD INDEX (both must be a positive integer) "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_CONDITION + "CONDITION] " + "\n"
            + "Example: " + COMMAND_WORD + " 1/2 "
            + PREFIX_DATE + "21092023 1800 "
            + PREFIX_CONDITION + "Cold";

    public static final String MESSAGE_EDIT_RECORD_SUCCESS = "Edited record: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_RECORD = "This record already exists in the record of the patient.";

    private final Index patientIndex;
    private final Index recordIndex;
    private final EditRecordCommand.EditRecordDescriptor editRecordDescriptor;

    /**
     * @param patientIndex index of the person in the filtered person list to edit
     * @param recordIndex index of the record of the targeted patient
     * @param editRecordDescriptor details to edit the record with
     */
    public EditRecordCommand(Index patientIndex, Index recordIndex,
                             EditRecordCommand.EditRecordDescriptor editRecordDescriptor) {
        requireNonNull(patientIndex);
        requireNonNull(recordIndex);
        requireNonNull(editRecordDescriptor);

        this.patientIndex = patientIndex;
        this.recordIndex = recordIndex;
        this.editRecordDescriptor = new EditRecordCommand.EditRecordDescriptor(editRecordDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPersonList = model.getFilteredPersonList();

        if (patientIndex.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownPersonList.get(patientIndex.getZeroBased());

        UniqueRecordList uniqueRecordList = personToEdit.getRecords();
        List<Record> lastShownRecordList = uniqueRecordList.getRecordList();

        if (recordIndex.getZeroBased() >= lastShownRecordList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RECORD_DISPLAYED_INDEX);
        }

        Record recordToEdit = lastShownRecordList.get(recordIndex.getZeroBased());
        Record editedRecord = createEditedRecord(recordToEdit, editRecordDescriptor);

        if (!recordToEdit.equals(editedRecord) && uniqueRecordList.contains(editedRecord)) {
            throw new CommandException(MESSAGE_DUPLICATE_RECORD);
        }

        uniqueRecordList.setRecord(recordToEdit, editedRecord);
        Person editedPerson = createdEditedPerson(personToEdit, uniqueRecordList);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(EditCommand.MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_RECORD_SUCCESS,
                Messages.format(editedRecord, personToEdit)));
    }

    /**
     * Creates and returns a {@code Record} with the details of {@code recordToEdit}
     * edited with {@code editRecordDescriptor}.
     */
    private static Record createEditedRecord(Record recordToEdit,
                                             EditRecordCommand.EditRecordDescriptor editRecordDescriptor) {
        assert recordToEdit != null;

        DateTime updatedDateTime = editRecordDescriptor.getDateTime().orElse(recordToEdit.getDateTime());
        List<Condition> updatedConditions = editRecordDescriptor.getConditions().orElse(recordToEdit.getConditions());

        return new Record(updatedDateTime, updatedConditions);
    }

    private static Person createdEditedPerson(Person personToEdit, UniqueRecordList records) {
        assert personToEdit != null;
        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getEmail(),
                personToEdit.getPhone(), personToEdit.getGender(),
                personToEdit.getAge(), personToEdit.getBloodType(),
                personToEdit.getAllergies(), records,
                personToEdit.getAppointments(), personToEdit.isPinned());

        return editedPerson;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditRecordCommand)) {
            return false;
        }

        EditRecordCommand otherEditRecordCommand = (EditRecordCommand) other;
        return patientIndex.equals(otherEditRecordCommand.patientIndex)
                && recordIndex.equals(otherEditRecordCommand.recordIndex)
                && editRecordDescriptor.equals(otherEditRecordCommand.editRecordDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("patientIndex", patientIndex)
                .add("recordIndex", recordIndex)
                .add("editRecordDescriptor", editRecordDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the record with. Each non-empty field value will replace the
     * corresponding field value of the record.
     */
    public static class EditRecordDescriptor {
        private DateTime dateTime;
        private List<Condition> conditions;

        public EditRecordDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code conditionss} is used internally.
         */
        public EditRecordDescriptor(EditRecordCommand.EditRecordDescriptor toCopy) {
            setDateTime(toCopy.dateTime);
            setConditions(toCopy.conditions);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(dateTime, conditions);
        }

        public void setDateTime(DateTime dateTime) {
            this.dateTime = dateTime;
        }

        public Optional<DateTime> getDateTime() {
            return Optional.ofNullable(dateTime);
        }

        /**
         * Sets {@code conditions} to this record's {@code conditions}.
         * A defensive copy of {@code condtions} is used internally.
         */
        public void setConditions(List<Condition> conditions) {
            this.conditions = (conditions != null) ? new ArrayList<>(conditions) : null;
        }

        /**
         * Returns an unmodifiable condition list, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code conditions} is null.
         */
        public Optional<List<Condition>> getConditions() {
            return (conditions != null) ? Optional.of(Collections.unmodifiableList(conditions)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditRecordCommand.EditRecordDescriptor)) {
                return false;
            }

            EditRecordCommand.EditRecordDescriptor otherEditRecordDescriptor =
                    (EditRecordCommand.EditRecordDescriptor) other;
            return Objects.equals(dateTime, otherEditRecordDescriptor.dateTime)
                    && Objects.equals(conditions, otherEditRecordDescriptor.conditions);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("dateTime", dateTime)
                    .add("conditions", conditions)
                    .toString();
        }
    }
}
