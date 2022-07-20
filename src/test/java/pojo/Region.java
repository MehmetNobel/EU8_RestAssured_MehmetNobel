package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
//automatically brings getters setters and toString methods.

public class Region {

    //region_id
    @JsonProperty("region_id")   // with that we say that; use the argument as regionid for "region_id" parameter
    private int regionid;
    @JsonProperty("region_name")
    private String regionname;
    private List<Link> links;




}
