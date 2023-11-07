package seedu.address.ui;

import java.util.List;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Person;

public class PersonViewPanel extends UiPart<Region>{

    private static final String FXML = "ViewedPersonPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(PersonViewPanel.class);

    private List<Index> patientIndex;

    @FXML
    private ListView<Person> viewedPerson;

    public PersonViewPanel(ObservableList<Person> personList, List<Index> patientIndex){
        super(FXML);
        this.patientIndex = patientIndex;
        viewedPerson.setItems(personList);
        viewedPerson.setCellFactory(listView -> new PersonViewPanel.PersonViewCell());
    }

    class PersonViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, patientIndex.get(0).getOneBased()).getRoot());
            }
        }
    }
}
