package seedu.address.ui;

import java.awt.Desktop;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.scene.control.Hyperlink;
import java.io.File;
import java.io.IOException;

import seedu.address.model.record.Record;

/**
 * An UI component that displays information of a {@code Record}.
 */
public class RecordCard extends UiPart<Region> {

    private static final String FXML = "RecordListCard.fxml";

    public final Record record;
    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label dateTime;
    @FXML
    private Label condition;
    @FXML
    private Hyperlink filePathLabel;

    /**
     * Creates a {@code RecordCode} with the given {@code Record} and index to
     * display.
     */
    public RecordCard(Record record, int displayedIndex) {
        super(FXML);
        this.record = record;
        id.setText(displayedIndex + ". ");
        dateTime.setText(record.getDateTime().toString());
        condition.setText(record.getConditions().toString());
        if (record.getFilePath() != null) {
            filePathLabel.setText(record.getFilePath().toString());
        } else {
            filePathLabel.setText("No file attached");
            filePathLabel.setDisable(true); // Disable the hyperlink if no file is attached
        }

    }

    @FXML
    public void handleAttachFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File to Attach");
        File file = fileChooser.showOpenDialog(cardPane.getScene().getWindow()); // Fetching the current stage from the
                                                                                 // cardPane

        if (file != null) {
            // Handle the attached file. You might want to save its path, upload it, etc.
            this.record.setFilePath(file.toPath());
            filePathLabel.setText(file.toPath().toString());
        }
    }

    @FXML
    public void handleOpenFile(ActionEvent event) {
        if (record.getFilePath() != null && Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(record.getFilePath().toFile());
            } catch (IOException e) {
                // Handle the exception, perhaps show an error message to the user.
                e.printStackTrace();
            }
        }
    }

}
