package seedu.address.model.record;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the date and time in which patient visits the doctor
 */
public class DateTime {
    public static final String MESSAGE_CONSTRAINTS =
            "Date should be in the form of 'dd-MM-yyyy HHmm";

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("ddMMyyyy HHmm");
    public final LocalDateTime dateTime;

    /**
     * Constructs a {@code DateTime}.
     *
     * @param dateTime A valid date and time.
     */
    public DateTime(String dateTime) {
        requireNonNull(dateTime);
        this.dateTime = LocalDateTime.parse(dateTime, FORMATTER);
    }

    /**
     * Constructs a {@code DateTime}.
     *
     * @param dateTime A valid date and time.
     */
    public DateTime(LocalDateTime dateTime) {
        requireNonNull(dateTime);
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DateTime)) {
            return false;
        }

        DateTime otherDateTime = (DateTime) other;
        return dateTime.equals(otherDateTime.dateTime);
    }

    @Override
    public int hashCode() {
        return dateTime.hashCode();
    }

    @Override
    public String toString() {
        return dateTime.format(FORMATTER);
    }


}
