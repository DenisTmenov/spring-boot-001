package com.epam.jmp.springadvancepet01.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import com.epam.jmp.springadvancepet01.persistence.entity.Owner;
import com.epam.jmp.springadvancepet01.persistence.entity.Pet;
import com.epam.jmp.springadvancepet01.persistence.entity.PetType;
import com.epam.jmp.springadvancepet01.persistence.entity.Visit;

public interface ClinicService
{

	Collection<PetType> findPetTypes() throws DataAccessException;

	Owner findOwnerById(Long id) throws DataAccessException;

	Pet findPetById(Long id) throws DataAccessException;

	void savePet(Pet pet) throws DataAccessException;

	void saveVisit(Visit visit) throws DataAccessException;

	Collection<Owner> findOwnerByLastName(String lastName) throws DataAccessException;

	void saveOwner(Owner owner) throws DataAccessException;

	Collection<Visit> findVisitsByPetId(Long petId);

}
