package com.rt.service;

import com.rt.model.Address;
import com.rt.model.Person;
import com.rt.repository.PersonJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonJpaRepository personJpaRepository;

    @Autowired
    @InjectMocks
    private PersonService personService;

    @Test
    void givenPersonToAddShouldReturnAddedPerson(){
        //when
        final Person person = new Person(1, "fname", "lname");
        when(personJpaRepository.save(any())).thenReturn(person);

        //then
        personService.savePerson(person);

        //verify
        verify(personJpaRepository,times(1)).save(person);
    }

    @Test
    public void givenIdThenShouldReturnPersonOfThatId() {
        final Person person = new Person(1, "fname", "lname");
        when(personJpaRepository.findById(1)).thenReturn(Optional.ofNullable(person));

        Assertions.assertEquals(personService.getPerson(person.getId()), person);
        verify(personJpaRepository,times(1)).findById(Integer.valueOf(1));
    }

    @Test
    public void givenIdThenShouldDeletePersonOfThatId(){
        final Person person = new Person(1, "fname", "lname");
        personService.delete(1);
        verify(personJpaRepository,times(1)).deleteById(Integer.valueOf(1));
    }

    @Test
    void givenAddressToAddShouldReturnAddedAddress(){
        //when
        final Person person = new Person(1, "fname", "lname");
        final Address address = new Address(1, "street", "state", "city", "postalcode");
        when(personJpaRepository.findById(1)).thenReturn(Optional.ofNullable(person));
        when(personJpaRepository.save(any())).thenReturn(person);

        //then
        personService.addAddressToPerson(1, address);

        //verify
        verify(personJpaRepository,times(1)).save(person);
        verify(personJpaRepository,times(1)).findById(Integer.valueOf(1));

    }

    @Test
    public void givenPersonListThenShouldReturnAllPerson() {
        //when
        final List<Person> personList = new ArrayList<>();
        when(personJpaRepository.findAll()).thenReturn(personList);

        //then
        //verify
        Assertions.assertEquals(personService.getAll(), personList);
        verify(personJpaRepository,times(1)).findAll();
    }

}
