package com.assignment.personservice.person.mapper;


import com.assignment.personservice.person.model.Person;
import com.assignment.personservice.person.model.PersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mapping(target = "id", ignore = true)
    Person personVOToPersonTO(PersonDTO personDTO);

    PersonDTO personTOToPersonVO(Person person);

    List<Person> personVOToPersonTO(List<PersonDTO> personDTO);

    List<PersonDTO> personTOToPersonVO(List<Person> person);
}
