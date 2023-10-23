package seedu.address.testutil;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.shared.DateTime;
import seedu.address.model.shared.Name;

/**
 * A utility class to help with building Appointment objects.
 */
public class AppointmentBuilder {

    public static final String DEFAULT_NAME = "Nose Exam";
    public static final String DEFAULT_DATETIME = "27-10-2010 1200";

    private Name name;
    private DateTime dateTime;

    /**
     * Creates a {@code AppoointmentBuilder} with the default details.
     */
    public AppointmentBuilder() {
        name = new Name(DEFAULT_NAME);
        dateTime = new DateTime(DEFAULT_DATETIME);
    }

    /**
     * InitializesAppointment with the data of {@code personToCopy}.
     */
    public AppointmentBuilder(Appointment appointmentToCopy) {
        name = appointmentToCopy.getName();
        dateTime = appointmentToCopy.getDateTime();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public AppointmentBuilder withName(String name) {
        this.name = new Name(name);
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
        return new Appointment(name, dateTime);
    }

}
