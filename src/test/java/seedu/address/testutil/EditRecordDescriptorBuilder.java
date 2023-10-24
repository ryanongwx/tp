package seedu.address.testutil;

import java.util.List;

import seedu.address.logic.commands.EditRecordCommand;
import seedu.address.model.record.Condition;
import seedu.address.model.record.Record;
import seedu.address.model.shared.DateTime;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building EditRecordDescriptor objects.
 */
public class EditRecordDescriptorBuilder {

    private EditRecordCommand.EditRecordDescriptor descriptor;

    public EditRecordDescriptorBuilder() {
        descriptor = new EditRecordCommand.EditRecordDescriptor();
    }

    public EditRecordDescriptorBuilder(EditRecordCommand.EditRecordDescriptor descriptor) {
        this.descriptor = new EditRecordCommand.EditRecordDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditRecordDescriptor} with fields containing {@code record}'s details
     */
    public EditRecordDescriptorBuilder(Record record) {
        descriptor = new EditRecordCommand.EditRecordDescriptor();
        descriptor.setDateTime(record.getDateTime());
        descriptor.setConditions(record.getConditions());
    }

    /**
     * Sets the {@code DateTime} of the {@code EditRecordDescriptor} that we are building.
     */
    public EditRecordDescriptorBuilder withDateTime(String dateTime) {
        DateTime newDateTime = new DateTime(dateTime);
        descriptor.setDateTime(newDateTime);
        return this;
    }

    /**
     * Sets the {@code patientIndex} of the {@code EditRecordDescriptor} that we are building.
     */
    public EditRecordDescriptorBuilder withPatientIndex(Integer patientIndex) {
        descriptor.setPatientIndex(patientIndex);
        return this;
    }

    /**
     * Parses the {@code conditions} into a {@code ArrayList<Condition>} and set it to the {@code EditRecordDescriptor}
     * that we are building.
     */
    public EditRecordDescriptorBuilder withConditions(String... conditions) {
        List<Condition> conditionList = SampleDataUtil.getConditionList(conditions);
        descriptor.setConditions(conditionList);
        return this;
    }

    public EditRecordCommand.EditRecordDescriptor build() {
        return descriptor;
    }
}
