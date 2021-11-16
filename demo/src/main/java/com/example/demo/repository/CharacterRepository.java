package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Detail;

public interface CharacterRepository extends JpaRepository<Detail, Long> {
	
	/**
	 * Special function calling on the special JPA functions to find by a specific attribute
	 * 
	 * @param name
	 * @return
	 */
	List<Detail> findByNameContaining(String name);
	

}
