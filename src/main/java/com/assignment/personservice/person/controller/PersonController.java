package com.assignment.personservice.person.controller;

import com.assignment.personservice.person.model.PersonDTO;
import com.assignment.personservice.person.service.PersonService;
import com.assignment.personservice.person.exception.PersonNotFoundException;
import com.assignment.personservice.shared.model.ResponseVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/people")
@Tag(name = "Person API")
@Validated
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    @Operation(description = "Get all people")
    @ResponseStatus(HttpStatus.OK)
    public ResponseVO<List<PersonDTO>> getAllPersons() {
        List<PersonDTO> persons = personService.getAllPersons();
        return new ResponseVO<>(persons);
    }

    @GetMapping("/{id}")
    @Operation(description = "Get person by ID")
    @ResponseStatus(HttpStatus.OK)
    public ResponseVO<PersonDTO> getPersonById(@PathVariable Long id) {
        Optional<PersonDTO> person = personService.getPersonById(id);
        if (person.isPresent()) {
            return new ResponseVO<>(person.get());
        } else {
            throw new PersonNotFoundException(id);
        }
    }

    @PostMapping
    @Operation(description = "Save a new person")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseVO<PersonDTO> savePerson(@RequestBody @Valid PersonDTO person) {
        PersonDTO savedPerson = personService.savePerson(person);
        return new ResponseVO<>(savedPerson);
    }

    @PutMapping("/{id}")
    @Operation(description = "Update person by ID")
    @ResponseStatus(HttpStatus.OK)
    public ResponseVO<PersonDTO> updatePerson(@PathVariable Long id, @RequestBody @Valid PersonDTO updatedPerson) {
        Optional<PersonDTO> existingPerson = personService.getPersonById(id);

        if (existingPerson.isPresent()) {
            updatedPerson.setId(id);
            PersonDTO updated = personService.savePerson(updatedPerson);
            return new ResponseVO<>(updated);
        } else {
            throw new PersonNotFoundException(id);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete person by ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }
}