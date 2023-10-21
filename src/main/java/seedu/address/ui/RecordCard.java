package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
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

    /**
     * Creates a {@code RecordCode} with the given {@code Record} and index to display.
     */

    public RecordCard(Record record, int displayedIndex) {
        super(FXML);
        this.record = record;
        id.setText(displayedIndex + ". ");
        dateTime.setText(record.getDateTime().toString());
        condition.setText(record.getConditions().toString());
    }
}
