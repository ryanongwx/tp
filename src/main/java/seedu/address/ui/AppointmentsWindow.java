package seedu.address.ui;

import java.util.List;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.appointment.Appointment;

/**
 * Controller for an Appointments page
 */
public class AppointmentsWindow extends UiPart<Stage> {

    private static final String FXML = "AppointmentsWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(AppointmentsWindow.class);

    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private AppointmentListPanel appointmentListPanel;
    private AppointmentCalenderPanel appointmentCalenderPanel;

    @FXML
    private StackPane appointmentListPanelPlaceholder;
    @FXML
    private StackPane appointmentCalenderPanelPlaceholder;

    /**
     * Creates a new AppointmentsWindow.
     *
     * @param root Stage to use as the root of the AppointmentsWindow.
     */
    public AppointmentsWindow(Stage root, Logic logic) {
        super(FXML, root);
        this.logic = logic;
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        List<Appointment> filteredAppointments = logic.getFilteredAppointmentList();

        // Print the contents of the list
        logger.info("Filtered Appointments:");
        for (Appointment appointment : filteredAppointments) {
            logger.info(appointment.toString());
        }
        appointmentListPanel = new AppointmentListPanel(logic.getFilteredAppointmentList());
        appointmentListPanelPlaceholder.getChildren().add(appointmentListPanel.getRoot());

        appointmentCalenderPanel = new AppointmentCalenderPanel(logic.getFullAppointmentList());
        appointmentCalenderPanelPlaceholder.getChildren().add(appointmentCalenderPanel.getRoot());
    }

    /**
     * Shows the appointment window.
     * 
     * @throws IllegalStateException
     *                               <ul>
     *                               <li>
     *                               if this method is called on a thread other than
     *                               the JavaFX Application Thread.
     *                               </li>
     *                               <li>
     *                               if this method is called during animation or
     *                               layout processing.
     *                               </li>
     *                               <li>
     *                               if this method is called on the primary stage.
     *                               </li>
     *                               <li>
     *                               if {@code dialogStage} is already showing.
     *                               </li>
     *                               </ul>
     */
    public void show() {
        logger.fine("Showing appointments window.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the appointments window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the appointments window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the appointments window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
    // /**
    // * Sets the default size based on {@code guiSettings}.
    // */
    // private void setWindowDefaultSize(GuiSettings guiSettings) {
    // primaryStage.setHeight(guiSettings.getWindowHeight());
    // primaryStage.setWidth(guiSettings.getWindowWidth());
    // if (guiSettings.getWindowCoordinates() != null) {
    // primaryStage.setX(guiSettings.getWindowCoordinates().getX());
    // primaryStage.setY(guiSettings.getWindowCoordinates().getY());
    // }
    // }
}
