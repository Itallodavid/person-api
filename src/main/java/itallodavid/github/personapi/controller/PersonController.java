package itallodavid.github.personapi.controller;

import itallodavid.github.personapi.dto.PersonDTO;
import itallodavid.github.personapi.exceptions.PersonAlreadyExistsException;
import itallodavid.github.personapi.model.Person;
import itallodavid.github.personapi.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController @RequestMapping("/people")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private final PersonService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Person> people(final Pageable pageable){
        return service.personPage(pageable);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Person createPerson(@RequestBody @Valid final PersonDTO dto) throws PersonAlreadyExistsException {
        return service.createPerson(dto);
    }
}
