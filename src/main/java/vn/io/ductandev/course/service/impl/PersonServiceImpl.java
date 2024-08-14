package vn.io.ductandev.course.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.io.ductandev.course.entity.PersonEntity;
import vn.io.ductandev.course.entity.RoleEntity;
import vn.io.ductandev.course.model.PersonDTO;
import vn.io.ductandev.course.repository.PersonRepository;
import vn.io.ductandev.course.repository.RoleRepository;
import vn.io.ductandev.course.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	RoleRepository roleRepository;


	@Override
	public List<PersonDTO> getListPerson() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addPerson(PersonDTO personDTO) {
		
		boolean isSuccess = false;
		
		PersonEntity personEntity = new PersonEntity();
		
		personEntity.setUsername(personDTO.getUsername());
		personEntity.setPassword(personDTO.getPassword());
		personEntity.setFirstName(personDTO.getFirstName());
		personEntity.setLastName(personDTO.getLastName());
//		personEntity.setIsDelete(personDTO.getIsDelete());
		
		RoleEntity r = roleRepository.getById(2);
		
		personEntity.setRole(r);

//		personRepository.addPerson(personEntity.getFirstName(), personEntity.getLastName(),  
//				personEntity.getPassword(),  personEntity.getRole().getId(), personEntity.getUsername());
		
		personRepository.save(personEntity);
		
		isSuccess = true;
		
		return isSuccess;
	}

	@Override
	public boolean updatePerson(int id, PersonDTO personDTO) {
//		 PersonEntity p = personRepository.getById(id);
//		 if(p!= null) {
////			 PersonEntity person = personRepository.getById(id);
//		        // Update fields
////		        person.setUsername(personDTO.getUsername());
////		        person.setPassword(personDTO.getPassword());
////		        person.setFirstName(personDTO.getFirstName());
////		        person.setLastName(personDTO.getLastName());
////		        person.setIsDelete(personDTO.getIsDelete());
//			 
//			 	modelMapper.map(personDTO, p);
//
//		        personRepository.save(p);
//			return true;
//		 }
//		 return false;
		try {
		 PersonEntity person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person not found"));

         person.setUsername(personDTO.getUsername());
         person.setPassword(personDTO.getPassword());
         person.setFirstName(personDTO.getFirstName());
         person.setLastName(personDTO.getLastName());
         person.setIsDelete(personDTO.getIsDelete());

         personRepository.save(person);
         return true;
     } catch (Exception e) {
         return false;
     }
	}

	@Override
	public boolean deletePerson(int id) {
		// TODO Auto-generated method stub
		return false;
	}
}
