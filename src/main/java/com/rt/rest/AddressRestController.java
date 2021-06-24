package com.rt.rest;

import com.rt.model.Address;
import com.rt.model.Person;
import com.rt.rest.api.AddressApi;
import com.rt.service.AddressService;
import com.rt.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.List;


@RestController
public class AddressRestController implements AddressApi {

    @Autowired
    private AddressService addressService;

    @Override
    public ResponseEntity<Void> deleteAddress(Integer id) {
        addressService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Address> getAddress(Integer id) {
        if(!addressService.isExist(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(addressService.getAddress(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Address>> getAllAddress() {

        return new ResponseEntity<>(addressService.getAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateAddress(@Valid Address body) {

        if(!addressService.isExist(body.getId()))
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        final Address add = addressService.getAddress(body.getId());
        add.setCity(body.getCity());
        add.setPostalcode(body.getPostalcode());
        add.setState(body.getState());
        add.setStreet(body.getStreet());
        addressService.saveAddress(add);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
