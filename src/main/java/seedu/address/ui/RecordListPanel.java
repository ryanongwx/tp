package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

public class RecordListPanel extends UiPart<Region>{

    private static final String FXML = "RecordListPanel.fxml";

    public PersonListPanel(ObservableList<Record> recordList) {
        super(FXML);
        recordListView.setItems(recordList);
        recordListView.setCellFactory(listView -> new RecordListPanel.RecordListViewCell());
    }
}
