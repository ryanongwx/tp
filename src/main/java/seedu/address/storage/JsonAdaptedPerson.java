package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.UniqueAppointmentList;
import seedu.address.model.person.Age;
import seedu.address.model.person.Allergy;
import seedu.address.model.person.BloodType;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.record.UniqueRecordList;
import seedu.address.model.shared.Name;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String gender;
    private final Integer age;
    private final String bloodType;
    private final List<JsonAdaptedAllergy> allergies = new ArrayList<>();
    private final List<JsonAdaptedRecord> records = new ArrayList<>();
    private final Boolean isPinned;
    private final List<JsonAdaptedAppointment> appointments = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("gender") String gender,
            @JsonProperty("age") Integer age, @JsonProperty("bloodType") String bloodType,
            @JsonProperty("allergies") List<JsonAdaptedAllergy> allergies,
            @JsonProperty("records") List<JsonAdaptedRecord> records,
            @JsonProperty("appointments") List<JsonAdaptedAppointment> appointments,
            @JsonProperty("isPinned") Boolean isPinned) {

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.bloodType = bloodType;
        if (allergies != null) {
            this.allergies.addAll(allergies);
        }
        if (records != null) {
            this.records.addAll(records);
        }
        this.isPinned = isPinned;
        if (appointments != null) {
            this.appointments.addAll(appointments);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        gender = source.getGender().gender;
        age = source.getAge().age;
        bloodType = source.getBloodType().bloodType;
        allergies.addAll(source.getAllergies().stream()
                .map(JsonAdaptedAllergy::new)
                .collect(Collectors.toList()));
        records.addAll(source.getRecords().asUnmodifiableObservableList()
                .stream()
                .map(JsonAdaptedRecord::new)
                .collect(Collectors.toList()));
        appointments.addAll(source.getAppointments().asUnmodifiableObservableList().stream()
            .map(JsonAdaptedAppointment::new)
                .collect(Collectors.toList()));
        isPinned = source.isPinned();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Allergy> allergiesList = new ArrayList<>();
        for (JsonAdaptedAllergy allergy : allergies) {
            allergiesList.add(allergy.toModelType());
        }

        final UniqueRecordList modelRecords = new UniqueRecordList();
        for (JsonAdaptedRecord record : records) {
            modelRecords.add(record.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (gender == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName()));
        }
        if (!Gender.isValidGender(gender)) {
            throw new IllegalValueException(Gender.MESSAGE_CONSTRAINTS);
        }
        final Gender modelGender = new Gender(gender);

        if (age == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Age.class.getSimpleName()));
        }
        if (!Age.isValidAge(age)) {
            throw new IllegalValueException(Age.MESSAGE_CONSTRAINTS);
        }
        final Age modelAge = new Age(age);

        if (bloodType == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    BloodType.class.getSimpleName()));
        }
        if (!BloodType.isValidBloodType(bloodType)) {
            throw new IllegalValueException(BloodType.MESSAGE_CONSTRAINTS);
        }
        final BloodType modelBloodType = new BloodType(bloodType);

        final Set<Allergy> modelAllergies = new HashSet<>(allergiesList);

        final UniqueAppointmentList modelAppointments = new UniqueAppointmentList();
        for (JsonAdaptedAppointment jsonAdaptedAppointment : appointments) {
            Appointment appointment = jsonAdaptedAppointment.toModelType();
            modelAppointments.add(appointment);
        }


        return new Person(modelName, modelEmail, modelPhone, modelGender,
                modelAge, modelBloodType, modelAllergies, modelRecords, modelAppointments, isPinned);
    }
}
