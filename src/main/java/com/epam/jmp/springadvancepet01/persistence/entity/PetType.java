package com.epam.jmp.springadvancepet01.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "types")
public class PetType extends NamedEntity {

}
