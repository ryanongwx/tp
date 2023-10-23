package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.record.Condition;
import seedu.address.model.record.Record;
import seedu.address.model.shared.DateTime;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Record objects.
 */
public class RecordBuilder {

    private List<Condition> conditions;

    private DateTime dateTime;

    /**
     * Creates a {@code RecordBuilder} with the default details.
     */
    public RecordBuilder() {
        conditions = new ArrayList<>();
        dateTime = new DateTime("09-10-2023 1800");
    }

    /**
     * Initializes the RecordBuilder with the data of {@code recordToCopy}.
     */
    public RecordBuilder(Record recordToCopy) {
        conditions = recordToCopy.getConditions();
        dateTime = recordToCopy.getDateTime();
    }

    /**
     * Parses the {@code conditions} into a {@code List<Condition>}
     * and set it to the {@code Record} that we are building.
     */
    public RecordBuilder withConditions(String ... conditions) {
        this.conditions = SampleDataUtil.getConditionList(conditions);
        return this;
    }

    /**
     * Sets the {@code dateTime} of the {@code Record} that we are building.
     */
    public RecordBuilder withDateTime(String dateTime) {
        this.dateTime = new DateTime(dateTime);
        return this;
    }

    /**
     * Builds a new record
     */
    public Record build() {
        return new Record(dateTime, conditions);
    }


}
