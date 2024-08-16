package vn.io.ductandev.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.io.ductandev.course.entity.PersonEntity;
import vn.io.ductandev.course.entity.RoleEntity;
import vn.io.ductandev.course.model.CategoryDTO;
import vn.io.ductandev.course.model.PersonDTO;
import vn.io.ductandev.course.model.RoleDTO;
import vn.io.ductandev.course.payload.ResponData;
import vn.io.ductandev.course.repository.PersonRepository;
import vn.io.ductandev.course.service.PersonService;
import vn.io.ductandev.course.service.impl.PersonServiceImpl;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
	
	  @Autowired
	  PersonService personService;
	  
	  @GetMapping
	    public ResponseEntity<?> getAllCategories() {
	        List<PersonDTO> personDTOs = personService.getListPerson();
	        return ResponseEntity.ok(personDTOs);
	    }
	
	  @PostMapping("/add")
	    public ResponseEntity<?> addPerson(
	            @RequestParam String username,
	            @RequestParam String password,
	            @RequestParam String firstName,
	            @RequestParam String lastName) {
		  
		  	ResponData responData = new ResponData();
	        
	        PersonDTO personDTO = new PersonDTO();
	        
	        personDTO.setUsername(username);
	        personDTO.setPassword(password);
	        personDTO.setFirstName(firstName);
	        personDTO.setLastName(lastName);
		  
			Boolean isSuccess = personService.addPerson(personDTO);

			responData.setData(isSuccess);
			
			return new ResponseEntity<>(responData, HttpStatus.OK);
	    }
	  
//	  @PostMapping
//	    public ResponseEntity<String> addPerson(@ModelAttribute PersonDTO personDTO) {
//	        boolean success = personServiceImpl.addPerson(personDTO);
//	        if (success) {
//	            return ResponseEntity.ok("Person added successfully");
//	        } else {
//	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add person");
//	        }
//	    }
	  
	  
	  @PutMapping("/update/{id}")
	    public ResponseEntity<?> updatePerson(@PathVariable int id, 
	    		@RequestParam String username,
	    	    @RequestParam String password,
	    	    @RequestParam String firstName,
	    	    @RequestParam String lastName, 
	    	    @RequestParam int isDelete) {
		  
		  	PersonDTO personDTO = new PersonDTO();
		    personDTO.setUsername(username);
		    personDTO.setPassword(password);
		    personDTO.setFirstName(firstName);
		    personDTO.setLastName(lastName);
		    personDTO.setIsDelete(isDelete);		
			  
	        boolean isUpdated = personService.updatePerson(id, personDTO);
	        
	        if (isUpdated) {
	            return new ResponseEntity<>("Person updated successfully", HttpStatus.OK);
	        } else {		
	            return new ResponseEntity<>("Failed to update person", HttpStatus.BAD_REQUEST);
	        }
	    }
	  
	  @DeleteMapping("/delete/{id}")
	  public ResponseEntity<?> deletePerson(@PathVariable int id) {
		 
		  boolean isDelete = personService.deletePerson(id);
		  
		  if (isDelete) {
	            return new ResponseEntity<>("Person delete successfully", HttpStatus.OK);
	        } else {		
	            return new ResponseEntity<>("Failed to delete person", HttpStatus.BAD_REQUEST);
	        }
	    }
	
	  private PersonDTO convertToDTO(PersonEntity personEntity) {
	        PersonDTO personDTO = new PersonDTO();
	        personDTO.setId(personEntity.getId());
	        personDTO.setUsername(personEntity.getUsername());
	        personDTO.setPassword(personEntity.getPassword());
	        personDTO.setFirstName(personEntity.getFirstName());
	        personDTO.setLastName(personEntity.getLastName());
	        personDTO.setIsDelete(personEntity.getIsDelete());
	        
	        RoleEntity role = personEntity.getRole();
	        
	        RoleDTO roleDTO = new RoleDTO();
	        
	        roleDTO.setId(role.getId());
	        roleDTO.setName(role.getName());
	        
	        personDTO.setRole(roleDTO);
	        
	        return personDTO;
	    }
	
}
