package com.rt.service;

import com.rt.model.Address;
import com.rt.model.Person;
import com.rt.repository.AddressJpaRepository;
import com.rt.repository.PersonJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddresserviceTest {

    @Mock
    private AddressJpaRepository addressJpaRepository;

    @Autowired
    @InjectMocks
    private AddressService addressService;

    @Test
    void givenAddressToAddShouldReturnAddedAddress(){
        //when
        final Address address = new Address(1, "street", "city", "state", "postal");
        when(addressJpaRepository.save(any())).thenReturn(address);

        //then
        addressService.saveAddress(address);

        //verify
        verify(addressJpaRepository,times(1)).save(address);
    }

    @Test
    public void givenIdThenShouldReturnAddressOfThatId() {
        //when
        final Address address = new Address(1, "street", "city", "state", "postal");
        when(addressJpaRepository.findById(1)).thenReturn(Optional.ofNullable(address));

        //then
        //verify
        Assertions.assertEquals(addressService.getAddress(address.getId()), address);
        verify(addressJpaRepository,times(1)).findById(Integer.valueOf(1));
    }

    @Test
    public void givenAddressListThenShouldReturnAllAddress() {
        //when
        final List<Address> addressList = new ArrayList<>();
        when(addressJpaRepository.findAll()).thenReturn(addressList);

        //then
        //verify
        Assertions.assertEquals(addressService.getAll(), addressList);
        verify(addressJpaRepository,times(1)).findAll();
    }

    @Test
    public void givenIdThenShouldDeleteAddressOfThatId() throws NoSuchFieldException, IllegalAccessException {
        //when
        final Address address = new Address(1, "street", "city", "state", "postal");
        final Person p = new Person(1, "fname", "lname");
        p.addAddressesItem(address);
        Class c = address.getClass();
        Field f = c.getDeclaredField("person");
        f.setAccessible(true);
        f.set(address, p);
        when(addressJpaRepository.findById(1)).thenReturn(Optional.ofNullable(address));

        //then
        addressService.delete(1);

        //verify
        verify(addressJpaRepository,times(1)).delete(address);
        verify(addressJpaRepository,times(1)).findById(Integer.valueOf(1));
    }
}
