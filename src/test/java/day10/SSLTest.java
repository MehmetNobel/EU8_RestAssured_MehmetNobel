package day10;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class SSLTest {


    //untrusted site accessing way 1
    @Test
    public void test1(){
        // relaxedhtml: i know it is not secure but give me results though.
        // not have ssl certificate but give me the test result.
        given().relaxedHTTPSValidation()
                .when().get("https://untrusted-root.badssl.com/").prettyPrint();


    }

    //untrusted site accessing way 2

    @Test
    public void keyStore(){

        given()
                .keyStore("pathtofile","password")
                .when().get("apiurl");

    }


}
