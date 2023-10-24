package seedu.address.model.util;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.UniqueAppointmentList;
import seedu.address.model.person.Age;
import seedu.address.model.person.Allergy;
import seedu.address.model.person.BloodType;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.record.Condition;
import seedu.address.model.record.Record;
import seedu.address.model.record.UniqueRecordList;
import seedu.address.model.shared.DateTime;
import seedu.address.model.shared.Name;


/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Email("alexyeoh@example.com"), new Phone("87438807"),
                        new Gender("M"), new Age(12), new BloodType("A+"), getAllergySet("Peanuts"),
                getRecordList(new Record(new DateTime("01-01-2001 1200"), getConditionList("Fever"))),
                    getAppointmentList(new Appointment(new Name("Eye Exam"), new DateTime("01-01-2001 1200"))),
                    true),
            new Person(new Name("Bernice Yu"), new Email("berniceyu@example.com"), new Phone("99272758"),
                        new Gender("F"), new Age(31), new BloodType("B+"), getAllergySet("Dust", "Peanuts"),
                new UniqueRecordList(), new UniqueAppointmentList(), false),
            new Person(new Name("Charlotte Oliveiro"), new Email("charlotte@example.com"), new Phone("93210283"),
                        new Gender("F"), new Age(12), new BloodType("AB+"), getAllergySet("Dust"),
                new UniqueRecordList(), new UniqueAppointmentList(), false),
            new Person(new Name("David Li"), new Email("lidavid@example.com"), new Phone("91031282"),
                new Gender("M"), new Age(33), new BloodType("O-"),
                getAllergySet("Pollen"), new UniqueRecordList(), new UniqueAppointmentList(), false),
            new Person(new Name("Irfan Ibrahim"), new Email("irfan@example.com"), new Phone("92492021"),
                new Gender("M"), new Age(21), new BloodType("B-"),
                getAllergySet("Fur"), new UniqueRecordList(), new UniqueAppointmentList(), false),
            new Person(new Name("Roy Balakrishnan"), new Email("royb@example.com"), new Phone("92624417"),
                new Gender("M"), new Age(24), new BloodType("B+"),
                getAllergySet("Grass"), new UniqueRecordList(), new UniqueAppointmentList(), false),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns an allergy set containing the list of strings given.
     */
    public static Set<Allergy> getAllergySet(String... strings) {
        return Arrays.stream(strings)
                .map(Allergy::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a list of conditions containing the list of strings given.
     */
    public static List<Condition> getConditionList(String ... strings) {
        return Arrays.stream(strings)
                .map(Condition::new)
                .collect(Collectors.toList());
    }

    public static UniqueRecordList getRecordList(Record... records) {
        UniqueRecordList recordList = new UniqueRecordList();
        recordList.setRecords(Arrays.asList(records));
        return recordList;
    }
    public static UniqueAppointmentList getAppointmentList(Appointment... appointments) {
        UniqueAppointmentList appointmentList = new UniqueAppointmentList();
        appointmentList.setAppointments(Arrays.asList(appointments));
        return appointmentList;
    }
}
