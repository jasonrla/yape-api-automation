import com.google.gson.JsonObject;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.yape.utils.APIUtils;
import org.yape.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CreateBookingTests extends BaseTest{

    @DataProvider(name = "bookingData")
    public Iterator<Object[]> bookingDataProvider() {
        JsonObject jsonObject = JsonUtils.readJsonObjectFromFile("src/main/java/org/yape/utils/data/get_booking_id_data.json");
        List<Object[]> testData = new ArrayList<>();
        for (String key : jsonObject.keySet()) {
            JsonObject data = jsonObject.getAsJsonObject(key);
            JsonObject request = data.getAsJsonObject("request");
            int expectedStatus = data.get("expected_status").getAsInt();
            testData.add(new Object[] {request, expectedStatus});
        }
        return testData.iterator();
    }

    @Test(dataProvider = "bookingData")
    public void testCreateBooking(JsonObject request, int expectedStatus) {
        ValidatableResponse response = APIUtils.createBooking(request)
                .then()
                .statusCode(expectedStatus);
    }

    @Test(dataProvider = "bookingData")
    public void testCreateBookingWithInvalidData(JsonObject request, int expectedStatus) {
        JsonObject invalidRequest = new JsonObject();
        ValidatableResponse response = APIUtils.createBooking(invalidRequest)
                .then()
                .statusCode(500);
    }
}
