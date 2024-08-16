package vn.io.ductandev.course.service;

import java.util.List;

import vn.io.ductandev.course.dto.PersonDTO;

public interface PersonService {
	
	List<PersonDTO> getListPerson();
	
	boolean addPerson(PersonDTO personDTO);
	
	boolean updatePerson(int id, PersonDTO personDTO );
	
	boolean deletePerson(int id);
	
}
