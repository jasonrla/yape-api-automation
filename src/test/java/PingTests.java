
import io.qameta.allure.testng.AllureTestNg;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.yape.utils.APIUtils;

@Listeners(AllureTestNg.class)
public class PingTests {
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        APIUtils.createToken("admin", "password123").then().statusCode(200);
    }
    @Test
    public void testHealthCheck() {
        APIUtils.pingHealthCheck()
                .then()
                .statusCode(201);
    }
}
