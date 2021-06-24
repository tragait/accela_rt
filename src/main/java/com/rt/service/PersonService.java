package com.rt.service;

import com.rt.model.Address;
import com.rt.model.Person;
import com.rt.repository.PersonJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonJpaRepository personJpaRepository;

    public Person getPerson(int id){
        final Person p  =  personJpaRepository.findById(id).get();
        return p;
    }

    public boolean isExist(int id){
        return personJpaRepository.existsById(id);
    }

    public Person savePerson(Person person){
        return personJpaRepository.save(person);
    }

    public List<Person> getAll(){
        return personJpaRepository.findAll();
    }

    public void delete(int id)
    {
        personJpaRepository.deleteById(id);
    }

    public void addAddressToPerson(int id, Address address){
        Person p = personJpaRepository.findById(id).get();
        p.addAddressesItem(address);
        personJpaRepository.save(p);
    }

}
