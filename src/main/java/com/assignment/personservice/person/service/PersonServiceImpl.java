package com.assignment.personservice.person.service;

import com.assignment.personservice.person.mapper.PersonMapper;
import com.assignment.personservice.person.model.Person;
import com.assignment.personservice.person.model.PersonDTO;
import com.assignment.personservice.person.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);

    public PersonServiceImpl(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    @Override
    public List<PersonDTO> getAllPersons() {
        LOGGER.info("getting all people...");
        List<Person> people = (List<Person>) personRepository.findAll();
        return personMapper.personTOToPersonVO(people);
    }

    @Override
    public Optional<PersonDTO> getPersonById(Long id) {
        LOGGER.info("getting person by id {}...", id);
        Optional<Person> optional = personRepository.findById(id);
        return optional.map(personTO -> personMapper.personTOToPersonVO(personTO));
    }

    @Override
    public PersonDTO savePerson(PersonDTO personDTO) {
        LOGGER.info("saving person {}...", personDTO);
        Person person = personMapper.personVOToPersonTO(personDTO);
        Person savedPerson = personRepository.save(person);
        return personMapper.personTOToPersonVO(savedPerson);
    }

    @Override
    public void deletePerson(Long id) {
        LOGGER.info("deleting person by id {}...", id);
        personRepository.deleteById(id);
    }
}