package utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class SpartanTestBase {

    //BeforeAll is a annotation equals to @BeforeClass in testNg, we use with static method name.
    @BeforeAll
    public static void init() {
        baseURI = "http://35.171.2.97:8000";

    }







}
