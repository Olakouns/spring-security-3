package io.kouns.demospringsecurity3.entities;

import io.kouns.demospringsecurity3.entities.enums.TypePrivilege;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@JpaEntity(tableName = "privileges")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class Privilege extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypePrivilege name;

    private String description;

    public Privilege(TypePrivilege name, String description) {
        this.name = name;
        this.description = description;
    }
}
