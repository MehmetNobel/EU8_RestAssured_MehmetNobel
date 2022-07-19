package day6;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import pojo.Region;
import utilities.HRTestBase;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class ORDSPojoGetRequestTest extends HRTestBase {

    @Test
    public void regionTest(){

        JsonPath jsonPath = given().accept(ContentType.JSON).get("/regions").then()
                .statusCode(200).extract().jsonPath();

        //we are converting the first element to the region class.
        Region regionObject = jsonPath.getObject("items[0]", Region.class);

        System.out.println("regionObject = " + regionObject);

        System.out.println("regionObject.getRegion_id() = " + regionObject.getRegion_id());

        System.out.println("regionObject.getRegion_name() = " + regionObject.getRegion_name());

        System.out.println("regionObject.getLinks().get(0) = " + regionObject.getLinks().get(0));

        System.out.println("regionObject.getLinks().get(0).getHref() = " + regionObject.getLinks().get(0).getHref());


    }
}
