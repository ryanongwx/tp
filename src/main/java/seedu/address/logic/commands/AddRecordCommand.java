package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddRecordCommandParser;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.record.Record;

import java.util.List;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

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
}
