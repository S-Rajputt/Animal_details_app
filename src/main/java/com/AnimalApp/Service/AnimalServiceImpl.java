package com.AnimalApp.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.AnimalApp.Dao.AnimalDao;
import com.AnimalApp.Entity.Animal;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AnimalServiceImpl implements AnimalService{

//	this is implemented class of Animal service so we can write our business logic
//	and override all the methods 
	
	@Autowired
	private AnimalDao animalDao;
	
	@Override
	public List<Animal> getAnimal() {
	return animalDao.findAll();
	}

	@Override
	public Animal addAnimal( Animal animal) {
			return animalDao.save(animal);
			}

	@Override
	public Animal updateAnimal( Animal animal) {
		return animalDao.save(animal);
	}

	@Override
	public void deleteAnimal(Long parselong) {
		Animal entity=animalDao.getReferenceById(parselong);
		animalDao.delete(entity);
	}

//	sorting the data by name we use find all method 
//	in this method sort.by we create new instance of sort and we get sorted data by name  
	@Override
	public List<Animal> sortByName() {
		return animalDao.findAll(Sort.by(Sort.Direction.ASC, "name"));
	}

//	sorting the data by category we use find all method 
//	in this method sort.by we create new instance of sort and we get sorted data by category 
	
	@Override
	public List<Animal> sortByCategory() {
		return animalDao.findAll(Sort.by(Sort.Direction.ASC, "category"));
	}

//	sorting the data by age we use find all method 
//	in this method sort.by we create new instance of sort and we get sorted data by age
	
	@Override
	public List<Animal> sortByAge() {
		return animalDao.findAll(Sort.by(Sort.Direction.ASC,"age"));
	}


}
