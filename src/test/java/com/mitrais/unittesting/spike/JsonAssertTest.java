package com.mitrais.unittesting.spike;


import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class JsonAssertTest {

    // simulate this as response from services
    String actualResponse = "{\"id\":1,\"name\":\"Ball\",\"price\":10000,\"quantity\":20}";

    @Test
    public void jsonAssert_StrictTrue_ExactMatchExceptForSpaces() throws JSONException {
        String expected = "{\"id\": 1,\"name\":\"Ball\", \"price\": 10000,\"quantity\":20}";
        // if true thant have to have all element in the object
        JSONAssert.assertEquals(expected, actualResponse, true);
    }

    @Test
    public void jsonAssert_StrictFalse_ExactMatchExceptForSpaces() throws JSONException {
        String expected = "{\"id\": 1,\"name\":\"Ball\",\"quantity\":20}";
        // if true thant have to have all element in the object
        JSONAssert.assertEquals(expected, actualResponse, false);
    }

    @Test
    public void jsonAssert_WithoutEscapeCharacter() throws JSONException {
        String expected = "{id:1,name:\"Ball\",quantity:20}";
        // if true thant have to have all element in the object
        JSONAssert.assertEquals(expected, actualResponse, false);
    }
}
