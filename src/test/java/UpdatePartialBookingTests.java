import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.yape.utils.APIUtils;
import com.google.gson.JsonObject;
import org.yape.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static io.restassured.RestAssured.given;

public class UpdatePartialBookingTests extends BaseTest{
    private String token;

    @BeforeClass
    public void setUp() {
        Response response = APIUtils.createToken("admin", "password123");
        token = response.getBody().path("token");
    }

    @DataProvider(name = "updatePartialBookingData")
    public Iterator<Object[]> bookingDataProvider() {
        JsonObject jsonObject = JsonUtils.readJsonObjectFromFile("src/main/java/org/yape/utils/data/update_partial_booking_data.json");
        List<Object[]> testData = new ArrayList<>();
        for (String key : jsonObject.keySet()) {
            JsonObject data = jsonObject.getAsJsonObject(key);
            JsonObject request = data.getAsJsonObject("request");
            int id = data.get("id").getAsInt();
            int expectedStatus = data.get("expected_status").getAsInt();
            testData.add(new Object[] {request, id, expectedStatus});
        }
        return testData.iterator();
    }

    @Test(dataProvider = "updatePartialBookingData")
    public void testUpdatePartialBooking(JsonObject request, int id, int expectedStatus) {
        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token=" + token)
                .body(request.toString())
                .when()
                .patch("https://restful-booker.herokuapp.com/booking/" + id)
                .then()
                .statusCode(expectedStatus);
    }
}