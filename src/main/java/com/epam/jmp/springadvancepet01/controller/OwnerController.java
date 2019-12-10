package com.epam.jmp.springadvancepet01.controller;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.jmp.springadvancepet01.persistence.entity.Owner;
import com.epam.jmp.springadvancepet01.service.ClinicService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class OwnerController
{
	private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
	private ClinicService clinicService;

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder)
	{
		log.info("@InitBinder add bind field = id");
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/owners/new")
	public String initCreationForm(Map<String, Object> model)
	{
		log.info("Create new 'Owner' and put to model");
		Owner owner = new Owner();
		model.put("owner", owner);
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/owners/new")
	public String processCreationForm(@Valid Owner owner, BindingResult result)
	{
		log.info("Check 'Owner' for error");
		if (result.hasErrors())
		{
			log.error("Owner has " + result.getErrorCount() + " errors: " + result.getAllErrors());
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		}
		else
		{
			log.info("Try save 'Owner' " + owner.getFirstName() + " " + owner.getLastName());
			this.clinicService.saveOwner(owner);
			return "redirect:/owners/" + owner.getId();
		}
	}

	@GetMapping(value = "/owners/find")
	public String initFindForm(Map<String, Object> model)
	{
		log.info("Init find form for 'Owner'");
		model.put("owner", new Owner());
		return "owners/findOwners";
	}

	@GetMapping(value = "/owners")
	public String processFindForm(Owner owner, BindingResult result, Map<String, Object> model)
	{

		if (owner.getLastName() == null)
		{
			owner.setLastName(""); // empty string signifies broadest possible search
		}

		model.put("owners", null);

		log.info("Start find 'Owners' by last name = " + owner.getLastName());
		Collection<Owner> results = this.clinicService.findOwnerByLastName(owner.getLastName());
		log.info("Finish find'Owners'");
		if (results.isEmpty())
		{
			log.info("No 'Owners' found");
			result.rejectValue("lastName", "notFound", "not found");
			return "owners/findOwners";
		}
		else if (results.size() == 1)
		{
			log.info("1 'Owner' found");
			owner = results.iterator().next();
			return "redirect:/owners/" + owner.getId();
		}
		else
		{
			log.info("Multiple 'Owners' found = " + results.size());
			model.put("selections", results);
			return "owners/ownersList";
		}
	}

	@GetMapping(value = "/owners/{ownerId}/edit")
	public String initUpdateOwnerForm(@PathVariable("ownerId") Long ownerId, Model model)
	{
		log.info("Start find 'Owner' by id = " + ownerId);
		Owner owner = this.clinicService.findOwnerById(ownerId);
		log.info("Finish find 'Owner' by id = " + ownerId);
		log.info("'Owner' is " + owner.getFirstName() + " " + owner.getLastName());
		model.addAttribute(owner);
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/owners/{ownerId}/edit")
	public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable("ownerId") long ownerId)
	{
		log.info("Check 'Owner' for error after edit");
		if (result.hasErrors())
		{
			log.error("Owner has " + result.getErrorCount() + " errors: " + result.getAllErrors());
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		}
		else
		{
			log.info("Save 'Owner'");
			this.clinicService.saveOwner(owner);
			return "redirect:/owners/{ownerId}";
		}
	}

	@GetMapping("/owners/{ownerId}")
	public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId)
	{
		ModelAndView mav = new ModelAndView("owners/ownerDetails");
		log.info("Find 'Owner' by id = " + ownerId);
		mav.addObject("owner", this.clinicService.findOwnerById(ownerId));
		return mav;
	}

	@Autowired
	public void setClinicService(ClinicService clinicService)
	{
		this.clinicService = clinicService;
	}
}
