import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.yape.utils.APIUtils;

public class GetBookingTests extends BaseTest{

    String schemaPath = "schemas/get_booking_id_schema.json";

    @Test
    public void testGetBookingById() {
        Response response = APIUtils.getBookingById(2);
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void testGetBookingByIdNotFound() {
        Response response = APIUtils.getBookingById(9999);
        Assert.assertEquals(response.getStatusCode(), 404);
    }
}
