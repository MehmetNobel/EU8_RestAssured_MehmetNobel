package z_oscar;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;

public class ZippoTestBase {

    @BeforeAll
    public static void init(){

        baseURI="http://api.zippopotam.us";
        basePath="/us";
    }

}
