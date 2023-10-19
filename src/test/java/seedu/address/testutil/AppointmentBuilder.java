package seedu.address.testutil;

import seedu.address.model.person.Name;
import seedu.address.model.person.appointment.Appointment;
import seedu.address.model.person.appointment.DateTime;

/**
 * A utility class to help with building Appointment objects.
 */
public class AppointmentBuilder {

    public static final String DEFAULT_TITLE = "Nose Exam";
    public static final String DEFAULT_DATETIME = "27-10-2010 1200";

    private Name title;
    private DateTime dateTime;

    /**
     * Creates a {@code AppoointmentBuilder} with the default details.
     */
    public AppointmentBuilder() {
        title = new Name(DEFAULT_TITLE);
        dateTime = new DateTime(DEFAULT_DATETIME);
    }

    /**
     * InitializesAppointment with the data of {@code personToCopy}.
     */
    public AppointmentBuilder(Appointment appointmentToCopy) {
        title = appointmentToCopy.getTitle();
        dateTime = appointmentToCopy.getDateTime();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public AppointmentBuilder withTitle(String title) {
        this.title = new Name(title);
        return this;
    }

    /**
     * Sets the {@code DateTime} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withDateTime(String dateTime) {
        this.dateTime = new DateTime(dateTime);
        return this;
    }

    public Appointment build() {
        return new Appointment(title, dateTime);
    }

}
