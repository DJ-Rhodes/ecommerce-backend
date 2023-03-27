package com.djrhodes.ecommercebackend.model.repository;

import com.djrhodes.ecommercebackend.model.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

/**
 * Data Access Object for the Address data.
 */
public interface AddressRepository extends ListCrudRepository<Address, Long> {

    List<Address> findByUser_Id(Long id);

}
