package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.DESC_FIRST_REC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONDITION_DIARRHEA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_SLEEP_STUDY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_THYROID_CHECK;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_RECORD;
import static seedu.address.testutil.TypicalPersonsEditable.getTypicalAddressBookEditable;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.record.Record;
import seedu.address.model.record.UniqueRecordList;
import seedu.address.testutil.EditRecordDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.RecordBuilder;

public class EditRecordCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookEditable(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UniqueRecordList uniqueRecordList = personToEdit.getRecords();
        Record recordToEdit = uniqueRecordList.getRecordList().get(INDEX_FIRST_RECORD.getZeroBased());
        Record editedRecord = new RecordBuilder(recordToEdit)
                .withDateTime(VALID_DATETIME_THYROID_CHECK)
                .withConditions(VALID_CONDITION_DIARRHEA).withPersonIndex(0).build();

        Person editedPerson = new PersonBuilder(personToEdit).withRecords(uniqueRecordList).build();
        EditRecordCommand.EditRecordDescriptor descriptor = new EditRecordDescriptorBuilder(recordToEdit)
                .withDateTime(VALID_DATETIME_THYROID_CHECK)
                .withConditions(VALID_CONDITION_DIARRHEA).build();
        EditRecordCommand editRecordCommand = new EditRecordCommand(INDEX_FIRST_PERSON, INDEX_FIRST_RECORD, descriptor);

        String expectedMessage = String.format(EditRecordCommand.MESSAGE_EDIT_RECORD_SUCCESS,
                Messages.format(editedRecord, editedPerson));
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editRecordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UniqueRecordList uniqueRecordList = firstPerson.getRecords();
        Record recordToEdit = uniqueRecordList.getRecordList().get(INDEX_FIRST_RECORD.getZeroBased());
        Record editedRecord = new RecordBuilder(recordToEdit)
                .withDateTime(VALID_DATETIME_THYROID_CHECK).build();

        PersonBuilder personInList = new PersonBuilder(firstPerson);
        Person editedPerson = personInList.withRecords(uniqueRecordList).build();
        EditRecordCommand.EditRecordDescriptor descriptor = new EditRecordDescriptorBuilder()
                .withDateTime(VALID_DATETIME_THYROID_CHECK).build();
        EditRecordCommand editRecordCommand = new EditRecordCommand(INDEX_FIRST_PERSON, INDEX_FIRST_RECORD, descriptor);

        String expectedMessage = String.format(EditRecordCommand.MESSAGE_EDIT_RECORD_SUCCESS, Messages.format(
                editedRecord, editedPerson));
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editRecordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_failure() {
        EditRecordCommand editRecordCommand = new EditRecordCommand(INDEX_FIRST_PERSON, INDEX_FIRST_RECORD,
                new EditRecordCommand.EditRecordDescriptor());

        assertCommandFailure(editRecordCommand, model, EditRecordCommand.MESSAGE_DUPLICATE_RECORD);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UniqueRecordList uniqueRecordList = personInFilteredList.getRecords();
        Record recordToEdit = uniqueRecordList.getRecordList().get(INDEX_FIRST_RECORD.getZeroBased());
        Record editedRecord = new RecordBuilder(recordToEdit).withDateTime(VALID_DATETIME_SLEEP_STUDY)
                .withPersonIndex(0).build();
        Person editedPerson = new PersonBuilder(personInFilteredList).withRecords(uniqueRecordList).build();

        EditRecordCommand editRecordCommand = new EditRecordCommand(INDEX_FIRST_PERSON, INDEX_FIRST_RECORD,
                new EditRecordDescriptorBuilder().withDateTime(VALID_DATETIME_SLEEP_STUDY).withPatientIndex(0).build());
        String expectedMessage = String.format(EditRecordCommand.MESSAGE_EDIT_RECORD_SUCCESS,
                Messages.format(editedRecord, editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editRecordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateRecordUnfilteredList_failure() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UniqueRecordList uniqueRecordList = firstPerson.getRecords();
        Record firstRecord = uniqueRecordList.getRecordList().get(INDEX_FIRST_RECORD.getZeroBased());
        EditRecordCommand.EditRecordDescriptor descriptor = new EditRecordDescriptorBuilder(firstRecord).build();
        EditRecordCommand editRecordCommand = new EditRecordCommand(INDEX_FIRST_PERSON,
                INDEX_FIRST_RECORD, descriptor);
        assertCommandFailure(editRecordCommand, model, EditRecordCommand.MESSAGE_DUPLICATE_RECORD);
    }

    @Test
    public void execute_duplicateRecordFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInList = model.getAddressBook().getPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UniqueRecordList uniqueRecordList = personInList.getRecords();
        Record firstRecord = uniqueRecordList.getRecordList().get(INDEX_FIRST_RECORD.getZeroBased());
        EditRecordCommand editRecordCommand = new EditRecordCommand(INDEX_FIRST_PERSON, INDEX_FIRST_RECORD,
                new EditRecordDescriptorBuilder(firstRecord).build());

        assertCommandFailure(editRecordCommand, model, EditRecordCommand.MESSAGE_DUPLICATE_RECORD);
    }

    @Test
    public void execute_invalidRecordIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromZeroBased(model.getFilteredPersonList().get(
                INDEX_FIRST_PERSON.getZeroBased()).getRecords().getRecordList().size());
        EditRecordCommand.EditRecordDescriptor descriptor = new EditRecordDescriptorBuilder()
                .withDateTime(VALID_DATETIME_THYROID_CHECK).build();
        EditRecordCommand editRecordCommand = new EditRecordCommand(INDEX_FIRST_PERSON, outOfBoundIndex, descriptor);

        assertCommandFailure(editRecordCommand, model, Messages.MESSAGE_INVALID_RECORD_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditRecordCommand.EditRecordDescriptor descriptor = new EditRecordDescriptorBuilder()
                .withDateTime(VALID_DATETIME_THYROID_CHECK).build();
        EditRecordCommand editRecordCommand = new EditRecordCommand(outOfBoundIndex, INDEX_FIRST_RECORD, descriptor);

        assertCommandFailure(editRecordCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void toStringTest() {
        EditRecordCommand.EditRecordDescriptor editedFirstRecord = new EditRecordDescriptorBuilder(DESC_FIRST_REC)
                .withDateTime(VALID_DATETIME_THYROID_CHECK).build();
        EditRecordCommand editRecordCommand = new EditRecordCommand(INDEX_FIRST_PERSON,
                INDEX_FIRST_RECORD, editedFirstRecord);
        String expected = EditRecordCommand.class.getCanonicalName() + "{patientIndex=" + INDEX_FIRST_PERSON + ", "
                + "recordIndex=" + INDEX_FIRST_RECORD + ", "
                + "editRecordDescriptor=" + editedFirstRecord + "}";

        assertEquals(expected, editRecordCommand.toString());
    }

}
