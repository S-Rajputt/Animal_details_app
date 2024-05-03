package com.AnimalApp.Dao;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import com.AnimalApp.Entity.Animal;

public interface AnimalDao extends JpaRepository<Animal, Long> {
}
