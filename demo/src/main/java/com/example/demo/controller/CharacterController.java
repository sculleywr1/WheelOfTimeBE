package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Detail;
import com.example.demo.repository.CharacterRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CharacterController {

	/**
	 * uses Spring dependency injection to connect the repository layer to the controller. 
	 */
	@Autowired
	CharacterRepository characterRepository;
	
	/**
	 * Returns all of the Detail objects returned by the Repository and packages them as JSONs, then sends them to the front end upon receiving the GET HTTP request to the designated endpoint. Can also be used to request a specific character by the character's name.
	 * @param name
	 * @return
	 */
	@GetMapping("/details")
	public ResponseEntity<List<Detail>> getAllDetails(@RequestParam(required = false) String name)
	{
		try
		{
			List<Detail> details = new ArrayList<Detail>();
			
			if (name == null)
			{
				characterRepository.findAll().forEach(details::add);
			}
			else
			{
				characterRepository.findByNameContaining(name).forEach(details::add);
			}
			
			if (details.isEmpty())
			{
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(details, HttpStatus.OK);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	/**
	 * returns a specific Detail object by the ID when a get request to this endpoint is made
	 * @param id
	 * @return
	 */
	@GetMapping("/details/{id}")
	public ResponseEntity<Detail> getDetailById(@PathVariable("id") long id)
	{
		Optional<Detail> detailData = characterRepository.findById(id);
		
		if (detailData.isPresent())
		{
			return new ResponseEntity<>(detailData.get(), HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * saves the sent Detail object to the database
	 * @param detail
	 * @return
	 */
	@PostMapping("/details")
	public ResponseEntity<Detail> createDetail(@RequestBody Detail detail)
	{
		try
		{
			Detail _detail = characterRepository.save(new Detail(detail.getName(), detail.getNationality(), detail.isChanneler(), detail.getGender()));
			return new ResponseEntity<>(_detail, HttpStatus.CREATED);	
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * retrieves the specified Detail, edits it, then returns it to the Repository
	 * @param id
	 * @param detail
	 * @return
	 */
	@PutMapping("/details/{id}")
	public ResponseEntity<Detail> updateDetail(@PathVariable("id") long id, @RequestBody Detail detail)
	{
		Optional<Detail> detailData = characterRepository.findById(id);
		
		if (detailData.isPresent())
		{
			Detail _detail = detailData.get();
			_detail.setName(detail.getName());
			_detail.setNationality(detail.getNationality());
			_detail.setChanneler(detail.isChanneler());
			_detail.setGender(detail.getGender());
			return new ResponseEntity<>(characterRepository.save(_detail), HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * takes in an ID and calls the DeleteById function in the repository
	 * @param id
	 * @return
	 */
	@DeleteMapping("/details/{id}")
	public ResponseEntity<HttpStatus> deleteDetail(@PathVariable("id") long id)
	{
		try
		{
			characterRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
