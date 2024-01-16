package com.assignment.personservice.person.repository;

import com.assignment.personservice.person.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
