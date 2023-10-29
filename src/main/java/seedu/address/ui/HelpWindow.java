package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddRecordCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditRecordCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.ListCommand;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL =
            "https://ay2324s1-cs2103t-t12-4.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "A more detailed User "
            + "Guide can be accessed" + "from this url: ";

    public static final String COMMAND_SUMMARY = "COMMAND SUMMARY";
    private static final String PIN_AND_UNPIN = "pin/unpin INDEX"
            + "\n" + "pin / unpin a patient.";
    private static final String VIEW_AND_DELETE = "view/delete INDEX"
            + "\n" + "view / delete a patient from MedBook.";
    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;
    @FXML
    private Label commandSummary;
    @FXML
    private Label helpMessage;

    @FXML
    private Label addPatient;

    @FXML
    private Label editPatient;

    @FXML
    private Label addRecord;

    @FXML
    private Label editRecord;

    @FXML
    private Label pinAndUnpin;

    @FXML
    private Label list;

    @FXML
    private Label find;

    @FXML
    private Label viewAndDelete;

    @FXML
    private Label exit;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        commandSummary.setText(COMMAND_SUMMARY);
        helpMessage.setText(HELP_MESSAGE + USERGUIDE_URL);
        addPatient.setText(AddCommand.MESSAGE_USAGE);
        editPatient.setText(EditCommand.MESSAGE_USAGE);
        addRecord.setText(AddRecordCommand.MESSAGE_USAGE);
        editRecord.setText(EditRecordCommand.MESSAGE_USAGE);
        find.setText(FindCommand.MESSAGE_USAGE);
        pinAndUnpin.setText(PIN_AND_UNPIN);
        viewAndDelete.setText(VIEW_AND_DELETE);
        list.setText(ListCommand.COMMAND_WORD + "\n" + ListCommand.MESSAGE_SUCCESS);
        exit.setText(ExitCommand.COMMAND_WORD + "\n" + ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
