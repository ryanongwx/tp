package seedu.address.storage;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.record.Condition;
import seedu.address.model.record.Record;
import seedu.address.model.shared.DateTime;

/**
 * Jackson-friendly version of {@link Record}.
 */
public class JsonAdaptedRecord {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Record's %s field is missing!";
    private final String dateTime;
    private final List<JsonAdaptedCondition> conditions = new ArrayList<>();
    private final String filePath;

    /**
     * Constructs a {@code JsonAdoptedRecord} with the given record details.
     */
    @JsonCreator
    public JsonAdaptedRecord(@JsonProperty("dateTime") String dateTime,
            @JsonProperty("condition") List<JsonAdaptedCondition> conditions,
            @JsonProperty("filePath") String filePath) {
        this.dateTime = dateTime;
        if (conditions != null) {
            this.conditions.addAll(conditions);
        }
        this.filePath = filePath;
    }

    /**
     * Converts a given {@code Record} into this class for Jackson use.
     */
    public JsonAdaptedRecord(Record source) {
        this.dateTime = source.getDateTime().toString();
        this.filePath = source.getFilePath() == null ? null : source.getFilePath().toString();
        this.conditions.addAll(source.getConditions().stream()
                .map(JsonAdaptedCondition::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's
     * {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted person.
     */
    public Record toModelType() throws IllegalValueException {
        final List<Condition> conditionsList = new ArrayList<>();
        if (conditions == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Condition.class.getSimpleName()));
        }

        for (JsonAdaptedCondition condition : conditions) {
            if (condition == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                        Condition.class.getSimpleName()));
            }
            conditionsList.add(condition.toModelType());
        }

        if (dateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DateTime.class.getSimpleName()));
        }

        final DateTime modelDateTime = new DateTime(dateTime);

        final List<Condition> modelConditions = new ArrayList<>(conditionsList);

        if (filePath == null) {
            return new Record(modelDateTime, modelConditions, null);
        } else {
            Path modelFilePath = Paths.get(filePath);
            return new Record(modelDateTime, modelConditions, modelFilePath);
        }

    }
}
