package vn.io.ductandev.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.io.ductandev.course.entity.PersonEntity;
import vn.io.ductandev.course.entity.RoleEntity;
import vn.io.ductandev.course.model.PersonDTO;
import vn.io.ductandev.course.model.RoleDTO;
import vn.io.ductandev.course.payload.ResponData;
import vn.io.ductandev.course.service.PersonService;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {
	
	  @Autowired
	  PersonService personService;
	  
	  @GetMapping
	    public ResponseEntity<?> getAllCategories() {
	        List<PersonDTO> personDTOs = personService.getListPerson();
	        return ResponseEntity.ok(personDTOs);
	    }
	
	  @PostMapping
	  public ResponseEntity<?> addPerson(@RequestBody PersonDTO personDTO) {
	      ResponData responData = new ResponData();
	      
	      Boolean isSuccess = personService.addPerson(personDTO);

	      responData.setData(isSuccess);
	      
	      return new ResponseEntity<>(responData, HttpStatus.OK);
	  }
	  
	  
	  @PutMapping("/update/{id}")
	  public ResponseEntity<?> updatePerson(@PathVariable int id, @RequestBody PersonDTO personDTO) {
	      boolean isUpdated = personService.updatePerson(id, personDTO);
	      
	      if (isUpdated) {
	          return new ResponseEntity<>("Person updated successfully", HttpStatus.OK);
	      } else {        
	          return new ResponseEntity<>("Failed to update person", HttpStatus.BAD_REQUEST);
	      }
	  }

	  
	  @PatchMapping("/delete/{id}")
	    public ResponseEntity<?> deletePerson(@PathVariable int id) {
	        boolean isDelete = personService.deletePerson(id);
	        if (isDelete) {
	            return new ResponseEntity<>("Person deleted successfully", HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Failed to delete person", HttpStatus.BAD_REQUEST);
	        }
	    }
	
//	  private PersonDTO convertToDTO(PersonEntity personEntity) {
//		    PersonDTO personDTO = new PersonDTO();
//		    personDTO.setId(personEntity.getId());
//		    personDTO.setUsername(personEntity.getUsername());
//		    personDTO.setPassword(personEntity.getPassword());
//		    personDTO.setFirstName(personEntity.getFirstName());
//		    personDTO.setLastName(personEntity.getLastName());
//		    personDTO.setIsDelete(personEntity.getIsDelete());
//		    
//		    RoleEntity role = personEntity.getRole();
//		    
//		    RoleDTO roleDTO = new RoleDTO();
//		    roleDTO.setId(role.getId());
//		    roleDTO.setName(role.getName());
//		    
//		    personDTO.setRole(roleDTO);
//		    
//		    return personDTO;
//		}
//
//	  private PersonEntity convertToEntity(PersonDTO personDTO) {
//		    PersonEntity personEntity = new PersonEntity();
//		    personEntity.setId(personDTO.getId());
//		    personEntity.setUsername(personDTO.getUsername());
//		    personEntity.setPassword(personDTO.getPassword());
//		    personEntity.setFirstName(personDTO.getFirstName());
//		    personEntity.setLastName(personDTO.getLastName());
//		    personEntity.setIsDelete(personDTO.getIsDelete());
//		    
//		    RoleDTO roleDTO = personDTO.getRole();
//		    
//		    RoleEntity roleEntity = new RoleEntity();
//		    roleEntity.setId(roleDTO.getId());
//		    roleEntity.setName(roleDTO.getName());
//		    
//		    personEntity.setRole(roleEntity);
//		    
//		    return personEntity;
//		}

	
}
