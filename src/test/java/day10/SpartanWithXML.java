package day10;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.SpartanAuthTestBase;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class SpartanWithXML extends SpartanAuthTestBase {


    @DisplayName("GET request to /api/spartans and verify xml")
    @Test
    public void getSpartanXml() {

        //we move inside the xml like that:  List.item[index no].name

                 given().accept(ContentType.XML)
                         .auth().basic("admin", "admin")
                .when().get("/api/spartans")
                .then().statusCode(200)
                         .contentType("application/xml;charset=UTF-8")   // we verify the content type
                         .body("List.item[0].gender", is("Male"))
                         .body("List.item[0].name", is("Meade"))   // we are using hemcrest matchers method here.
                         .body("List.item[1].name", is("Nels"))
                        .log().all();


    }

    @DisplayName("GET request to /api/spartans and verify xmlPath")
    @Test
    public void getSpartanXmlPath() {


      Response response = given().accept(ContentType.XML)
                                    .auth().basic("admin", "admin")
                         .when().get("/api/spartans");

      XmlPath xmlPath = response.xmlPath();

      System.out.println("xmlPath.get(\"List.item[0].gender\") = " + xmlPath.get("List.item[0].gender"));

        int id = xmlPath.getInt("List.item[2].id");

        System.out.println("id = " + id);

        //how to get all names and store in a list of string
        //since we did not point any index so it gives us all the names as a list.
        List<String > names = xmlPath.getList("List.item.name");

        System.out.println("names = " + names);

    }


}
