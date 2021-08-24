package itallodavid.github.personapi.exceptions;

public class PersonAlreadyExistsException extends RuntimeException {
    public PersonAlreadyExistsException(final String id){
        super(String.format("Person with cpf %s already exists in database", id));
    }
}
