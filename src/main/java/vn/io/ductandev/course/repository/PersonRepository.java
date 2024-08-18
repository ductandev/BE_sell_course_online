package vn.io.ductandev.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.io.ductandev.course.entity.PersonEntity;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {
    PersonEntity findPersonEntityByUsername(String email);

    @Query(value = "INSERT INTO tb_person (firstname, lastname, password, role_id, username) VALUES (?, ?, ?, ?, ?)", nativeQuery = true)
    void addPerson(String firstName, String lastName, String password, int roleId, String username);

}
