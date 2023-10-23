package seedu.address.model.record;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRecords.ALLERGIC_REACTION;
import static seedu.address.testutil.TypicalRecords.FEVER;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.record.exceptions.DuplicateRecordException;
import seedu.address.model.record.exceptions.RecordNotFoundException;

public class UniqueRecordListTest {
    private final UniqueRecordList uniqueRecordList = new UniqueRecordList();

    @Test
    public void contains_nullRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecordList.contains(null));
    }

    @Test
    public void contains_recordNotInList_returnsFalse() {
        assertFalse(uniqueRecordList.contains(FEVER));
    }
    @Test
    public void contains_recordInList_returnsTrue() {
        uniqueRecordList.add(FEVER);
        assertTrue(uniqueRecordList.contains(FEVER));
    }
    @Test
    public void add_nullRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecordList.add(null));
    }
    @Test
    public void add_duplicateRecord_throwsDuplicateRecordException() {
        uniqueRecordList.add(FEVER);
        assertThrows(DuplicateRecordException.class, () -> uniqueRecordList.add(FEVER));
    }

    @Test
    public void setPerson_nullTargetRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecordList.setRecord(null, FEVER));
    }

    @Test
    public void setPerson_nullEditedRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecordList.setRecord(FEVER, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsRecordNotFoundException() {
        assertThrows(RecordNotFoundException.class, () -> uniqueRecordList.setRecord(FEVER, FEVER));
    }

    @Test
    public void setRecord_editedRecordIsSameRecord_success() {
        uniqueRecordList.add(FEVER);
        uniqueRecordList.setRecord(FEVER, FEVER);
        UniqueRecordList expectedUniqueRecordList = new UniqueRecordList();
        expectedUniqueRecordList.add(FEVER);
        assertEquals(expectedUniqueRecordList, uniqueRecordList);
    }

    @Test
    public void setRecord_editedRecordIsDifferentRecord_success() {
        uniqueRecordList.add(FEVER);
        uniqueRecordList.setRecord(FEVER, ALLERGIC_REACTION);
        UniqueRecordList expectedUniqueRecordList = new UniqueRecordList();
        expectedUniqueRecordList.add(ALLERGIC_REACTION);
        assertEquals(expectedUniqueRecordList, uniqueRecordList);
    }

    @Test
    public void setPerson_editedRecordIsTheSame_throwsDuplicateRecordException() {
        uniqueRecordList.add(FEVER);
        uniqueRecordList.add(ALLERGIC_REACTION);
        assertThrows(DuplicateRecordException.class, () -> uniqueRecordList.setRecord(FEVER, ALLERGIC_REACTION));
    }

    @Test
    public void remove_nullRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecordList.remove(null));
    }

    @Test
    public void remove_recordDoesNotExist_throwsRecordNotFoundException() {
        assertThrows(RecordNotFoundException.class, () -> uniqueRecordList.remove(FEVER));
    }

    @Test
    public void remove_existingRecord_removesRecord() {
        uniqueRecordList.add(FEVER);
        uniqueRecordList.remove(FEVER);
        UniqueRecordList expectedUniqueRecordList = new UniqueRecordList();
        assertEquals(expectedUniqueRecordList, uniqueRecordList);
    }

    @Test
    public void setRecords_nullUniqueRecordList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecordList.setRecords((UniqueRecordList) null));
    }

    @Test
    public void setRecords_uniqueRecordList_replacesOwnListWithProvidedUniqueRecordList() {
        uniqueRecordList.add(FEVER);
        UniqueRecordList expectedUniqueRecordList = new UniqueRecordList();
        expectedUniqueRecordList.add(ALLERGIC_REACTION);
        uniqueRecordList.setRecords(expectedUniqueRecordList);
        assertEquals(expectedUniqueRecordList, uniqueRecordList);
    }

    @Test
    public void setRecords_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecordList.setRecords((List<Record>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueRecordList.add(FEVER);
        List<Record> recordList = Collections.singletonList(FEVER);
        uniqueRecordList.setRecords(recordList);
        UniqueRecordList expectedUniqueRecordList = new UniqueRecordList();
        expectedUniqueRecordList.add(FEVER);
        assertEquals(expectedUniqueRecordList, uniqueRecordList);
    }

    @Test
    public void setRecords_listWithDuplicateRecords_throwsDuplicateRecordException() {
        List<Record> listWithDuplicateRecords = Arrays.asList(FEVER, FEVER);
        assertThrows(DuplicateRecordException.class, () -> uniqueRecordList.setRecords(listWithDuplicateRecords));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueRecordList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueRecordList.asUnmodifiableObservableList().toString(), uniqueRecordList.toString());
    }

}
