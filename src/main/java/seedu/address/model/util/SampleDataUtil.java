package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Age;
import seedu.address.model.person.Allergy;
import seedu.address.model.person.BloodType;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.appointment.Appointment;
import seedu.address.model.person.appointment.DateTime;
import seedu.address.model.person.appointment.UniqueAppointmentList;


/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Email("alexyeoh@example.com"), new Phone("87438807"),
                        new Gender("M"), new Age(12), new BloodType("A+"), getAllergySet("Peanuts"),
                true, getAppointmentList(new Appointment(new Name("Eye Exam"), new DateTime("01-01-2001 1200")))),
            new Person(new Name("Bernice Yu"), new Email("berniceyu@example.com"), new Phone("99272758"),
                        new Gender("F"), new Age(31), new BloodType("B+"), getAllergySet("Dust", "Peanuts"),
                false, new UniqueAppointmentList()),
            new Person(new Name("Charlotte Oliveiro"), new Email("charlotte@example.com"), new Phone("93210283"),
                        new Gender("F"), new Age(12), new BloodType("AB+"), getAllergySet("Dust"),
                false, new UniqueAppointmentList()),
            new Person(new Name("David Li"), new Email("lidavid@example.com"), new Phone("91031282"),
                new Gender("M"), new Age(33), new BloodType("O-"),
                getAllergySet("Pollen"), false, new UniqueAppointmentList()),
            new Person(new Name("Irfan Ibrahim"), new Email("irfan@example.com"), new Phone("92492021"),
                new Gender("M"), new Age(21), new BloodType("B-"),
                getAllergySet("Fur"), false, new UniqueAppointmentList()),
            new Person(new Name("Roy Balakrishnan"), new Email("royb@example.com"), new Phone("92624417"),
                new Gender("M"), new Age(24), new BloodType("B+"),
                getAllergySet("Grass"), false, new UniqueAppointmentList()),
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
     * Returns an appointment list containing the list of appointments given.
     */
    public static UniqueAppointmentList getAppointmentList(Appointment... appointments) {
        UniqueAppointmentList appointmentList = new UniqueAppointmentList();
        for (Appointment appointment : appointments) {
            appointmentList.add(appointment);
        }
        return appointmentList;
    }
}
