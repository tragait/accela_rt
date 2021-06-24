package com.rt.repository;

import com.rt.model.Address;
import com.rt.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class JpaRepositoryTest {

    @Autowired
    private PersonJpaRepository personJpaRepository;

    @Autowired
    private AddressJpaRepository addressJpaRepository;

    @Test
    public void givenPersonToAddShouldReturnAddedPerson(){
        final Person person = new Person(1, "myFirstName", "myLastName");
        final Address address = new Address(1, "street", "city", "state","postalcode");
        person.addAddressesItem(address);
        personJpaRepository.save(person);
        final Person fetchedPerson = personJpaRepository.findById(person.getId()).get();
        Assertions.assertNotNull(fetchedPerson);
        Assertions.assertTrue(fetchedPerson.getAddresses().contains(address));
        final Address fetchedAddress = addressJpaRepository.findById(address.getId()).get();
        Assertions.assertNotNull(fetchedAddress);
        Assertions.assertTrue(fetchedPerson.getAddresses().contains(address));
    }

    @Test
    public void givenPersonToAddAddressShouldReturnUpdatedPerson(){
        final Person person = new Person(1, "myFirstName", "myLastName");
        personJpaRepository.save(person);
        final Address address = new Address(1, "street", "city", "state","postalcode");
        person.addAddressesItem(address);
        personJpaRepository.save(person);
        final Person fetchedPerson = personJpaRepository.findById(1).get();
        Assertions.assertNotNull(fetchedPerson);
        Assertions.assertTrue(fetchedPerson.getAddresses().contains(address));
        final Address fetchedAddress = addressJpaRepository.findById(1).get();
        Assertions.assertNotNull(fetchedAddress);
    }

    @Test
    public void givenPluralPersonToAddShouldReturnPluralPerson(){
        final Person person1 = new Person(1, "myFirstName", "myLastName");
        final Person person2 = new Person(2, "myFirstName", "myLastName");
        personJpaRepository.save(person1);
        personJpaRepository.save(person2);

        List<Person> fetchedPersons = personJpaRepository.findAll();
        Assertions.assertEquals(2, fetchedPersons.size());
        Assertions.assertTrue(fetchedPersons.contains(person1));
        Assertions.assertTrue(fetchedPersons.contains(person2));
    }

    @Test
    public void givenIdTODeleteThenShouldDeleteThePerson() {
        //given
        final Person person = new Person(1, "myFirstName", "myLastName");
        personJpaRepository.save(person);

        //then
        personJpaRepository.deleteById(person.getId());

        //verify
        Optional optional = personJpaRepository.findById(person.getId());
        Assertions.assertEquals(Optional.empty(), optional);
    }

    @Test
    public void givenIdTODeleteAddressThenShouldDeleteTheAddress() {
        // given
        final Person person = new Person(1, "myFirstName", "myLastName");
        final Address address = new Address(1, "street", "city", "state","postalcode");
        person.addAddressesItem(address);
        personJpaRepository.save(person);

        //then
        addressJpaRepository.deleteById(address.getId());

        //verify
        Optional optional = addressJpaRepository.findById(1);
        Assertions.assertEquals(Optional.empty(), optional);
    }

    @Test
    public void givenAddressToUpdateShouldReturnUpdatedAddress(){
        //given
        final Person person = new Person(1, "myFirstName", "myLastName");
        final Address address = new Address(1, "street", "city", "state","postalcode");
        person.addAddressesItem(address);
        personJpaRepository.save(person);

        //then
        final Address address1 = new Address(1, "updatedstreet", "updatedcity", "updatedstate","updatedpostalcode");
        addressJpaRepository.save(address1);

        //verify
        final Address fetchedAddress = addressJpaRepository.findById(address.getId()).get();
        Assertions.assertNotNull(fetchedAddress);
        Assertions.assertEquals(fetchedAddress, address1);
    }

    @AfterEach
    public void teardown(){
        personJpaRepository.deleteAll();
        addressJpaRepository.deleteAll();
    }
}
