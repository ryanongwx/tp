package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.record.Record;

public class RecordCard extends UiPart<Region>{

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

    public RecordCard(Record record, int displayedIndex) {
        super(FXML);
        this.record = record;
        id.setText(displayedIndex + ". ");
        dateTime.setText(record.getDateTime().toString());
        condition.setText(record.getConditions().toString());
    }
}
