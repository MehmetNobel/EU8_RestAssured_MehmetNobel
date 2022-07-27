package z_oscar;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import z_oscar.pojo.Postcode;

import static io.restassured.RestAssured.given;

public class ExampleWithPojo extends ZippoTestBase{

    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .when().pathParam("zip", 22031)
                .get("/{zip}");


        Postcode postcode = response.as(Postcode.class);

        System.out.println("postcode.getCountry() = " + postcode.getCountry());

        System.out.println("postcode.getCountry_abbreviation() = " + postcode.getCountry_abbreviation());

        System.out.println("postcode = " + postcode);

        System.out.println("postcode.getPlaces().size() = " + postcode.getPlaces().size());

        System.out.println("postcode.getPlaces() = " + postcode.getPlaces());

        System.out.println("postcode.getPlaces().get(0).getLatitude() = " + postcode.getPlaces().get(0).getLatitude());

        System.out.println("postcode.getPlaces().get(0) = " + postcode.getPlaces().get(0));


        //response.prettyPrint();

        //deserialization   : json to java  ==>> converting json format to java as a map

    }
}