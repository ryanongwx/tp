package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALLERGIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOODTYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONDITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.shared.DateTime;
import seedu.address.model.shared.Name;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_GENDER_AMY = "F";
    public static final String VALID_GENDER_BOB = "M";
    public static final int VALID_AGE_AMY = 20;
    public static final int VALID_AGE_BOB = 31;
    public static final String VALID_BLOODTYPE_AMY = "B+";
    public static final String VALID_BLOODTYPE_BOB = "A-";
    public static final String VALID_ALLERGY_DUST = "Dust";
    public static final String VALID_ALLERGY_PEANUTS = "Peanuts";

    public static final String VALID_NAME_THYROID_CHECK = "Thyroid Check";
    public static final String VALID_NAME_SLEEP_STUDY = "Sleep Study";
    public static final String VALID_DATETIME_THYROID_CHECK = "01-01-2001 1200";
    public static final String VALID_DATETIME_SLEEP_STUDY = "11-09-2001 1200";
    public static final String VALID_CONDITION_DIARRHEA = "Diarrhea";
    public static final String VALID_CONDITION_HEAT_STROKE = "Heat stroke";
    public static final Appointment VALID_APPOINTMENT_THYROID_CHECK =
            new Appointment(new Name(VALID_NAME_THYROID_CHECK), new DateTime(VALID_DATETIME_THYROID_CHECK));
    public static final Appointment VALID_APPOINTMENT_SLEEP_STUDY = new Appointment(new Name(VALID_NAME_SLEEP_STUDY),
            new DateTime(VALID_DATETIME_SLEEP_STUDY));

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String GENDER_DESC_AMY = " " + PREFIX_GENDER + VALID_GENDER_AMY;
    public static final String GENDER_DESC_BOB = " " + PREFIX_GENDER + VALID_GENDER_BOB;
    public static final String AGE_DESC_AMY = " " + PREFIX_AGE + VALID_AGE_AMY;
    public static final String AGE_DESC_BOB = " " + PREFIX_AGE + VALID_AGE_BOB;
    public static final String BLOODTYPE_DESC_AMY = " " + PREFIX_BLOODTYPE + VALID_BLOODTYPE_AMY;
    public static final String BLOODTYPE_DESC_BOB = " " + PREFIX_BLOODTYPE + VALID_BLOODTYPE_BOB;
    public static final String ALLERGY_DESC_DUST = " " + PREFIX_ALLERGIES + VALID_ALLERGY_DUST;
    public static final String ALLERGY_DESC_PEANUTS = " " + PREFIX_ALLERGIES + VALID_ALLERGY_PEANUTS;

    public static final String NAME_DESC_SLEEP_STUDY = " " + PREFIX_NAME + VALID_NAME_SLEEP_STUDY;
    public static final String NAME_DESC_THYROID_CHECK = " " + PREFIX_NAME + VALID_NAME_THYROID_CHECK;
    public static final String DATETIME_DESC_SLEEP_STUDY = " " + PREFIX_DATE + VALID_DATETIME_SLEEP_STUDY;
    public static final String DATETIME_DESC_THYROID_CHECK = " " + PREFIX_DATE + VALID_DATETIME_THYROID_CHECK;
    public static final String CONDITON_DESC_HEAT_STROKE = " " + PREFIX_CONDITION + VALID_CONDITION_HEAT_STROKE;
    public static final String CONDITION_DESC_DIARRHEA = " " + PREFIX_CONDITION + VALID_CONDITION_DIARRHEA;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_GENDER_DESC = " " + PREFIX_GENDER + "A"; // 'A' not allowed in gender
    public static final String INVALID_AGE_DESC = " " + PREFIX_AGE + "0"; // age must be more than 0
    public static final String INVALID_BLOODTYPE_DESC = " " + PREFIX_BLOODTYPE + "G"; // 'G' not allowed in bloodtype
    public static final String INVALID_ALLERGY_DESC = " " + PREFIX_ALLERGIES + "Peanuts*";

    public static final String INVALID_DATETIME_DESC = " " + PREFIX_DATE
            + "11/1/01 1200"; // date must be in the format dd-mm-yyyy
    public static final String INVALID_CONDITION_DESC = " " + PREFIX_CONDITION
            + "Fever*"; // '*' not allowed in conditions

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withEmail(VALID_EMAIL_AMY).withPhone(VALID_PHONE_AMY).withGender(VALID_GENDER_AMY)
                .withAge(VALID_AGE_AMY).withBloodType(VALID_BLOODTYPE_AMY)
                .withAllergies(VALID_ALLERGY_DUST).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withEmail(VALID_EMAIL_BOB).withPhone(VALID_PHONE_BOB).withGender(VALID_GENDER_BOB)
                .withAge(VALID_AGE_BOB).withBloodType(VALID_BLOODTYPE_BOB)
                .withAllergies(VALID_ALLERGY_DUST, VALID_ALLERGY_PEANUTS).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult}
     * <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to
     * {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in
     * {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given
     * {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
