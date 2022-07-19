package pojo;

import java.util.List;

public class Search {

    private List<Spartan> content;
    private String totalElement;


    public List<Spartan> getContent() {
        return content;
    }

    public void setContent(List<Spartan> content) {
        this.content = content;
    }

    public String getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(String totalElement) {
        this.totalElement = totalElement;
    }

    @Override
    public String toString() {
        return "Search{" +
                "content=" + content +
                ", totalElement='" + totalElement + '\'' +
                '}';
    }
}
