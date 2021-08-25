package itallodavid.github.personapi.utils;

import itallodavid.github.personapi.dto.PersonDTO;
import itallodavid.github.personapi.dto.PersonUpdateDTO;
import itallodavid.github.personapi.model.Person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PersonUtils {

    public static final String FIRST_NAME = "Itallo";
    public static final String LAST_NAME = "David";
    public static final String CPF_NUMBER = "45815102016";
    public static final LocalDate BIRTH_DATE = LocalDate.of(2021, 1, 1);

    public static PersonDTO createFakeDTOWithoutId(){
        return PersonDTO.builder()
            .firstName(FIRST_NAME)
            .lastName(LAST_NAME)
            .birthDate(BIRTH_DATE.format(DateTimeFormatter.ofPattern("dd-MM-yyy")))
            .build();
    }

    public static Person createFakeEntityWithoutId(){
        return Person.builder()
            .firstName(FIRST_NAME)
            .lastName(LAST_NAME)
            .birthDate(BIRTH_DATE)
            .build();
    }

    public static PersonDTO createFakeDTOWithId(){
        return PersonDTO.builder()
            .cpf(CPF_NUMBER)
            .firstName(FIRST_NAME)
            .lastName(LAST_NAME)
            .birthDate(BIRTH_DATE.format(DateTimeFormatter.ofPattern("dd-MM-yyy")))
            .build();
    }

    public static Person createFakeEntityWithId(){
        return Person.builder()
            .cpf(CPF_NUMBER)
            .firstName(FIRST_NAME)
            .lastName(LAST_NAME)
            .birthDate(BIRTH_DATE)
            .build();
    }

    public static PersonUpdateDTO createFakeUpdateDTO(){
        return PersonUpdateDTO.builder()
            .firstName(FIRST_NAME)
            .lastName(LAST_NAME)
            .birthDate(BIRTH_DATE.format(DateTimeFormatter.ofPattern("dd-MM-yyy")))
            .build();
    }
}
