package itallodavid.github.personapi.mapper;

import itallodavid.github.personapi.dto.PersonDTO;
import itallodavid.github.personapi.dto.PersonUpdateDTO;
import itallodavid.github.personapi.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    Person toEntity(final PersonDTO dto);

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    @Mapping(target = "cpf", ignore = true)
    Person toEntity(final PersonUpdateDTO dto);

    PersonDTO toDTO(final Person dto);
}
