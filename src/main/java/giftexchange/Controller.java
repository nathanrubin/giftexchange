package giftexchange;

import giftexchange.model.Connection;
import giftexchange.model.PeopleMapping;
import giftexchange.model.Person;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/giftexchange")
public class Controller {

    private final AtomicLong counter = new AtomicLong();
    private final List<Person> people = new ArrayList<>();

    @RequestMapping("/names/match")
    public PeopleMapping findMatchForNames(@RequestParam(value="names") String[] names){
        people.clear();
        for (String name : names){
            people.add(new Person(String.valueOf(counter.incrementAndGet()), name));
        }

        final List<Connection> connections = generateMappingsAlternative(people);

        return new PeopleMapping(people, connections);
    }

    private List<Connection> generateMappings(final List<Person> people) {
        final List<Connection> connections = new ArrayList<>();
        Collections.shuffle(people);
        for (int i=0; i<people.size(); i++) {

            final Connection connection;
            if (i==(people.size()-1)) {
                // Last person should point to the first
                connection = new Connection(people.get(i).getId(), people.get(0).getId());
            }
            else {
                connection = new Connection(people.get(i).getId(), people.get(i+1).getId());
            }
            connections.add(connection);
        }

        return connections;
    }

    private List<Connection> generateMappingsAlternative(final List<Person> people) {
        final List<Connection> connections = new ArrayList<>();
        final List<Person> from = new ArrayList<>(people);
        final List<Person> to = new ArrayList<>(people);
        final Person lastPerson = from.get(from.size()-1);

        for (Person person : from) {
            Collections.shuffle(to);

            // Make sure the current person and last person don't get stuck buying for themselves
            int target = 0;
            if (person.equals(to.get(target))){
                target++;
            }
            else if (to.size() == 2) {
                if (lastPerson.equals(to.get(target+1))){
                    target++;
                }
            }

            Person receiver = to.remove(target);
            connections.add(new Connection(person.getId(), receiver.getId()));
        }

        return connections;
    }
}