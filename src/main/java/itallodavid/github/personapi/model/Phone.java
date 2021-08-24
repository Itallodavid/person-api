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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PhoneType type;

    @Column(nullable = false, unique = true)
    private String number;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Phone phone = (Phone) o;

        return Objects.equals(id, phone.id);
    }

    @Override
    public int hashCode() {
        return 745010098;
    }
}
