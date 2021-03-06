package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Detail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "nationality")
	private String nationality;
	
	@Column(name = "channeler")
	private boolean channeler;
	
	@Column(name = "gender")
	private String gender;
	
	public Detail() {
		// TODO Auto-generated constructor stub
	}

	public Detail(long id, String name, String nationality, boolean channeler, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.nationality = nationality;
		this.channeler = channeler;
		this.gender = gender;
	}
	
	

	public Detail(String name, String nationality, boolean channeler, String gender) {
		super();
		this.name = name;
		this.nationality = nationality;
		this.channeler = channeler;
		this.gender = gender;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public boolean isChanneler() {
		return channeler;
	}

	public void setChanneler(boolean channeler) {
		this.channeler = channeler;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Detail [id=" + id + ", name=" + name + ", nationality=" + nationality + ", channeler=" + channeler
				+ ", gender=" + gender + "]";
	}
	
	
	
	
}
