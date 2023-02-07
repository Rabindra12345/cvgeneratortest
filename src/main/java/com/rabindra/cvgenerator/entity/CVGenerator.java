package com.rabindra.cvgenerator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="cv")
@Getter
@Setter
public class CVGenerator {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="name")
	private String name;
	 
	@Column(name="address")
	 private String address;
	 
	@Column(name="contact")
	 private String contact;
	 
	@Column(name="experience")
	 private String experience;
	 
	@Column(name="objectives") 
	private String objectives;
	 
	@Column(name="skills")
	private String skills;
	
	@Column(name="email")
	private String email;
	 
	@Column(name="file_type") 
	private String fileType;
	
	public CVGenerator() {
		
	}

	public CVGenerator(String name, String address, String contact, String experience, String objectives, String skills,
			String fileType) {
		this.name = name;
		this.address = address;
		this.contact = contact;
		this.experience = experience;
		this.objectives = objectives;
		this.skills = skills;
		this.fileType = fileType;
	}

	@Override
	public String toString() {
		return "CVGenerator [id=" + id + ", name=" + name + ", address=" + address + ", contact=" + contact
				+ ", experience=" + experience + ", objectives=" + objectives + ", skills=" + skills + ", fileType="
				+ fileType + "]";
	}
	
	
}
