
import io.qameta.allure.testng.AllureTestNg;
import io.restassured.response.Response;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.yape.utils.APIUtils;

@Listeners(AllureTestNg.class)
public class GetBookingIdsTests extends BaseTest {

    String schemaPath = "schemas/get_booking_ids_schema.json";

    @Test
    public void testGetBookingIds() {
        Response response = APIUtils.getBookingIds();
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void testInvalidHttpMethodWithGetBookingIds() {
        Response response = APIUtils.postBookingIds();
        Assert.assertEquals(response.getStatusCode(), 500);
    }

    @Test
    public void testGetBookingIdsWithParams() {
        Response response = APIUtils.getBookingIds("John", "Doe");
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void testGetBookingIdsWithNonexistentParams() {
        Response response = APIUtils.getBookingIds("nonexistent", "nonexistent");
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void testGetBookingIdsWithDates() {
        Response response = APIUtils.getBookingIds(2020, 2023);
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void testGetBookingIdsWithDatesAndNames() {
        Response response = APIUtils.getBookingIds("John", "Doe", 2022, 2023);
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void testGetBookingIdsWithInvalidDates() {
        Response response = APIUtils.getBookingIds(3000, 4000);
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void testGetBookingIdsWithInvalidDatesAndNames() {
        Response response = APIUtils.getBookingIds("nonexistent", "nonexistent", 3000, 4000);
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
        Assert.assertEquals(response.getStatusCode(), 200);
    }

}
