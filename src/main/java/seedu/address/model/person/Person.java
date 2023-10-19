package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.record.Record;


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
    private final List<Record> records = new ArrayList<>();
    private boolean isPinned;
    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Email email, Phone phone, Gender gender, Age age,
                  BloodType bloodType, Set<Allergy> allergies, boolean isPinned) {
        requireAllNonNull(name, phone, email, gender, age, allergies);
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.age = age;
        this.bloodType = bloodType;
        this.allergies.addAll(allergies);
        this.isPinned = isPinned;
    }

    /**
     * Constructs a Person object
     */
    public Person(Name name, Email email, Phone phone, Gender gender, Age age,
                  BloodType bloodType, Set<Allergy> allergies, List<Record> records, boolean isPinned) {
        requireAllNonNull(name, phone, email, gender, age, allergies, records);
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.age = age;
        this.bloodType = bloodType;
        this.allergies.addAll(allergies);
        this.records.addAll(records);
        this.isPinned = isPinned;
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

    public List<Record> getRecords() {
        return Collections.unmodifiableList(records);
    }

    public List<Record> getModifiableRecords() {
        return records;
    }

    public void setPinned(boolean pinned) {
        this.isPinned = pinned;
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
     * Adds a record to the patient
     */
    public void addRecord(Record record) {
        requireNonNull(record);
        this.records.add(record);
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
                && records.equals(otherPerson.records)
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
                .add("records", records)
                .add("isPinned", isPinned)
                .toString();
    }

}
