package vn.io.ductandev.course.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.io.ductandev.course.config.Config;
import vn.io.ductandev.course.config.Mapper;
import vn.io.ductandev.course.entity.PersonEntity;
import vn.io.ductandev.course.entity.RoleEntity;
import vn.io.ductandev.course.dto.PersonDTO;
import vn.io.ductandev.course.dto.RoleDTO;
import vn.io.ductandev.course.repository.PersonRepository;
import vn.io.ductandev.course.repository.RoleRepository;
import vn.io.ductandev.course.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	Config appConfig;
	
	@Autowired
	Mapper<PersonDTO> personMapper;


	@Override
	public List<PersonDTO> getListPerson() {
		
		List<PersonEntity> listEntities = personRepository.findAll();
		
		List<PersonDTO> listDtos = new ArrayList<>();
		
		for(PersonEntity p : listEntities) {
			
			PersonDTO personDTO = new PersonDTO();
			
			personDTO.setId(p.getId());
			personDTO.setFirstName(p.getFirstName());
			personDTO.setLastName(p.getLastName());
			personDTO.setUsername(p.getUsername());
			personDTO.setPassword(p.getPassword());
			
			RoleEntity role = roleRepository.getById(p.getRole().getId());
			
			RoleDTO roleDTO = new RoleDTO();
			
			roleDTO.setId(role.getId());
			roleDTO.setName(role.getName());
			
			personDTO.setRole(roleDTO);
				
			listDtos.add(personDTO);
			
		}
		
		return listDtos;
	}

	@Override
	public boolean addPerson(PersonDTO personDTO) {
		
		boolean isSuccess = false;
		
		try {
			RoleEntity r = roleRepository.getById(2);
			
			PersonEntity p = personMapper.convert(personDTO, PersonEntity.class);
			
			p.setRole(r);
			
			personRepository.save(p);
			
			isSuccess = true;
			
			return isSuccess;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
		
	}

	@Override
	public boolean updatePerson(int id, PersonDTO personDTO) {
		
		Boolean isSuccess = false;
		
		try {
		 PersonEntity person = personRepository.getById(id);

         person.setUsername(personDTO.getUsername());
         person.setPassword(personDTO.getPassword());
         person.setFirstName(personDTO.getFirstName());
         person.setLastName(personDTO.getLastName());
         person.setIsDelete(personDTO.getIsDelete());

         personRepository.save(person);
         
         isSuccess = true;
         
         return isSuccess;
     } catch (Exception e) {
         return isSuccess;
     }
	}

	@Override
	public boolean deletePerson(int id) {
		Boolean isSuccess = false;
		
		PersonEntity person = personRepository.getById(id);
		 
		if(person != null) {
			 person.setIsDelete(1);
			 personRepository.save(person);
			 isSuccess = true;
			 return isSuccess;
		}else {
			return isSuccess;
		}
			
		
	}

	@Override
	public PersonDTO getbyID(int id) {
		PersonEntity p = personRepository.getById(id);
		
		PersonDTO personDTO = new PersonDTO();
		
		personDTO.setId(p.getId());
		personDTO.setFirstName(p.getFirstName());
		personDTO.setLastName(p.getLastName());
		personDTO.setUsername(p.getUsername());
		personDTO.setPassword(p.getPassword());
		personDTO.setIsDelete(p.getIsDelete());
		
		return personDTO;
	}
	
	
	
}
