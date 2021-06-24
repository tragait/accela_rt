package com.rt.rest;

import com.rt.model.Address;
import com.rt.model.Person;
import com.rt.rest.api.PersonApi;
import com.rt.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
public class PersonRestController implements PersonApi {

    @Autowired
    private PersonService personService;

    @Override
    public ResponseEntity<Void> deletePerson(Integer id) {

        if(!personService.isExist(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        personService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Person>> getAllPerson() {
        return new ResponseEntity<>(personService.getAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Person> getPerson(Integer id) {
        if(!personService.isExist(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(personService.getPerson(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> savePerson(@Valid Person body) {

        personService.savePerson(body);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> updatePerson(@Valid Person body) {
        final boolean isExist = personService.isExist(body.getId());
        personService.savePerson(body);

        if(isExist)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> addAddressToPerson(Integer personId, @Valid Address body) {
        personService.addAddressToPerson(personId, body);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
