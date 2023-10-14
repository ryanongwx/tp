package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Email("alexyeoh@example.com"), new Phone("87438807"),
                new Gender("M"), new Age (12), new BloodType("A"),getAllergySet("Peanuts")),
            new Person(new Name("Bernice Yu"), new Email("berniceyu@example.com"), new Phone("99272758"),
                new Gender("F"), new Age(31), new BloodType("B"), getAllergySet("Dust", "Peanuts")),
            new Person(new Name("Charlotte Oliveiro"), new Email("charlotte@example.com"), new Phone("93210283"),
                new Gender("F"), new Age(12), new BloodType("AB"), getAllergySet("Dust")),
            new Person(new Name("David Li"), new Email("lidavid@example.com"), new Phone("91031282"),
                new Gender("M"), new Age(33), new BloodType("O"),
                getAllergySet("Pollen")),
            new Person(new Name("Irfan Ibrahim"), new Email("irfan@example.com"), new Phone("92492021"),
                new Gender("M"), new Age(21), new BloodType("B"),
                getAllergySet("Fur")),
            new Person(new Name("Roy Balakrishnan"), new Email("royb@example.com"), new Phone("92624417"),
                new Gender("M"), new Age(24), new BloodType("B+"),
                getAllergySet("Grass"))
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
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Allergy> getAllergySet(String... strings) {
        return Arrays.stream(strings)
                .map(Allergy::new)
                .collect(Collectors.toSet());
    }

}
