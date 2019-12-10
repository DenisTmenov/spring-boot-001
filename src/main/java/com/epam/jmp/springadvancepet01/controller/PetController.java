package com.epam.jmp.springadvancepet01.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epam.jmp.springadvancepet01.persistence.entity.Owner;
import com.epam.jmp.springadvancepet01.persistence.entity.Pet;
import com.epam.jmp.springadvancepet01.persistence.entity.PetType;
import com.epam.jmp.springadvancepet01.service.ClinicService;
import com.epam.jmp.springadvancepet01.validator.PetValidator;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController
{

	private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
	private ClinicService clinicService;

	@ModelAttribute("types")
	public Collection<PetType> populatePetTypes()
	{
		return this.clinicService.findPetTypes();
	}

	@ModelAttribute("owner")
	public Owner findOwner(@PathVariable("ownerId") Long ownerId)
	{
		return this.clinicService.findOwnerById(ownerId);
	}

	@InitBinder("owner")
	public void initOwnerBinder(WebDataBinder dataBinder)
	{
		dataBinder.setDisallowedFields("id");
	}

	@InitBinder("pet")
	public void initPetBinder(WebDataBinder dataBinder)
	{
		dataBinder.setValidator(new PetValidator());
	}

	@GetMapping(value = "/pets/new")
	public String initCreationForm(Owner owner, ModelMap model)
	{
		Pet pet = new Pet();
		owner.addPet(pet);
		model.put("pet", pet);
		return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/pets/new")
	public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult result, ModelMap model)
	{
		if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null)
		{
			result.rejectValue("name", "duplicate", "already exists");
		}
		if (result.hasErrors())
		{
			model.put("pet", pet);
			return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
		}
		else
		{
			owner.addPet(pet);
			this.clinicService.savePet(pet);
			return "redirect:/owners/{ownerId}";
		}
	}

	@GetMapping(value = "/pets/{petId}/edit")
	public String initUpdateForm(@PathVariable("petId") Long petId, ModelMap model)
	{
		Pet pet = this.clinicService.findPetById(petId);
		model.put("pet", pet);
		return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/pets/{petId}/edit")
	public String processUpdateForm(@Valid Pet pet, BindingResult result, Owner owner, ModelMap model)
	{
		if (result.hasErrors())
		{
			model.put("pet", pet);
			return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
		}
		else
		{
			pet.setOwner(owner);
			this.clinicService.savePet(pet);
			return "redirect:/owners/{ownerId}";
		}
	}

	@Autowired
	public void setClinicService(ClinicService clinicService)
	{
		this.clinicService = clinicService;
	}
}
