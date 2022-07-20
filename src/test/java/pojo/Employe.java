package pojo;

// we  will ignore some parts below
//with :
/*@JsonIgnoreProperties(ignoreUnknown = true)
    "items": [
        {
            "employee_id": 100,
            "first_name": "Steven",
            "last_name": "King",
            "email": "SKING",
            "phone_number": "515.123.4567",
            "hire_date": "2003-06-17T00:00:00Z",
            "job_id": "AD_PRES",
            "salary": 24000,
            "commission_pct": null,
            "manager_id": null,
            "department_id": 90,
            "links": [

 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)  // we are telling that ignore the other parts

//we wanna get just 4 field not more.
public class Employe {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("job_id")
    private String jobId;

    private int salary;




}
