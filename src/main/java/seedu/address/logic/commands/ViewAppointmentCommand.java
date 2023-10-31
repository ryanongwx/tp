package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * 
 */
public class ViewAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "viewappointment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Opens Appointments window.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened appointments window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, false, true, false);
    }
}
