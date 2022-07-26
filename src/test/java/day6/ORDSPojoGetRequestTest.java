package day6;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Employe;
import pojo.Region;
import pojo.Regions;
import utilities.HRTestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;


public class ORDSPojoGetRequestTest extends HRTestBase {

    @Test
    public void regionTest(){

        JsonPath jsonPath = given().accept(ContentType.JSON).get("/regions").then()
                .statusCode(200).extract().jsonPath();

        //we are converting the first element to the region class.
        Region regionObject = jsonPath.getObject("items[0]", Region.class);

        System.out.println("regionObject = " + regionObject);

        // after the annototaions we used as Regionid
        System.out.println("regionObject.getRegion_id() = " + regionObject.getRegionid());

        // after the annototaions we used as Regionname
        System.out.println("regionObject.getRegion_name() = " + regionObject.getRegionname());

        System.out.println("regionObject.getLinks().get(0) = " + regionObject.getLinks().get(0));

        //gives the first element inside the links list.
        System.out.println("regionObject.getLinks().get(0).getHref() = " + regionObject.getLinks().get(0).getHref());

    }

    @DisplayName("GET request to /employees and only get couple of values as pojo class")
    @Test
    public void employeeGet(){

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .when().get("employees")
                .then().statusCode(200).extract().jsonPath();

        //we are getting error since we did not define all the fields.
        Employe emp1 = jsonPath.getObject("items[0]", Employe.class);

        System.out.println("emp1 = " + emp1);


        //converting to the map
        //Map<String, Object> map1=response.as(Map.class);
        //System.out.println("map1 = " + map1);


    }

  @DisplayName("GET request to /regions and only get couple of values as pojo class")
    @Test
    public void regionsGet(){

      Regions regions = given().accept(ContentType.JSON)
                       .when().get("regions")
                       .then().statusCode(200).extract().response().as(Regions.class);

      //verify count is 4
      assertThat(regions.getCount(), is(4));

      List<Region> items = regions.getItems();
      List<String> regionNames = new ArrayList<>();
      List<Integer> regionIDs = new ArrayList<>();

      System.out.println("items.size() = " + items.size());

      for (Region item : items) {

          regionNames.add(item.getRegionname());
          regionIDs.add(item.getRegionid());
      }

      List<Integer> expectedRegionIDs= Arrays.asList(1,2,3,4);
      List<String> expectedRegionNames= Arrays.asList("Europe","Americas","Asia","Middle East and Africa");

      //assertion to check both of them
      assertThat(regionNames,is(expectedRegionNames));
      assertThat(regionIDs, is(expectedRegionIDs));


  }





}
