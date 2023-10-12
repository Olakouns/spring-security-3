package io.kouns.demospringsecurity3.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.kouns.demospringsecurity3.entities.enums.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.sql.Types;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
@SQLDelete(sql =
        "UPDATE users " +
                "SET deleted = true " +
                "WHERE id = ?")
@Where(clause = "deleted = false")
@Data
public class User extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

    @NotBlank
    @Email()
    private String email;

    @NotBlank
    private String username;

    private String phoneNumber;

    @Size(max = 100, min = 6)
    @NotBlank
    @JsonIgnore
    private String password;

    private String firstname;

    private String lastname;
    @Enumerated(value = EnumType.STRING)
    private UserType userType;

    @ManyToOne
    private Role role;


}
