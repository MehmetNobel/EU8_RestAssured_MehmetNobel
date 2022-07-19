package pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
//automatically brings getters setters and toString methods.

public class Region {

    private int region_id;
    private String region_name;
    private List<Link> links;




}
