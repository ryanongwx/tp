package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

import static seedu.address.logic.commands.CommandTestUtil.*;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withGender("F")
            .withAge(20)
            .withBloodType("AB+")
            .withAllergies("Chocolate").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com")
            .withPhone("98765432")
            .withGender("M")
            .withAge(15)
            .withBloodType("B-")
            .withAllergies("Pollen", "Soil").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz")
            .withEmail("heinz@example.com")
            .withPhone("95352563")
            .withGender("M")
            .withAge(24)
            .withBloodType("AB-")
            .withAllergies("Dogs").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withEmail("cornelia@example.com")
            .withPhone("87652533")
            .withGender("M")
            .withAge(26)
            .withBloodType("AB+")
            .withAllergies("Cats").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer")
            .withPhone("9482224")
            .withEmail("werner@example.com")
            .withGender("F")
            .withAge(27)
            .withBloodType("A-")
            .withAllergies("Light").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withEmail("lydia@example.com")
            .withPhone("9482427")
            .withGender("F")
            .withAge(29)
            .withBloodType("B+").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best")
            .withEmail("anna@example.com")
            .withPhone("9482442")
            .withGender("M")
            .withAge(30)
            .withBloodType("O").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier")
            .withEmail("stefan@example.com")
            .withPhone("8482424")
            .withGender("M")
            .withAge(30)
            .withBloodType("A").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller")
            .withEmail("hans@example.com")
            .withPhone("8482131")
            .withGender("M")
            .withAge(33)
            .withBloodType("B").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withGender(VALID_GENDER_AMY)
            .withAge(VALID_AGE_AMY)
            .withBloodType(VALID_BLOODTYPE_AMY)
            .withAllergies(VALID_ALLERGY_DUST).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withGender(VALID_GENDER_BOB)
            .withAge(VALID_AGE_BOB)
            .withBloodType(VALID_BLOODTYPE_BOB)
            .withAllergies(VALID_ALLERGY_DUST, VALID_ALLERGY_PEANUTS).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
