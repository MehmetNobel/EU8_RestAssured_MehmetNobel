package z_oscar.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pojo.Link;

import java.util.List;

@Getter
@Setter
@ToString
//automatically brings getters setters and toString methods.

/*
            "place name": "Fairfax",
            "longitude": "-77.2649",
            "state": "Virginia",
            "state abbreviation": "VA",
            "latitude": "38.8604"

 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Place {
    @JsonProperty("place name")
    private String place_name;

    @JsonProperty("longitude")
    private String longitude;

    @JsonProperty("state")
    private String state;

    @JsonProperty("state abbreviation")
    private String state_abbreviation;

    @JsonProperty("latitude")
    private String latitude;







}
