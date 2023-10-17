package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.appointment.Appointment;
import seedu.address.model.person.appointment.UniqueAppointmentList;
/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Gender gender;
    private final Age age;
    private final BloodType bloodType;
    private final Set<Allergy> allergies = new HashSet<>();
    private boolean isPinned;
    private final UniqueAppointmentList appointments;
    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Email email, Phone phone, Gender gender, Age age,
                  BloodType bloodType, Set<Allergy> allergies, boolean isPinned, UniqueAppointmentList appointments) {
        requireAllNonNull(name, phone, email, gender, age, allergies, isPinned, appointments);
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.age = age;
        this.bloodType = bloodType;
        this.allergies.addAll(allergies);
        this.isPinned = isPinned;
        this.appointments = appointments;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Gender getGender() {
        return gender;
    }

    public boolean isPinned() {
        return isPinned;
    }

    public Age getAge() {
        return age;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public Set<Allergy> getAllergies() {
        return Collections.unmodifiableSet(allergies);
    }

    public UniqueAppointmentList getAppointments() {
        return appointments;
    }

    /**
     * Returns true if the same appointment as {@code appointment} exists in the person.
     */
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return appointments.contains(appointment);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && gender.equals(otherPerson.gender)
                && age.equals(otherPerson.age)
                && bloodType.equals(otherPerson.bloodType)
                && allergies.equals(otherPerson.allergies)
                && isPinned == otherPerson.isPinned;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, email, phone, gender, age, bloodType, allergies);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("email", email)
                .add("phone", phone)
                .add("gender", gender)
                .add("age", age)
                .add("bloodType", bloodType)
                .add("allergies", allergies)
                .add("isPinned", isPinned)
                .toString();
    }
}
