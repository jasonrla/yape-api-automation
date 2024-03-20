import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.yape.utils.APIUtils;
import com.google.gson.JsonObject;
import org.yape.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DeleteTests extends BaseTest{
    private String token;

    @BeforeClass
    public void setUp() {
        Response response = APIUtils.createToken("admin", "password123");
        token = response.getBody().path("token");
    }

    @DataProvider(name = "bookingData")
    public Iterator<Object[]> bookingDataProvider() {
        JsonObject jsonObject = JsonUtils.readJsonObjectFromFile("src/main/java/org/yape/utils/data/delete_booking_data.json");
        List<Object[]> testData = new ArrayList<>();
        JsonObject data = jsonObject.getAsJsonObject("data_ok");
        if (data != null) {
            JsonObject request = data.getAsJsonObject("request");
            int expectedStatus = data.get("expected_status").getAsInt();
            testData.add(new Object[] {request, expectedStatus});
        }
        return testData.iterator();
    }

    @Test(dataProvider = "bookingData")
    public void testDeleteBooking(JsonObject newBooking, int expectedStatus) {
        Response createResponse = APIUtils.createBooking(newBooking);
        int bookingId = createResponse.jsonPath().getInt("bookingid");

        Response response = APIUtils.deleteBooking(bookingId, token);
        response.then().statusCode(expectedStatus);
    }
}