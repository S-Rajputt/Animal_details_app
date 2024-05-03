package com.AnimalApp.Entity;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="Animal_Info")
public class Animal {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(name="name")
	private String name;
	@Column(name="category")
	private String category;
	@Column(name="description")
	private String description;
	@Column(name="age")
	private Long age;
	@Lob
	@Column(name="image")
	private String imagefile;
	public Animal() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Animal(long id, String name, String category, String description, Long age, String imagefile) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.description = description;
		this.age = age;
		this.imagefile = imagefile;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	
	public String getImage() {
		return imagefile;
	}
	public void setImage(String imagefile) {
		this.imagefile = imagefile;
	}
	@Override
	public String toString() {
		return "Animal [id=" + id + ", name=" + name + ", category=" + category + ", description=" + description
				+ ",age="+ age + ", image=" + imagefile + "]";
	}
	
	
}
