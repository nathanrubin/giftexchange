package giftexchange.model;

import java.util.List;

public class PeopleMapping {
    private List<Person> people;
    private List<Connection> connections;

    public PeopleMapping(List<Person> people, List<Connection> connections) {
        this.people = people;
        this.connections = connections;
    }

    public List<Person> getPeople() {
        return people;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    @Override
    public String toString() {
        return "PeopleMapping{" +
                "people=" + people +
                ", connections=" + connections +
                '}';
    }
}
