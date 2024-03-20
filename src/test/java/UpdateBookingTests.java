import com.google.gson.JsonObject;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.yape.utils.APIUtils;
import org.yape.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UpdateBookingTests extends BaseTest{

    private String token;

    @BeforeClass
    public void setUp() {
        Response response = APIUtils.createToken("admin", "password123");
        token = response.getBody().path("token");
    }

    @DataProvider(name = "updateBookingData")
    public Iterator<Object[]> bookingDataProvider() {
        JsonObject jsonObject = JsonUtils.readJsonObjectFromFile("src/main/java/org/yape/utils/data/update_booking_data.json");
        List<Object[]> testData = new ArrayList<>();
        for (String key : jsonObject.keySet()) {
            JsonObject data = jsonObject.getAsJsonObject(key);
            JsonObject request = data.getAsJsonObject("request");
            int id = data.get("id").getAsInt(); // get the id from the data
            int expectedStatus = data.get("expected_status").getAsInt();
            testData.add(new Object[] {request, id, expectedStatus}); // add the id to the test data
        }
        return testData.iterator();
    }


    @Test(dataProvider = "updateBookingData")
    public void testUpdateBookingPositive(JsonObject request, int id, int expectedStatus) {
        ValidatableResponse response = APIUtils.updateBooking(id, request, token)
                .then()
                .statusCode(expectedStatus);
    }

    @Test(dataProvider = "updateBookingData")
    public void testUpdateBookingNegative(JsonObject request, int id, int expectedStatus) {
        APIUtils.updateBooking(id, request, token)
                .then()
                .statusCode(expectedStatus);
    }
}
