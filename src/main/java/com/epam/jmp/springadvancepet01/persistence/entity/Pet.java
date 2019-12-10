package com.epam.jmp.springadvancepet01.persistence.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = {"visits"}, callSuper = true)
@Entity
@Table(name = "pets")
public class Pet extends NamedEntity
{

	@Column(name = "birth_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "type_id")
	private PetType type;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "owner_id")
	private Owner owner;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pet", fetch = FetchType.LAZY)
	private Set<Visit> visits;

	protected Set<Visit> getVisitsInternal()
	{
		if (this.visits == null)
		{
			this.visits = new HashSet<>();
		}
		return this.visits;
	}

	public List<Visit> getVisits()
	{
		List<Visit> sortedVisits = new ArrayList<>(getVisitsInternal());
		PropertyComparator.sort(sortedVisits, new MutableSortDefinition("date", false, false));
		return Collections.unmodifiableList(sortedVisits);
	}

	public void addVisit(Visit visit)
	{
		getVisitsInternal().add(visit);
		visit.setPet(this);
	}

	@Transient
	public String getBirthDateStr()
	{
		return birthDate != null ? birthDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null;
	}

	@Transient
	public void setBirthDateStr(String birthDateStr)
	{
		this.birthDate = LocalDate.parse(birthDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		System.out.println();
	}
}
