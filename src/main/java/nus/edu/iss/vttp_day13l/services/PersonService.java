package nus.edu.iss.vttp_day13l.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.edu.iss.vttp_day13l.models.Person;
import nus.edu.iss.vttp_day13l.repo.PersonRepo;

@Service
public class PersonService {
    @Autowired
    PersonRepo personRepo;
    
    public List<Person> findAll() {
        return personRepo.findAll();
    }

    public Boolean create(Person person) {
        return personRepo.create(person);
    }

    public Boolean delete(Person person){
        return personRepo.delete(person);
    }

    public Boolean update(Person person){
        return personRepo.update(person);
    }
    
    public Person findById(String personId){
        return personRepo.findById(personId);
    }
}
