package io.kouns.demospringsecurity3.entities;

import jakarta.persistence.Table;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@SQLDelete(sql =
        "UPDATE {table} " +
                "SET deleted = true " +
                "WHERE id = ?")
@Table(name = "{table}")
@Where(clause = "deleted = false")
public @interface JpaEntity {
    String table();
}
