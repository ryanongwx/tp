package seedu.address.testutil;

import seedu.address.model.record.Record;

/**
 * A utility class containing a list of {@code Record} objects to be used in tests.
 */
public class TypicalRecords {
    public static final Record FEVER = new RecordBuilder()
            .withDateTime("09-10-2023 1800")
            .withConditions("Fever")
            .build();

    public static final Record FEVER_AND_COLD = new RecordBuilder()
            .withDateTime("09-09-2023 1800")
            .withConditions("Fever", "Cold")
            .build();

    public static final Record ALLERGIC_REACTION = new RecordBuilder()
            .withDateTime("23-10-2022 1130")
            .withConditions("Allergic Reaction")
            .build();

    public static final Record HEADACHE = new RecordBuilder()
            .withDateTime("24-12-2023 1200")
            .withConditions("Headache")
            .build();

    public static final Record STOMACHACHE = new RecordBuilder()
            .withDateTime("20-03-2023 1500")
            .withConditions("Stomachache")
            .build();

    public static final Record SORE_THROAT_AND_COLD = new RecordBuilder()
            .withDateTime("25-04-2023 1000")
            .withConditions("Sore throat", "Cold")
            .build();
}
