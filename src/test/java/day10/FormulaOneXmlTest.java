package day10;


import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

//RETRIEVE DATA FROM XML FILE
//RETRIEVE THE ATTRIBUTE VALUES.

public class FormulaOneXmlTest {


    @BeforeAll
    public static void setup() {

        //restassured automaticllay combine baseURI and basePath and give the result
        //if there is stmh new in end point we should add to these.

        baseURI = "http://ergast.com";
        basePath = "/api/f1";


    }

    @Test
    public void test1() {

        Response response = given()
                .pathParam("driver", "alonso")
                .when()
                .get("/drivers/{driver}")
                .then()
                .statusCode(200).log().all()
                .extract().response();

        XmlPath xmlPath = response.xmlPath();

        System.out.println("xmlPath.getString(\"MRData.DriverTable.Driver\") = " + xmlPath.getString("MRData.DriverTable.Driver"));

        System.out.println("xmlPath.getString(\"MRData.DriverTable.Driver.Nationality\") = " + xmlPath.getString("MRData.DriverTable.Driver.Nationality"));

        //ATTRIBUTE  ==>> AFTER @ SIGN WE DEFINE ATTRIBUTE NAME; LIKE BELOW;
        //MRData.DriverTable.Driver.@driverId
        String id = xmlPath.getString("MRData.DriverTable.Driver.@driverId");

        System.out.println("id = " + id);

        String code = xmlPath.getString("MRData.DriverTable.Driver.@code");

        String url = xmlPath.getString("MRData.DriverTable.Driver.@url");

        System.out.println("url = " + url);


    }


}
