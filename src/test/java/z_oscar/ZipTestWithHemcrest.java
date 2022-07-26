package z_oscar;


import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;


public class ZipTestWithHemcrest extends ZippoTestBase {

    @Test
    public void hemcrestTest() {

        // log all gives all the details about request header and response header
        //this part is not related with hemcrest actually

        given().log().all()
                .accept(ContentType.JSON)
                .and().pathParam("zip", 22031)
                .get("/{zip}")
                .then()
                .assertThat().statusCode(200)
                .and().contentType("application/json")
                .and().assertThat().header("Server", equalTo("cloudflare"))  //hemcrest
                .assertThat().header("Report-To", notNullValue())   //hemcrest
                .body("country", equalTo("United States"), "\'country abbreviation\'", is("US"))
                .log().all();

        // we get the all the response with log all method.
        //checking body with hemcrestmatchers method.
        // WE CAN WRITE LOTS OF THINGS INSIDE BODY


    }
}
