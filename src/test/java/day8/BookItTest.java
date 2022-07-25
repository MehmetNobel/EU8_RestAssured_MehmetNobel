package day8;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class BookItTest {

    //BeforeAll is a annotation equals to @BeforeClass in testNg, we use with static method name.
    @BeforeAll
    public static void init() {
        baseURI = ConfigurationReader.getProperty("bookitUrl");

    }

    String accessToken="Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMzkiLCJhdWQiOiJzdHVkZW50LXRlYW0tbGVhZGVyIn0._vM1-eRoS7SsHu6T-QPdJoEdA8LSwnxUvvTTbhV-8ms";

    @DisplayName("GET all campuses")
    @Test
    public void testAuth1(){  // PASSED

        //how to pass bearer token for bookit ? use header method to give as key value header

        given().
                header("Authorization",accessToken)
                .and().accept(ContentType.JSON)
       .when()
                .get("/api/campuses")
       .then()
                .statusCode(200)
                .log().all();

    }


    
 @DisplayName("GET all clusters")
    @Test
    public void testAuth2(){  // PASSED

        //how to pass bearer token for bookit ? use header method to give as key value header

        given().
                header("Authorization",accessToken)
                .and().accept(ContentType.JSON)
       .when()
                .get("/api/clusters")
       .then()
                .statusCode(200)
                .log().all();

    }





}