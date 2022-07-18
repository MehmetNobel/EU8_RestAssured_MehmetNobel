package utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

//since we do not create any objects we make these abstract class.
public abstract class SpartanTestBase {

    //BeforeAll is a annotation equals to @BeforeClass in testNg, we use with static method name.
    @BeforeAll
    public static void init() {
        baseURI = ConfigurationReader.getProperty("spartanUrl");

        String dbUrl = ConfigurationReader.getProperty("spartanDataBase");
        String dbUsername = ConfigurationReader.getProperty("spartanUserName");
        String dbPassword = ConfigurationReader.getProperty("spartanPassword");

        DBUtils.createConnection(dbUrl,dbUsername,dbPassword);

    }

    @AfterAll
    public static void tearDown(){

        DBUtils.destroy();
    }







}
