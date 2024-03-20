import io.qameta.allure.testng.AllureTestNg;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.yape.utils.APIUtils;
import io.restassured.module.jsv.JsonSchemaValidator;

@Listeners(AllureTestNg.class)
public class CreateTokenTests extends BaseTest {

    String schemaPath = "schemas/create_token_schema.json";

    @Test
    public void testCreateTokenValidCredentials() {
        Response response = APIUtils.createToken("admin", "password123");
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void testCreateTokenInvalidCredentials() {
        Response response = APIUtils.createToken("invalidUsername", "invalidPassword");
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("reason"), "Bad credentials");
    }

    @Test
    public void testCreateTokenEmptyUsername() {
        Response response = APIUtils.createToken("", "password123");
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("reason"), "Bad credentials");
    }

    @Test
    public void testCreateTokenEmptyPassword() {
        Response response = APIUtils.createToken("admin", "");
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("reason"), "Bad credentials");
    }

    @Test
    public void testCreateTokenNullUsername() {
        Response response = APIUtils.createToken(null, "password123");
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("reason"), "Bad credentials");
    }

    @Test
    public void testCreateTokenNullPassword() {
        Response response = APIUtils.createToken("admin", null);
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("reason"), "Bad credentials");
    }

}
