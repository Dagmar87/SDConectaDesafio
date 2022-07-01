package com.dagmar87.medico.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.dagmar87.medico.exception.ResourceNotFoundException;
import com.dagmar87.medico.model.user;
import com.dagmar87.medico.repository.userRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class userController {
	
	@Autowired
	userRepository userRep;
	
	@GetMapping("/users")
	public ResponseEntity<List<user>> getAllUsers(@RequestParam(required = false) String name) {
		List<user> users = new ArrayList<user>();
		if (name == null)
			userRep.findAll().forEach(users::add);
		else
			userRep.findByName(name).forEach(users::add);
		if (users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<user> getUserById(@PathVariable("id") long id) {
		user user = userRep.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado com id = " + id));
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PostMapping("/users")
	public ResponseEntity<user> createUser(@RequestBody user user) {
		user _user = userRep
				.save(new user(user.getEmail(), user.getPassword(), user.getName(), 
						user.getSurname(), user.getMobile_phone()));
		return new ResponseEntity<>(_user, HttpStatus.CREATED);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<user> updateUser(@PathVariable("id") long id, @RequestBody user user) {
		user _user = userRep.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado com id = " + id));
		_user.setEmail(user.getEmail());
		_user.setPassword(user.getPassword());
		_user.setName(user.getName());
		_user.setSurname(user.getSurname());
		_user.setMobile_phone(user.getMobile_phone());

		return new ResponseEntity<>(userRep.save(_user), HttpStatus.OK);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
		userRep.deleteById(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/users")
	public ResponseEntity<HttpStatus> deleteAllUsers() {
		userRep.deleteAll();

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
