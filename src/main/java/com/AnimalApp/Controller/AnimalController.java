package com.AnimalApp.Controller;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.AnimalApp.Entity.Animal;
import com.AnimalApp.Service.AnimalService;

import jakarta.servlet.annotation.MultipartConfig;
//import com.EventManagementApp.entity.Event;

@RestController
public class AnimalController {

	
	@Autowired
	private AnimalService service;
	
	
	public static String uploadDir=System.getProperty("user.dir")+"/src/main/resources/static/images";	
	@CrossOrigin
	@GetMapping("/home")
	public void home() {
		System.out.println("Hello i'm going to carete a animal app");
	}
	
	
//	Get all the animal details
	
	@CrossOrigin
	@GetMapping("/animal")
	public List<Animal> getAnimal(){
		return this.service.getAnimal();
	}
	
//	Add new Animal in the DB
	
	
	@CrossOrigin()
	@PostMapping("/animal")
	public ResponseEntity<String> addAnimal(@RequestParam(value="category",  required = false) String category,
											@RequestParam(value="name" , required = false) String name, 
											@RequestParam(value="description", required = false) String description,
											@RequestParam(value="age" , required = false) Long age,
											@RequestParam(value="imagefile" ,required=true) MultipartFile imagefile)
	{
		try {
				
			
			Animal animal=new Animal();
			animal.setCategory(category);
			animal.setName(name);
			animal.setDescription(description);
			animal.setAge(age);
			
			
			String OriginalFileName=imagefile.getOriginalFilename();
			Path fileNameandPath=(Path) Paths.get(uploadDir, OriginalFileName);
			
			Files.write(fileNameandPath, imagefile.getBytes());
			animal.setImage(OriginalFileName);
			
			animal.setImage(imagefile.getOriginalFilename());
			this.service.addAnimal(animal);
			return ResponseEntity.status(HttpStatus.OK).body("Animal add successfully");
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to add animal");
		}
	
	}
	
	
//	update the existing Animal details 
	
	@CrossOrigin
	@PutMapping("/animal")
	public Animal updateAnimal(@RequestBody Animal animal) {
		return this.service.updateAnimal(animal);
	}
	 
	
//	Delete the Animal Details 
	
	@CrossOrigin
	@DeleteMapping("animal/{animalId}")
	public ResponseEntity<HttpStatus> deleteAnimal(@PathVariable String animalId){
		try {
		this.service.deleteAnimal(Long.parseLong(animalId));
		return new ResponseEntity(HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
//	Name  sorting API
	
	
	@CrossOrigin
	@GetMapping("/namesort")
	public List<Animal> sortByName(){
		return this.service.sortByName();
	}
	
//	Category Sorting API 
	
	@CrossOrigin
	@GetMapping("/categorysort")
	public List<Animal> sortByCategory(){
		return this.service.sortByCategory();
	}
	
//	Age Sorting API

	@CrossOrigin
	@GetMapping("/agesort")
	public List<Animal> sortByAge(){
		return this.service.sortByAge();
	}
}
