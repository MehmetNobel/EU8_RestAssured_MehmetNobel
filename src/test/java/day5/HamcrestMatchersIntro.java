package day5;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//static needed methods below
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersIntro {


    @Test
    public void simpleTest1() {

        //actual 5+5 expected 10
        // 3 of them tells the same
        assertThat(5 + 5, is(10));
        assertThat(5 + 5, equalTo(10));
        assertThat(5 + 5, is(equalTo(10)));

        assertThat(3+3,is(6));
        assertThat(3+3, equalTo(6));
        assertThat(3+3,is(equalTo(6)));

        //not method
        assertThat(5 + 5, not(9));
        assertThat(5 + 5, is(not(9)));
        assertThat(5 + 5, is(not(equalTo(9))));

        // BENEFICIAL HAMCREST METHODS
        //greaterThan()
        //greaterThanOrEqualTo()
        //lessThan()
        //lessThanOrEqualTo()

        assertThat(5 + 5, is(greaterThan(9)));
    }


    @DisplayName("Assertion with String")
    @Test
    public void stringHamcrest() {

        String text = "EU8 is learning Hamcrest";

        //checking the equality
        assertThat(text,is("EU8 is learning Hamcrest"));
        assertThat(text,equalTo("EU8 is learning Hamcrest"));
        assertThat(text,is(equalTo("EU8 is learning Hamcrest")));

        //we can make it case insensetive
        assertThat(text,startsWith("EU8"));
        assertThat(text,startsWithIgnoringCase("eu8"));

        //ends with
        assertThat(text,endsWith("rest"));
        assertThat(text,endsWithIgnoringCase("REST"));

        //CONTAİNS
        assertThat(text,containsString("learning"));
        assertThat(text,containsStringIgnoringCase("LEARNing"));

        String str="  ";

        //BLANK STRING
        assertThat(str, blankString());

        //check if the trimmed str is empty string
        assertThat(str.trim(),emptyString());
    }

    @DisplayName("HAMCREST FOR COLLECTION")
    @Test
    public void testCollection(){

        List<Integer> list= Arrays.asList(1,4,5,6,89,32,54);

        //check the size of list
        assertThat(list, hasSize(7));

        //check if this list hasItem 54
        assertThat(list,hasItem(54));

        //check if the list has some ıtems
        assertThat(list,hasItems(1,4,5));

        // MAGICAL METHOD : EVERY ITEM
        //check if the item is greater than 0
        assertThat(list,everyItem(greaterThan(0)));

        //we are checking if every item ends with et:
        List<String> list2=Arrays.asList("ahmet","mehmet","muhammet");

        assertThat(list2,everyItem(endsWith("et")));








    }


}







