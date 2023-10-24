package seedu.address.testutil;

import java.io.IOException;

import seedu.address.commons.util.JsonUtil;

public class MockJsonUtil extends JsonUtil {

    // Override the fromJsonString method to return null
    public static <T> T fromJsonString(String jsonString, Class<T> classOfT) throws IOException {
        return null;
    }
}
