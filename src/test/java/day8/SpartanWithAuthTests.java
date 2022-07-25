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
    public void test1() {   //FAILED
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
@DisplayName("DELETE /api/spartans{id} as editor user expect 403")
    @Test
    public void testEditorDelete() {   //FAILED
        given()
                .auth().basic("editor", "editor")
                .and().accept(ContentType.JSON)
                .and().pathParam("id", 30)
       .when()
                .delete("/api/spartans/{id}")
       .then()
                .statusCode(403)
                .log().body();
    }



}
