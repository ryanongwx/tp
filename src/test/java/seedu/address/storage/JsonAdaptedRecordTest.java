package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedRecord.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRecords.FEVER;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.record.Condition;
import seedu.address.model.record.DateTime;

public class JsonAdaptedRecordTest {

    private static final LocalDateTime VALID_DATETIME = FEVER.getDateTime().dateTime;

    private static final List<JsonAdaptedCondition> VALID_CONDITIONS = FEVER.getConditions()
            .stream()
            .map(JsonAdaptedCondition::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validRecordDetails_returnsRecord() throws Exception {
        JsonAdaptedRecord record = new JsonAdaptedRecord(FEVER);
        assertEquals(FEVER, record.toModelType());
    }
    @Test
    public void toModelType_nullDateTime_throwsIllegalValueException() {
        JsonAdaptedRecord record = new JsonAdaptedRecord(null, VALID_CONDITIONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                DateTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, record::toModelType);
    }

    @Test
    public void toModelType_nullCondition_throwsIllegalValueException() {
        List<JsonAdaptedCondition> invalidCondition = new ArrayList<>(VALID_CONDITIONS);
        invalidCondition.add(null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Condition.class.getSimpleName());
        JsonAdaptedRecord record = new JsonAdaptedRecord(VALID_DATETIME, invalidCondition);
        assertThrows(IllegalValueException.class, expectedMessage, record::toModelType);
    }
}
