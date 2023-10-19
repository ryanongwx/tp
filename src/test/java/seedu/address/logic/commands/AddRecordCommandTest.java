package seedu.address.logic.commands;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalRecords.FEVER;
import static seedu.address.testutil.TypicalRecords.SORE_THROAT_AND_COLD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class AddRecordCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToAddRecord = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        AddRecordCommand addRecordCommand = new AddRecordCommand(INDEX_FIRST_PERSON, FEVER);

        String expectedMessage = AddRecordCommand.MESSAGE_SUCCESS;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList()
                .get(INDEX_FIRST_PERSON.getZeroBased()), personToAddRecord);

        assertCommandSuccess(addRecordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AddRecordCommand addRecordCommand = new AddRecordCommand(outOfBoundIndex, FEVER);

        assertCommandFailure(addRecordCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        AddRecordCommand addRecordCommand1 = new AddRecordCommand(INDEX_FIRST_PERSON, FEVER);
        AddRecordCommand addRecordCommand2 = new AddRecordCommand(INDEX_FIRST_PERSON, FEVER);
        AddRecordCommand addRecordCommand3 = new AddRecordCommand(INDEX_THIRD_PERSON, SORE_THROAT_AND_COLD);

        assertTrue(addRecordCommand1.equals(addRecordCommand1));
        assertTrue(addRecordCommand1.equals(addRecordCommand2));
        assertFalse(addRecordCommand1.equals(1));
        assertFalse(addRecordCommand1.equals(null));
        assertFalse(addRecordCommand1.equals(addRecordCommand3));

    }
}
