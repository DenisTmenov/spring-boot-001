package com.epam.jmp.springadvancepet01.repository;

import org.springframework.data.repository.CrudRepository;

import com.epam.jmp.springadvancepet01.persistence.entity.Pet;

public interface PetRepository extends CrudRepository<Pet, Long>
{

}
