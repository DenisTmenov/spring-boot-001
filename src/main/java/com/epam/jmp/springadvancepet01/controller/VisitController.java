package com.epam.jmp.springadvancepet01.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.epam.jmp.springadvancepet01.persistence.entity.Pet;
import com.epam.jmp.springadvancepet01.persistence.entity.Visit;
import com.epam.jmp.springadvancepet01.service.ClinicService;

@Controller
public class VisitController
{

	private ClinicService clinicService;

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder)
	{
		dataBinder.setDisallowedFields("id");
	}

	@ModelAttribute("visit")
	public Visit loadPetWithVisit(@PathVariable("petId") Long petId)
	{
		Pet pet = this.clinicService.findPetById(petId);
		Visit visit = new Visit();
		visit.setPet(pet);
		return visit;
	}

	@GetMapping(value = "/owners/*/pets/{petId}/visits/new")
	public String initNewVisitForm(@PathVariable("petId") int petId, Map<String, Object> model)
	{
		return "pets/createOrUpdateVisitForm";
	}

	@PostMapping(value = "/owners/{ownerId}/pets/{petId}/visits/new")
	public String processNewVisitForm(@Valid Visit visit, BindingResult result)
	{
		if (result.hasErrors())
		{
			return "pets/createOrUpdateVisitForm";
		}
		else
		{
			visit.getPet().addVisit(visit);
			this.clinicService.saveVisit(visit);
			return "redirect:/owners/{ownerId}";
		}
	}

	@GetMapping(value = "/owners/*/pets/{petId}/visits")
	public String showVisits(@PathVariable Long petId, Map<String, Object> model)
	{
		model.put("visits", this.clinicService.findPetById(petId).getVisits());
		return "visitList";
	}

	@Autowired
	public void setClinicService(ClinicService clinicService)
	{
		this.clinicService = clinicService;
	}
}
