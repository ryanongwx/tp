package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.model.person.appointment.Appointment;
/**
 * A utility class for Appointment.
 */
public class AppointmentUtil {

    /**
     * Returns an add command string for adding the {@code Appointment}.
     */
    public static String getAddAppointmentCommand(Appointment Appointment) {
        return AddAppointmentCommand.COMMAND_WORD + " 1 " + getAppointmentDetails(Appointment);
    }

    /**
     * Returns the part of command string for the given {@code Appointment}'s details.
     */
    public static String getAppointmentDetails(Appointment Appointment) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_TITLE + Appointment.getTitle().fullName + " ");
        sb.append(PREFIX_DATE + Appointment.getDateTime().toString());
        return sb.toString();
    }
}
