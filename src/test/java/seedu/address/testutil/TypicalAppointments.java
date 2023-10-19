package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_SLEEP_STUDY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_THYROID_CHECK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_SLEEP_STUDY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_THYROID_CHECK;

import seedu.address.model.person.appointment.Appointment;
import seedu.address.model.person.appointment.UniqueAppointmentList;
/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalAppointments {

    public static final Appointment EYE_EXAM = new AppointmentBuilder().withTitle("Eye Exam")
            .withDateTime("01-01-2001 1200").build();
    public static final Appointment VACCINATION = new AppointmentBuilder().withTitle("Vaccination")
            .withDateTime("11-09-2001 1200").build();
    public static final Appointment COLONOSCOPY = new AppointmentBuilder().withTitle("Colonoscopy").withDateTime("21-12-2001 1200").build();

    // Manually added
    public static final Appointment BIOPSY = new AppointmentBuilder().withTitle("Biopsy")
            .withDateTime("01-01-2001 1200").build();
    public static final Appointment STD_TEST = new AppointmentBuilder().withTitle("STD Test")
            .withDateTime("17-08-1964 1900").build();

    // Manually added - Appointment's details found in {@code CommandTestUtil}
    public static final Appointment SLEEP_STUDY = new AppointmentBuilder().withTitle(VALID_TITLE_SLEEP_STUDY)
            .withDateTime(VALID_DATETIME_SLEEP_STUDY).build();
    public static final Appointment THYROID_CHECK = new AppointmentBuilder().withTitle(VALID_TITLE_THYROID_CHECK)
                    .withDateTime(VALID_DATETIME_THYROID_CHECK).build();
 
    private TypicalAppointments() {} // prevents instantiation

    public static UniqueAppointmentList getTypicalAppointments() {
        UniqueAppointmentList ab = new UniqueAppointmentList();
        ab.add(EYE_EXAM);
        ab.add(VACCINATION);
        ab.add(COLONOSCOPY);
        return ab;
    }

    
}
