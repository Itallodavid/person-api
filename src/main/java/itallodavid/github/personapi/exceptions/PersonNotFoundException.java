package itallodavid.github.personapi.exceptions;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(final String id){
        super(String.format("Person with cpf %s not found in database.", id));
    }
}
