package itallodavid.github.personapi.dto;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class PersonUpdateDTO implements Serializable {

    @NotEmpty @Size(min = 3, max = 100)
    private String firstName;

    @NotEmpty @Size(min = 3, max = 100)
    private String lastName;

    @NotEmpty @Size(min = 10, max = 10) @Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}")
    private String birthDate;

    @NotNull @Valid
    private List<PhoneDTO> phones;
}
