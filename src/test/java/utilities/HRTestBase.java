package utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

//since we do not create any objects we make these abstract class.
public abstract class HRTestBase {

    //BeforeAll is a annotation equals to @BeforeClass in testNg, we use with static method name.
    @BeforeAll
    public static void init(){
        RestAssured.baseURI=ConfigurationReader.getProperty("hrUrl");

    }

}
