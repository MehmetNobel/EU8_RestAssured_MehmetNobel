package day10;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class GovXmlTest {

    //send a get request to
    //https://data.ct.gov/api/views/qm34-pq7e/rows.xml
    //get all the years
    //get all unknowns
    //get 2005 other
    //get 2007 _address

    @Test
    public void test1(){

        Response response = given().accept(ContentType.XML)
                .when().get("https://data.ct.gov/api/views/qm34-pq7e/rows.xml")
                .then()
                .statusCode(200).extract().response();

        XmlPath xmlPath = response.xmlPath();

        xmlPath.prettyPrint();


    }






}
