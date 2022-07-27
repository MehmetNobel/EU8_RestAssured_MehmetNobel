package z_oscar.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
//automatically brings getters setters and toString methods.

@JsonIgnoreProperties(ignoreUnknown = true)
public class Postcode {

    @JsonProperty("post code")
    private String postcode;

    @JsonProperty("country")
    private String country;

    @JsonProperty("country abbreviation")
    private String country_abbreviation;

    //this is important to define inside list as Place object; otherwise it will not work.
    @JsonProperty("places")
    private List<Place> places;



}
