package day8;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.SpartanAuthTestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class SpartanWithAuthTests extends SpartanAuthTestBase {


    @DisplayName("GET /api/spartans as a public user (guest) and expect 401")
    @Test
    public void test1() {
        given().accept(ContentType.JSON)
                .when()
                .get("/api/spartans")
                .then().statusCode(401)
                .log().all();


    }


    @DisplayName("GET /api/spartans as admin user and expect 200")
    @Test
    public void testAdmin() {
        given()
                .auth().basic("admin","admin")
                .and().accept(ContentType.JSON)
        .when()
                .get("/api/spartans")
        .then()
                .statusCode(200)
                .log().all();

    }










}
