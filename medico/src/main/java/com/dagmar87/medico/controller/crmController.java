package com.dagmar87.medico.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.dagmar87.medico.exception.ResourceNotFoundException;
import com.dagmar87.medico.model.crm;
import com.dagmar87.medico.repository.crmRepository;
import com.dagmar87.medico.repository.userRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class crmController {

	@Autowired
	private userRepository userRep;

	@Autowired
	private crmRepository crmRep;

	@GetMapping("/users/{userId}/crms")
	public ResponseEntity<List<crm>> getAllCrmsByUserId(@PathVariable(value = "userId") Long userId) {
		if (!userRep.existsById(userId)) {
			throw new ResourceNotFoundException("Usuario não encontrado com id = " + userId);
		}
		List<crm> crms = crmRep.findByUserId(userId);
		return new ResponseEntity<>(crms, HttpStatus.OK);
	}

	@GetMapping("/crms/{id}")
	public ResponseEntity<crm> getCrmById(@PathVariable(value = "id") Long id) {
		crm crm = crmRep.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("CRM não encontrado com id = " + id));
		return new ResponseEntity<>(crm, HttpStatus.OK);
	}

	@PostMapping("/users/{userId}/crms")
	public ResponseEntity<crm> createCrm(@PathVariable(value = "userId") Long userId, @RequestBody crm crmRequest) {
		crm crm = userRep.findById(userId).map(user -> {
			crmRequest.setUser(user);
			return crmRep.save(crmRequest);
		}).orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado com id = " + userId));
		return new ResponseEntity<>(crm, HttpStatus.CREATED);
	}

	@PutMapping("/crms/{id}")
	public ResponseEntity<crm> updateCrm(@PathVariable("id") long id, @RequestBody crm crmRequest) {
		crm crm = crmRep.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("CrmId " + id + "não encontrado"));
		crm.setCrm(crmRequest.getCrm());
		crm.setUf(crmRequest.getUf());
		crm.setSpecialty(crmRequest.getSpecialty());
		return new ResponseEntity<>(crmRep.save(crm), HttpStatus.OK);
	}

	@DeleteMapping("/crms/{id}")
	public ResponseEntity<HttpStatus> deleteCrm(@PathVariable("id") long id) {
		crmRep.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/users/{userId}/crms")
	public ResponseEntity<List<crm>> deleteAllCrmsOfUser(@PathVariable(value = "userId") Long userId) {
		if (!userRep.existsById(userId)) {
			throw new ResourceNotFoundException("Usuario não encontrado com id  = " + userId);
		}
		crmRep.deleteByUserId(userId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/crms/{specialty}")
	public ResponseEntity<List<crm>> findBySpecialty(@PathVariable(value = "specialty") String specialty) {
		List<crm> crms = crmRep.findBySpecialty(specialty);
		if (crms.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(crms, HttpStatus.OK);
	}

}
