package com.rt.service;

import com.rt.model.Address;
import com.rt.model.Person;
import com.rt.repository.AddressJpaRepository;
import com.rt.repository.PersonJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressJpaRepository addressJpaRepository;

    public Address getAddress(int id){
       final Address a =  addressJpaRepository.findById(id).get();
       return a;
    }

    public Address saveAddress(Address address){
        return addressJpaRepository.save(address);
    }

    public List<Address> getAll(){
        return addressJpaRepository.findAll();
    }

    public boolean isExist(int id){
        return addressJpaRepository.existsById(id);
    }
    public void delete(int id){
    {
        final Address add = getAddress(id);
        add.getPerson().getAddresses().remove(add);
        addressJpaRepository.delete(add);
    }
}
}
