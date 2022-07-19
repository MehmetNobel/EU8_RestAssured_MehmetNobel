package day5;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.DBUtils;
import utilities.SpartanTestBase;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class SpartanAPIvsDB extends SpartanTestBase {

    @DisplayName("Get one Spartan from api and database")
    @Test
    public void testDB1() {

        //get id, name, gender, phone from database
        //get id, name, gender, phone from api
        //compare

        //1- writing query for the retrieving data from databse
        String query="select spartan_id,name,gender,phone from spartans where spartan_id=15";

        Map<String, Object> dbMap = DBUtils.getRowMap(query);

        System.out.println("dbMap = " + dbMap);

        //2.getting the data from the api.. dirertly passed as map
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .and().contentType("application/json")
                .extract().response();

        //deserialization
        Map<String,Object> apiMap=response.as(Map.class);

        System.out.println("apiMap = " + apiMap);


        //3-compare database vs api. we assume expected result is db
        assertThat(apiMap.get("id").toString(),is(dbMap.get("SPARTAN_ID").toString()));
        assertThat(apiMap.get("name"),is(dbMap.get("NAME")));
        assertThat(apiMap.get("gender"),is(dbMap.get("GENDER")));
        assertThat(apiMap.get("phone").toString(),is(dbMap.get("PHONE").toString()));


    }


}
