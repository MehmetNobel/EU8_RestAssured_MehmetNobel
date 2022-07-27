package day10;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.SpartanAuthTestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class JsonSchemaValidationTest extends SpartanAuthTestBase {

    @DisplayName("Get request to verify one spartan against schema")
    @Test
    public void schemaValidation() {

                 given()
                        .accept(ContentType.JSON)
                        .and()
                        .pathParam("id", 1)
                        .and()
                         .auth().basic("admin", "admin")
                .when()
                        .get("/api/spartans/{id}")
                .then()
                         .statusCode(200)
                         //we are comparing with the schemafile in here.
                         .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json"))
                         .log().all();



    }


}
