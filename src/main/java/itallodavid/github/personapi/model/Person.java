package itallodavid.github.personapi.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity @Audited
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Person {

    @Id
    private String cpf;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDate birthDate;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Phone> phones;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Person person = (Person) o;

        return Objects.equals(cpf, person.cpf);
    }

    @Override
    public int hashCode() {
        return 1422108840;
    }
}
