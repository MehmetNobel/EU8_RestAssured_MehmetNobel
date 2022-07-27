package z_oscar;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import z_oscar.pojo.Postcode;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;

public class CollectionExampleZip extends ZippoTestBase {

    @Test
    public void test1() {

        /*Response response = given().accept(ContentType.JSON)
                .when().pathParam("zip", 22031)
                .get("/{zip}");

         */

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().pathParam("state", "MA")
                .and().pathParam("city", "BELMONT")
                .when().get("/{state}/{city}")
                .then().statusCode(200).contentType("application/json")
                .extract().jsonPath();


        List<Map<String, Object>> list = jsonPath.getList("places");

        System.out.println("list = " + list);

        for (Map<String, Object> each : list) {

            System.out.println("each.get(\"latitude\") = " + each.get("latitude"));
        }

    }

    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON)
                .when().pathParam("zip", 22031)
                .get("/{zip}");

        response.prettyPrint();


        //deserialization   : json to java  ==>> converting json format to java as a map

        Map<String, Object> map1 = response.as(Map.class);

        System.out.println("map1 = " + map1);

        Assertions.assertEquals("United States", map1.get("country"));

        JsonPath jsonPath = response.jsonPath();

        List<Map<String, Object>> list = jsonPath.getList("places");

        System.out.println("list.get(0).get(\"state\") = " + list.get(0).get("state"));


    }


}
