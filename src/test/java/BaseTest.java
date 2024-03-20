
import io.qameta.allure.testng.AllureTestNg;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.yape.utils.APIUtils;
import org.yape.utils.ConfigManager;

@Listeners(AllureTestNg.class)
public class BaseTest {
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigManager.getBaseURL();
        System.out.printf("hola como estas");
        APIUtils.pingHealthCheck().then().statusCode(201);
    }
}
