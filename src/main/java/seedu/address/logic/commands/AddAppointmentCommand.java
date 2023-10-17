package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.appointment.Appointment;
import seedu.address.model.person.appointment.UniqueAppointmentList;

/**
 * Adds an appointment to the address book.
 */
public class AddAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "addappointment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an appointment to the address book.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_TITLE + "TITLE "
            + PREFIX_DATE + "DATE\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TITLE + "Eye Examination "
            + PREFIX_DATE + "18-09-2023 1800 ";


    public static final String MESSAGE_SUCCESS = "New appointment added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment already exists in the address book";

    private final Index index;
    private final Appointment toAdd;

    /**
     * Creates an AddAppointmentCommand to add the specified {@code Appointment}
     */
    public AddAppointmentCommand(Index index, Appointment appointment) {
        requireNonNull(index);
        requireNonNull(appointment);
        this.toAdd = appointment;
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person oldPerson = lastShownList.get(index.getZeroBased());
        if (oldPerson.hasAppointment(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        UniqueAppointmentList newAppointmentList = new UniqueAppointmentList();
        newAppointmentList.setAppointments(oldPerson.getAppointments());
        newAppointmentList.add(toAdd);

        Person newPerson = new Person(oldPerson.getName(), oldPerson.getEmail(), oldPerson.getPhone(),
                oldPerson.getGender(), oldPerson.getAge(), oldPerson.getBloodType(), oldPerson.getAllergies(),
                oldPerson.isPinned(), newAppointmentList);

        model.setPerson(oldPerson, newPerson);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd, newPerson)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddAppointmentCommand)) {
            return false;
        }

        AddAppointmentCommand otherAddCommand = (AddAppointmentCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
