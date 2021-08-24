package itallodavid.github.personapi.service;

import itallodavid.github.personapi.dto.PersonDTO;
import itallodavid.github.personapi.exceptions.PersonAlreadyExistsException;
import itallodavid.github.personapi.exceptions.PersonNotFoundException;
import itallodavid.github.personapi.mapper.PersonMapper;
import itallodavid.github.personapi.model.Person;
import itallodavid.github.personapi.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private final PersonRepository repository;

    private final PersonMapper mapper;

    @Transactional(readOnly = true)
    public Page<Person> personPage(final Pageable pageable){
        return repository.findAll(pageable);
    }

    @Transactional
    public Person createPerson(final PersonDTO dto) throws PersonAlreadyExistsException {
        Optional<Person> person = repository.findById(dto.getCpf());

        if (person.isPresent()) throw new PersonAlreadyExistsException(dto.getCpf());

        Person personToSave = mapper.toEntity(dto);
        return repository.save(personToSave);
    }

    @Transactional(readOnly = true)
    public Person getPerson(final String cpf) throws PersonNotFoundException {
        return repository.findById(cpf).orElseThrow(() -> new PersonNotFoundException(cpf));
    }
}
