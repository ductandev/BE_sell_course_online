package vn.io.ductandev.course.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import vn.io.ductandev.course.entity.PersonEntity;
import vn.io.ductandev.course.entity.RoleEntity;
import vn.io.ductandev.course.dto.PersonDTO;
import vn.io.ductandev.course.dto.RoleDTO;
import vn.io.ductandev.course.request.PersonRequest;
import vn.io.ductandev.course.response.ResponseDTO;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.io.ductandev.course.payload.ResponData;
import vn.io.ductandev.course.service.PersonService;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {

    @Autowired
    PersonService personService;

	// ================================================
	//               	GET ALL PERSON
	// ================================================
    @GetMapping
    public ResponseEntity<?> getAllPerson() {
        List<PersonDTO> personDTOs = personService.getListPerson();

        ResponseDTO<PersonDTO> response = new ResponseDTO<>(
                "Thành công !",
                HttpStatus.OK.value(),
                (List<PersonDTO>) personDTOs,
                new Date()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

	// ================================================
	//               	CREATE PERSON
	// ================================================
    @PostMapping("/add")
    public ResponseEntity<?> addPerson(@RequestBody PersonDTO personDTO) {

        Boolean isAdd = personService.addPerson(personDTO);

        if (isAdd) {
        	ResponseDTO<PersonDTO> response = new ResponseDTO<>(
                    "Thêm thành công !",
                    HttpStatus.OK.value(),
                    (PersonDTO) personDTO,
                    new Date()
            );
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to add person", HttpStatus.BAD_REQUEST);
        }
    }

	// ================================================
	//               	UPDATE PERSON
	// ================================================
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable int id, @RequestBody PersonDTO personDTO) {
        boolean isUpdated = personService.updatePerson(id, personDTO);

        if (isUpdated) {
        	ResponseDTO<PersonDTO> response = new ResponseDTO<>(
                    "Update thành công !",
                    HttpStatus.OK.value(),
                    (PersonDTO) personDTO,
                    new Date()
            );
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
        	 ResponseDTO<String> response = new ResponseDTO<>(
                     "Failed to update person: Person not found with id " + id,
                     HttpStatus.NOT_FOUND.value(),
                     null,
                     new Date()
             );
             return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

	// ================================================
	//               	DELETE PERSON
	// ================================================
    @PatchMapping("/delete/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable int id) {
        boolean isDelete = personService.deletePerson(id);
        if (isDelete) {
        	ResponseDTO<PersonDTO> response = new ResponseDTO<>(
                    "Delete thành công !",
                    HttpStatus.OK.value(),
                    (PersonDTO) personService.getbyID(id),
                    new Date()
            );
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
        	 ResponseDTO<String> response = new ResponseDTO<>(
                     "Failed to delete person: Person not found with id " + id,
                     HttpStatus.NOT_FOUND.value(),
                     null,
                     new Date()
             );
             return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
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
