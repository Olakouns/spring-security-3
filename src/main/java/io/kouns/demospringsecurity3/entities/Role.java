package io.kouns.demospringsecurity3.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity
@JpaEntity(tableName = "roles")
@Data
public class Role extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String name;
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id"))
    @JsonIgnore
    private Collection<Privilege> privileges;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @JsonIgnore
    private User createdBy;

    public Role() {
    }

    public Role(int id, String name, String description, Collection<Privilege> privileges, User createdBy) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.privileges = privileges;
        this.createdBy = createdBy;
    }

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
