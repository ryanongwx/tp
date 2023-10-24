package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_CONDITION_DIARRHEA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_THYROID_CHECK;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_RECORD;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_RECORD;
import static seedu.address.testutil.TypicalPersonsEditable.getTypicalAddressBook;

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

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        UniqueRecordList uniqueRecordList = personToEdit.getRecords();
        Record recordToEdit = uniqueRecordList.getRecordList().get(INDEX_FIRST_RECORD.getZeroBased());
        Record editedRecord = new RecordBuilder(recordToEdit)
                .withDateTime(VALID_DATETIME_THYROID_CHECK)
                .withConditions(VALID_CONDITION_DIARRHEA).build();
        uniqueRecordList.setRecord(recordToEdit, editedRecord);

        Person editedPerson = new PersonBuilder(personToEdit).withRecords(uniqueRecordList).build();

        EditRecordCommand.EditRecordDescriptor descriptor = new EditRecordDescriptorBuilder(recordToEdit)
                .withDateTime(VALID_DATETIME_THYROID_CHECK)
                .withConditions(VALID_CONDITION_DIARRHEA).build();
        EditRecordCommand editRecordCommand = new EditRecordCommand(INDEX_FIRST_PERSON, INDEX_FIRST_RECORD, descriptor);

        String expectedMessage = String.format(EditRecordCommand.MESSAGE_EDIT_RECORD_SUCCESS,
                Messages.format(editedRecord, editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(personToEdit, editedPerson);

        assertCommandSuccess(editRecordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        UniqueRecordList uniqueRecordList = firstPerson.getRecords();
        Record recordToEdit = uniqueRecordList.getRecordList().get(INDEX_FIRST_RECORD.getZeroBased());
        Record editedRecord = new RecordBuilder(recordToEdit)
                .withDateTime(VALID_DATETIME_THYROID_CHECK).build();
        uniqueRecordList.setRecord(recordToEdit, editedRecord);

        PersonBuilder personInList = new PersonBuilder(firstPerson);
        Person editedPerson = personInList.withRecords(uniqueRecordList).build();

        EditRecordCommand.EditRecordDescriptor descriptor = new EditRecordDescriptorBuilder()
                .withDateTime(VALID_DATETIME_THYROID_CHECK).build();
        EditRecordCommand editRecordCommand = new EditRecordCommand(INDEX_FIRST_PERSON,
                INDEX_FIRST_RECORD, descriptor);

        String expectedMessage = String.format(EditRecordCommand.MESSAGE_EDIT_RECORD_SUCCESS,
                Messages.format(editedRecord, editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(editRecordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditRecordCommand editRecordCommand = new EditRecordCommand(INDEX_FIRST_PERSON,
                INDEX_FIRST_RECORD, new EditRecordCommand.EditRecordDescriptor());
        Person editedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UniqueRecordList uniqueRecordList = editedPerson.getRecords();
        Record editedRecord = uniqueRecordList.getRecordList().get(INDEX_FIRST_RECORD.getZeroBased());

        String expectedMessage = String.format(EditRecordCommand.MESSAGE_EDIT_RECORD_SUCCESS,
                Messages.format(editedRecord, editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editRecordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UniqueRecordList uniqueRecordList = personInFilteredList.getRecords();
        Record recordToEdit = uniqueRecordList.getRecordList().get(INDEX_FIRST_RECORD.getZeroBased());
        Record editedRecord = new RecordBuilder(recordToEdit).withDateTime(VALID_DATETIME_THYROID_CHECK).build();
        uniqueRecordList.setRecord(recordToEdit, editedRecord);
        Person editedPerson = new PersonBuilder(personInFilteredList).withRecords(uniqueRecordList).build();

        EditRecordCommand editRecordCommand = new EditRecordCommand(INDEX_FIRST_PERSON, INDEX_FIRST_RECORD,
                new EditRecordDescriptorBuilder().withDateTime(VALID_DATETIME_THYROID_CHECK).build());
        String expectedMessage = String.format(EditRecordCommand.MESSAGE_EDIT_RECORD_SUCCESS,
                Messages.format(editedRecord, editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editRecordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateRecordUnfilteredList_failure() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UniqueRecordList uniqueRecordList = firstPerson.getRecords();
        Record firstRecord = uniqueRecordList.getRecordList().get(INDEX_FIRST_RECORD.getZeroBased());
        EditRecordCommand.EditRecordDescriptor descriptor = new EditRecordDescriptorBuilder(firstRecord).build();
        EditRecordCommand editRecordCommand = new EditRecordCommand(INDEX_FIRST_PERSON,
                INDEX_SECOND_RECORD, descriptor);

        assertCommandFailure(editRecordCommand, model, EditRecordCommand.MESSAGE_DUPLICATE_RECORD);
    }

    @Test
    public void execute_duplicateRecordFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // edit first record of first person in filtered list into a duplicate in address book
        Person personInList = model.getAddressBook().getPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UniqueRecordList uniqueRecordList = personInList.getRecords();
        Record secondRecord = uniqueRecordList.getRecordList().get(INDEX_SECOND_RECORD.getZeroBased());
        EditRecordCommand editRecordCommand = new EditRecordCommand(INDEX_FIRST_PERSON, INDEX_FIRST_RECORD,
                new EditRecordDescriptorBuilder(secondRecord).build());

        assertCommandFailure(editRecordCommand, model, EditRecordCommand.MESSAGE_DUPLICATE_RECORD);
    }

    @Test
    public void execute_invalidRecordIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList()
                .get(INDEX_FIRST_PERSON.getZeroBased()).getRecords().getRecordList().size() + 1);
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

}
