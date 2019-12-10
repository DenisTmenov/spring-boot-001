package com.epam.jmp.springadvancepet01.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

import com.epam.jmp.springadvancepet01.persistence.entity.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long>
{

	Collection<Owner> findByLastName(String lastName) throws DataAccessException;

	Owner findById(Long id) throws DataAccessException;

}
