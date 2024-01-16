package com.assignment.personservice.person.service;

import com.assignment.personservice.person.mapper.PersonMapper;
import com.assignment.personservice.person.model.Person;
import com.assignment.personservice.person.model.PersonDTO;
import com.assignment.personservice.person.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonMapper personMapper;

    @InjectMocks
    private PersonServiceImpl personService;

    @Test
    void testGetAllPersons() {
        List<Person> people = new ArrayList<>();
        when(personRepository.findAll()).thenReturn(people);

        List<PersonDTO> personDTOS = new ArrayList<>();
        when(personMapper.personTOToPersonVO(people)).thenReturn(personDTOS);

        List<PersonDTO> result = personService.getAllPersons();

        assertEquals(personDTOS, result);
        verify(personRepository, times(1)).findAll();
        verify(personMapper, times(1)).personTOToPersonVO(people);
    }

    @Test
    void testGetPersonById() {
        Long personId = 1L;
        Person person = new Person();
        Optional<Person> optionalPersonTO = Optional.of(person);

        when(personRepository.findById(personId)).thenReturn(optionalPersonTO);

        PersonDTO personDTO = new PersonDTO();
        when(personMapper.personTOToPersonVO(person)).thenReturn(personDTO);

        Optional<PersonDTO> result = personService.getPersonById(personId);

        assertEquals(Optional.of(personDTO), result);
        verify(personRepository, times(1)).findById(personId);
        verify(personMapper, times(1)).personTOToPersonVO(person);
    }

    @Test
    void testSavePerson() {
        PersonDTO personDTO = new PersonDTO();
        Person person = new Person();

        when(personMapper.personVOToPersonTO(personDTO)).thenReturn(person);
        when(personRepository.save(person)).thenReturn(person);
        when(personMapper.personTOToPersonVO(person)).thenReturn(personDTO);

        PersonDTO result = personService.savePerson(personDTO);

        assertEquals(personDTO, result);
        verify(personMapper, times(1)).personVOToPersonTO(personDTO);
        verify(personRepository, times(1)).save(person);
        verify(personMapper, times(1)).personTOToPersonVO(person);
    }

    @Test
    void testDeletePerson() {
        Long personId = 1L;

        personService.deletePerson(personId);

        verify(personRepository, times(1)).deleteById(personId);
    }
}