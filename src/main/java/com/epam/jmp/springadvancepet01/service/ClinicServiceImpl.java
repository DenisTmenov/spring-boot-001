package com.epam.jmp.springadvancepet01.service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.jmp.springadvancepet01.persistence.entity.Owner;
import com.epam.jmp.springadvancepet01.persistence.entity.Pet;
import com.epam.jmp.springadvancepet01.persistence.entity.PetType;
import com.epam.jmp.springadvancepet01.persistence.entity.Visit;
import com.epam.jmp.springadvancepet01.repository.OwnerRepository;
import com.epam.jmp.springadvancepet01.repository.PetRepository;
import com.epam.jmp.springadvancepet01.repository.PetTypeRepository;
import com.epam.jmp.springadvancepet01.repository.VisitRepository;

@Service
public class ClinicServiceImpl implements ClinicService
{

	private PetRepository petRepository;
	private PetTypeRepository petTypeRepository;
	private OwnerRepository ownerRepository;
	private VisitRepository visitRepository;

	@Override
	@Transactional(readOnly = true)
	public Collection<PetType> findPetTypes() throws DataAccessException
	{
		return StreamSupport
				.stream(petTypeRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Owner findOwnerById(Long id) throws DataAccessException
	{
		return ownerRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Owner> findOwnerByLastName(String lastName) throws DataAccessException
	{
		return ownerRepository.findByLastName(lastName);
	}

	@Override
	@Transactional
	public void saveOwner(Owner owner) throws DataAccessException
	{
		ownerRepository.save(owner);
	}

	@Override
	@Transactional
	public void saveVisit(Visit visit) throws DataAccessException
	{
		visitRepository.save(visit);
	}

	@Override
	@Transactional(readOnly = true)
	public Pet findPetById(Long id) throws DataAccessException
	{
		return petRepository.findOne(id);
	}

	@Override
	@Transactional
	public void savePet(Pet pet) throws DataAccessException
	{
		petRepository.save(pet);
	}

	@Override
	public Collection<Visit> findVisitsByPetId(Long petId)
	{
		return visitRepository.findByPetId(petId);
	}

	@Autowired
	public void setOwnerRepository(OwnerRepository ownerRepository)
	{
		this.ownerRepository = ownerRepository;
	}

	@Autowired
	public void setPetRepository(PetRepository petRepository)
	{
		this.petRepository = petRepository;
	}

	@Autowired
	public void setPetTypeRepository(PetTypeRepository petTypeRepository)
	{
		this.petTypeRepository = petTypeRepository;
	}

	@Autowired
	public void setVisitRepository(VisitRepository visitRepository)
	{
		this.visitRepository = visitRepository;
	}
}
