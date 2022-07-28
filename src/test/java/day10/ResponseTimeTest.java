package day10;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import utilities.SpartanAuthTestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class ResponseTimeTest extends SpartanAuthTestBase {

    @Test
    public void test1() {

        //GİVES us the response time
        //we can control the time

        Response response = given()
                .auth().basic("admin", "admin")
                .accept(ContentType.JSON)
                .get("/api/spartans")
                .then()
                //.time(lessThanOrEqualTo(3000L))
                .time(both(greaterThan(500L)).and(lessThanOrEqualTo(3500L)))
                //GİVİNG A RANGE to check
                .extract().response();

        System.out.println("response.getTime() = " + response.getTime());

    }


}
