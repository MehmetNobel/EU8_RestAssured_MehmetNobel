package day6;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Search;
import pojo.Spartan;
import utilities.SpartanTestBase;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class SpartanPojoGetRequestTest extends SpartanTestBase {

    @DisplayName("Get one spartan and convert it to Spartan Object")
    @Test
    public void oneSpartanPojo(){

        Response response = given().accept(ContentType.JSON)
                                    .and().pathParam("id", 15)
                            .when().get("/api/spartans/{id}")
                            .then()
                                    .log().all().statusCode(200).extract().response();

        System.out.println("response.statusCode() = " + response.statusCode());

        response.prettyPrint();

        //deserialize JSON TO POJO
        //2 different way to do this.
        //1 . using as() method
        //we converted json response to spartan object with the help of jackson
        //as() method uses jackson to deserialization (json to java)
        // Map<String, Object> = response.as(Map.class);  ==>> json to java map
        Spartan spartan15=response.as(Spartan.class);   // json to Spartan POJO class

        System.out.println("spartan15 = " + spartan15);
        System.out.println("spartan15.getId() = " + spartan15.getId());
        System.out.println("spartan15.getName() = " + spartan15.getName());
        System.out.println("spartan15.getGender() = " + spartan15.getGender());
        System.out.println("spartan15.getPhone() = " + spartan15.getPhone());

        //2.way deserilization
        JsonPath jsonPath = response.jsonPath();

        //we are passing the path. to retrieve the related data.
        //we should give path to retrieve just one json object
        //more powerfull acc. to the 1.way
        Spartan s15 = jsonPath.getObject("", Spartan.class);

        System.out.println("s15 = " + s15);
        System.out.println("s15.getName() = " + s15.getName());
        System.out.println("s15.getGender() = " + s15.getGender());
        System.out.println("s15.getPhone() = " + s15.getPhone());
        System.out.println("s15.getId() = " + s15.getId());


    }

    @DisplayName("Get one spartan from search endpoint result and use POJO")
    @Test
    public void spartanSearchWithPojo(){

        // /api/spartans/search dan gelen sonucu Jackson yardımıyla
        // Search pojo sayfasına convert ediyoruz.
        //2 ana alan var : 1:content => data type list   2:total element: string

        Response response = given()
                .get("/api/spartans/search");

        JsonPath jsonPath = response.jsonPath();

        //we are storing the first element into a spartan class.
        //since we get from content a list we store the first one like that : content[0]
        Spartan sp15 = jsonPath.getObject("content[0]", Spartan.class);

        System.out.println("sp15 = " + sp15);

        //converting datas to the search object
        Search search = response.as(Search.class);

        String name = search.getContent().get(0).getName();

        System.out.println("search.getContent().get(99).getName() = " + search.getContent().get(99).getName());

        System.out.println("name = " + name);

        System.out.println("search.getContent().size() = " + search.getContent().size());

        System.out.println("search.getTotalElement() = " + search.getTotalElement());

        System.out.println("search.getContent() = " + search.getContent());


    }


    @DisplayName("Get /spartans/search and save as List<Spartan>")
    @Test
    public void spartanIntoAList(){

        //under the hood , jackson is doing the conversion
        //this is the way without creating search pojo page.
        List<Spartan> spartanList = given().queryParams("nameContains", "a"
                        , "gender", "Male")
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("content", Spartan.class);
                                            // we are storing the spartans in a list.

        String name = spartanList.get(0).getName();

        System.out.println("name = " + name);

        System.out.println("spartanList.size() = " + spartanList.size());

        System.out.println("spartanList.get(29).getName() = " + spartanList.get(29).getName());





    }






}
