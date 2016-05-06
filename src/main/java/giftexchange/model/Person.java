package giftexchange.model;

import org.springframework.data.annotation.Id;

public class Person {

    @Id private String id;

    private String label;

    public Person(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}