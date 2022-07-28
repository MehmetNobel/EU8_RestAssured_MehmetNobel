package day10;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.SpartanAuthTestBase;

import java.io.File;

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
                         //if our file is under resources folder then automatically will get from there.
                         .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json"))
                         .log().all();



    }
    @DisplayName("Get request to verify all spartan against schema")
    @Test
    public void allSpartanSchemaValidation() {

                 given()
                        .accept(ContentType.JSON)
                        .and()
                        .auth().basic("admin", "admin")
                .when()
                        .get("/api/spartans")
                .then()
                         .statusCode(200)
                         //we are getting the file from day10 package .. right click copy
                         //what if the schema .json file is in another  package we can get/give the path like that.
                         .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/day10/allSpartansSchema.json")))
                         .log().all();



    }


}
