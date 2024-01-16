package com.assignment.personservice.person.service;

import com.assignment.personservice.person.model.PersonDTO;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    public List<PersonDTO> getAllPersons();

    public Optional<PersonDTO> getPersonById(Long id);

    public PersonDTO savePerson(PersonDTO personDTO);

    public void deletePerson(Long id);

}
