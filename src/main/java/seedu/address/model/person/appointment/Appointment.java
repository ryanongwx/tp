package seedu.address.model.person.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Name;

/**
 * Represents an Appointment in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Appointment {

    // Identity fields
    private final Name title;
    private final DateTime dateTime;
    /**
     * Every field must be present and not null.
     */
    public Appointment(Name title, DateTime dateTime) {
        requireAllNonNull(title, dateTime);
        this.title = title;
        this.dateTime = dateTime;
    }

    public Name getTitle() {
        return title;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    /**
     * Returns true if both appointments have the same identity and data fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherAppointment = (Appointment) other;
        return title.equals(otherAppointment.title)
                && dateTime.equals(otherAppointment.dateTime);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, dateTime);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("title", title)
                .add("dateTime", dateTime)
                .toString();
    }

}
