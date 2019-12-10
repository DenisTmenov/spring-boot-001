package com.epam.jmp.springadvancepet01.repository;

import org.springframework.data.repository.CrudRepository;

import com.epam.jmp.springadvancepet01.persistence.entity.PetType;

public interface PetTypeRepository extends CrudRepository<PetType, Long>
{
}
