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
    public ResponseEntity<?> addPerson(@RequestBody PersonRequest personRequest) {

        PersonDTO personDTO = new PersonDTO();

        personDTO.setUsername(username);
        personDTO.setPassword(password);
        personDTO.setFirstName(firstName);
        personDTO.setLastName(lastName);


        ResponseDTO<PersonDTO> response = new ResponseDTO<>(
                "Thành công !",
                HttpStatus.OK.value(),
                (List<PersonDTO>) personDTO,
                new Date()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
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

	// ================================================
	//               	UPDATE PERSON
	// ================================================
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

	// ================================================
	//               	DELETE PERSON
	// ================================================
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
