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
@SQLDelete(sql = "UPDATE {tableName} SET deleted = true WHERE id = ?")
@Table(name = "{tableName}")
@Where(clause = "deleted = false")
public @interface JpaEntity {
    String tableName();
}
