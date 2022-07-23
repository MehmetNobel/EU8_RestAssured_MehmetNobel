package utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class SpartanAuthTestBase {

    //BeforeAll is a annotation equals to @BeforeClass in testNg, we use with static method name.
    @BeforeAll
    public static void init() {
        baseURI = ConfigurationReader.getProperty("spartanAuthUrl");


    }

    @AfterAll
    public static void tearDown(){

        // DBUtils.destroy();
    }



}
