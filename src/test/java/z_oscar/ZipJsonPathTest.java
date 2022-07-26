package z_oscar;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;



public class ZipJsonPathTest extends ZippoTestBase {


    @DisplayName("zip test")
    @Test
    public void test1() {

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().pathParam("state", "MA")
                .and().pathParam("city", "BELMONT")
                .when().get("/{state}/{city}")
                .then().statusCode(200).contentType("application/json")
                .extract().jsonPath();


        jsonPath.prettyPrint();

        System.out.println("jsonPath.getString(\"places[0]\") = " + jsonPath.getString("places[0]"));

        assertEquals("US",jsonPath.getString("\'country abbreviation\'"));

        //storing the elements in a list.
        List<String> expectedZips=new ArrayList<>(Arrays.asList("02178","02478"));

        List<String> actualZips=jsonPath.getList("places.\'post code\'");

        //assert with assertions method.
        assertEquals(expectedZips,actualZips);

        System.out.println("expectedZips = " + expectedZips);

        System.out.println("actualZips = " + actualZips);

        //assert with hemcrestmatchers method.
        assertThat(expectedZips, is(equalTo(actualZips)));

        List<Object> list = jsonPath.getList("places.\'post code\'");

        System.out.println("list.size() = " + list.size());

        System.out.println("list = " + list);

        //get the latitude where post code is 02178.
        String string = jsonPath.getString("places.findAll {it.\'post code\'==\"02178\"}.latitude");

        System.out.println("string = " + string);


    }


}
