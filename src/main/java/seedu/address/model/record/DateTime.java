package seedu.address.model.record;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

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
        checkArgument(isValidDateTime(dateTime), MESSAGE_CONSTRAINTS);
        this.dateTime = LocalDateTime.parse(dateTime, FORMATTER);
    }

    public DateTime(LocalDateTime dateTime) {
        requireNonNull(dateTime);
        this.dateTime = dateTime;
    }

    public static boolean isValidDateTime(String test) {
        return true;
    }

    public static boolean isValidDateTime(LocalDateTime test) {
        return true;
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
