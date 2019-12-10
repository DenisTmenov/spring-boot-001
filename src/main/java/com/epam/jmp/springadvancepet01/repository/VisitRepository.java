package com.epam.jmp.springadvancepet01.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.epam.jmp.springadvancepet01.persistence.entity.Visit;

public interface VisitRepository extends CrudRepository<Visit, Long>
{
	@Query("SELECT v FROM Visit v where v.pet.id= :petId")
	List<Visit> findByPetId(@Param("petId") Long petId);
}
