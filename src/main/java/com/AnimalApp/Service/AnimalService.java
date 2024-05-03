package com.AnimalApp.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.AnimalApp.Entity.Animal;

public interface AnimalService {
	
	
//	declare all the methods in interface 
	
	public List<Animal> getAnimal();
	public List<Animal> sortByName();
	public List<Animal> sortByCategory();
	public List<Animal> sortByAge();
	public Animal addAnimal(Animal animal);
	public Animal  updateAnimal(Animal animal);
	public void deleteAnimal(Long parselong);
	
}
