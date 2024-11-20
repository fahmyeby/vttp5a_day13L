package nus.edu.iss.vttp_day13l.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import nus.edu.iss.vttp_day13l.models.Person;

@Repository
public class PersonRepo {
    public List<Person> persons = new ArrayList<>();

    public List<Person> findAll() {
        return persons;
    }

    public Boolean create(Person person) {
        if(person == null) return false;
        persons.add(person);
        return true;
    }

    public Boolean delete(Person person) {
        int ifFoundPerson = persons.indexOf(person);

        if (ifFoundPerson >= 0) {
            persons.remove(person);
            return true;
        }

        return false;
    }

    public Boolean update(Person person) {
        List<Person> filteredPerson = persons.stream().filter(p -> p.getId().equals(person.getId()))
                .collect(Collectors.toList());

        if (filteredPerson.size() > 0) {
            persons.remove(filteredPerson.getFirst());
            persons.add(person);
            return true;
        }

        return false;
    }

    public Person findById(String personId) {
        Person foundPerson = persons.stream().filter(p -> p.getId().equals(personId)).findFirst().get();
        return foundPerson;
    }

}
