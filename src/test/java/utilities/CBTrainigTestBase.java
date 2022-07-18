package utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class CBTrainigTestBase {

    //BeforeAll is a annotation equals to @BeforeClass in testNg, we use with static method name.
    @BeforeAll
    public static void init() {
        RestAssured.baseURI = ConfigurationReader.getProperty("cbTrainingUrl");


    }
}