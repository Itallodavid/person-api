package itallodavid.github.personapi.model;

import itallodavid.github.personapi.enums.PhoneType;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Objects;

@Entity @Audited
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Phone {

    @Id
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PhoneType type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Phone phone = (Phone) o;

        return Objects.equals(number, phone.number);
    }

    @Override
    public int hashCode() {
        return 745010098;
    }
}
