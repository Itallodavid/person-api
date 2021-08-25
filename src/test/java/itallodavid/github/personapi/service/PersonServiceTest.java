package itallodavid.github.personapi.service;

import itallodavid.github.personapi.dto.PersonDTO;
import itallodavid.github.personapi.dto.PersonUpdateDTO;
import itallodavid.github.personapi.exceptions.PersonAlreadyExistsException;
import itallodavid.github.personapi.exceptions.PersonNotFoundException;
import itallodavid.github.personapi.mapper.PersonMapper;
import itallodavid.github.personapi.model.Person;
import itallodavid.github.personapi.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static itallodavid.github.personapi.utils.PersonUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository repository;

    @Mock
    private PersonMapper mapper;

    @InjectMocks
    private PersonService service;

    @Test
    void personPage() {
        Pageable pageable = PageRequest.of(0, 20);
        List<Person> personList = List.of(new Person());
        Page<Person> expectedPage = new PageImpl<>(personList, pageable, personList.size());

        when(repository.findAll(pageable)).thenReturn(expectedPage);

        Page<Person> personPage = service.personPage(pageable);

        assertEquals(expectedPage, personPage);
    }

    @Test
    void testGivenPersonDTOValidToCreationThenReturnPersonEntity() {
        PersonDTO personDTO = createFakeDTOWithoutId();
        Person personEntityWithoutID = createFakeEntityWithoutId();
        Person expectedSavedPerson = createFakeEntityWithId();

        when(mapper.toEntity(personDTO)).thenReturn(personEntityWithoutID);
        when(repository.save(personEntityWithoutID)).thenReturn(expectedSavedPerson);

        Person savedPerson = service.createPerson(personDTO);

        assertEquals(expectedSavedPerson, createFakeEntityWithId());
    }

    @Test
    void testGivenDuplicatePersonEntityToCreationThenThrowPersonAlreadyExistsException() {
       when(repository.findById(anyString())).thenReturn(Optional.of(createFakeEntityWithId()));
       assertThrows(PersonAlreadyExistsException.class, () -> service.createPerson(createFakeDTOWithId()));
    }

    @Test
    void testGivenValidIdThenReturnAPersonEntity(){
        final String fakeCPF = "999999999";
        when(repository.findById(anyString())).thenReturn(Optional.of(createFakeEntityWithId()));
        assertEquals(createFakeEntityWithId(), service.getPerson(fakeCPF));
    }

    @Test
    void testGivenInvalidIdThenThrowPersonNotFoundException(){
        final String fakeCPF = "999999999";
        when(repository.findById(anyString())).thenReturn(Optional.empty());
        assertThrows(PersonNotFoundException.class, () -> service.getPerson(fakeCPF));
    }

    @Test
    void testGivenValidPersonUpdateDTOThenUpdateAPersonEntity(){
        final String newFirstName = "Silva";

        PersonUpdateDTO updateDTO = createFakeUpdateDTO();
        updateDTO.setFirstName(newFirstName);

        Person mapperReturn = createFakeEntityWithoutId();
        mapperReturn.setFirstName(newFirstName);

        Person expectedPerson = createFakeEntityWithId();
        expectedPerson.setFirstName(newFirstName);

        when(mapper.toEntity(updateDTO)).thenReturn(mapperReturn);
        when(repository.findById(CPF_NUMBER)).thenReturn(Optional.of(createFakeEntityWithId()));
        when(repository.save(expectedPerson)).thenReturn(expectedPerson);

        assertEquals(expectedPerson, service.updatePerson(CPF_NUMBER, updateDTO));
    }
}