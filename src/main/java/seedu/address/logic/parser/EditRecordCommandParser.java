package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONDITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditRecordCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.record.Condition;

/**
 * Parses input arguments and creates a new EditRecordCommand object
 */
public class EditRecordCommandParser implements Parser<EditRecordCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditRecordCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_CONDITION);

        Index patientIndex;
        Index recordIndex;

        try {
            String preamble = argMultimap.getPreamble();
            patientIndex = ParserUtil.parsePatientIndex(preamble);
            recordIndex = ParserUtil.parseRecordIndex(preamble);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditRecordCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_DATE);

        EditRecordCommand.EditRecordDescriptor editRecordDescriptor = new EditRecordCommand.EditRecordDescriptor();

        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editRecordDescriptor.setDateTime(ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_DATE).get()));
        }
        parseConditionsForEdit(argMultimap.getAllValues(PREFIX_CONDITION))
                .ifPresent(editRecordDescriptor::setConditions);

        if (!editRecordDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditRecordCommand(patientIndex, recordIndex, editRecordDescriptor);
    }

    /**
     * Parses {@code Collection<String> allergies} into a {@code Set<Allergy>} if {@code allergies} is non-empty.
     * If {@code allergies} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Allergy>} containing zero allergies.
     */
    private Optional<List<Condition>> parseConditionsForEdit(Collection<String> conditions) throws ParseException {
        assert conditions != null;

        if (conditions.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> conditionSet = conditions.size() == 1
                && conditions.contains("") ? Collections.emptySet() : conditions;
        return Optional.of(ParserUtil.parseConditions(conditionSet));
    }
}
