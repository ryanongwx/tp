package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONDITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.record.Record;

/**
 * Adds a record to a patient in the MedBook
 */
public class AddRecordCommand extends Command {

    public static final String COMMAND_WORD = "addrecord";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a patient record to the address book. "
            + "Parameters: "
            + PREFIX_DATE + "DATE "
            + PREFIX_CONDITION + "CONDITION " + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DATE + "18092023 1800"
            + PREFIX_CONDITION + "Fever";

    public static final String MESSAGE_SUCCESS = "New record added";

    private final Record record;

    private final Index index;

    /**
     * Creates a record under a patient of specified index
     */
    public AddRecordCommand(Index index, Record record) {
        requireAllNonNull(index, record);
        this.index = index;
        this.record = record;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {

        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAddRecord = lastShownList.get(index.getZeroBased());

        personToAddRecord.addRecord(this.record);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddRecordCommand)) {
            return false;
        }

        AddRecordCommand otherAddRecordCommand = (AddRecordCommand) other;
        return record.equals(otherAddRecordCommand.record)
                && index.equals(otherAddRecordCommand.index);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("record", record)
                .toString();
    }
}
