package com.crud.springbootmysql.demo.repository;

import com.crud.springbootmysql.demo.model.Contact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {
    boolean existsByEmail(String email);
//    @Query("select from Contact c where c.email = false")
    @Query(value = "SELECT count(*) FROM Contact WHERE email = ?1 && id<> ?2", nativeQuery = true)
    Integer existsDuplicateForUpdateByEmail(String email, Long id);
//    List<Contact> findAllContact();
}
