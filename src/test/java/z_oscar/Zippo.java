package z_oscar;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class Zippo extends ZippoTestBase {


    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("zip", 22031)
                .when()
                .get("/{zip}");

        response.prettyPrint();

        System.out.println("response.statusCode() = " + response.statusCode());

        System.out.println("response.header(\"Date\") = " + response.header("Date"));

        assertEquals(200, response.statusCode());

        assertEquals("application/json", response.contentType());

        System.out.println("response.header(\"Server\") = " + response.header("Server"));

        //this brings all the headers to us.
        System.out.println("response.getHeaders() = " + response.getHeaders());

        assertTrue(response.headers().hasHeaderWithName("Report-To"));

        //since it is 2 words we must put them inside \'....\'    like that.
        System.out.println("response.path(\"post code\") = " + response.path("\'post code\'"));

        //from this part we will use path method for BODY verification

        JsonPath jsonPath=response.jsonPath();

        System.out.println("jsonPath.getString(\"post code\") = " + jsonPath.getString("\'post code\'"));

        System.out.println("response.path(\"country\") = " + response.path("country"));

        System.out.println("response.path(\"\'country abbreviation\") = " + response.path("\'country abbreviation\'"));

        System.out.println("response.path(\"places[0].place name\") = " + response.path("places[0]"));

        System.out.println("response.path(\"places[0].place name\") = " + response.path("places[0].\'place name\'"));

        assertEquals("United States", response.path("country"));

        assertEquals("Virginia",response.path("places[0].state"));

        assertEquals("38.8604",response.path("places[0].latitude"));

        System.out.println("jsonPath.getString(\"country\") = " + jsonPath.getString("country"));

        System.out.println("jsonPath.getString(\"places[0].state\") = " + jsonPath.getString("places[0].state"));


    }


}
