package io.kouns.demospringsecurity3.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.kouns.demospringsecurity3.utils.CustomInstantDeSerializer;
import io.kouns.demospringsecurity3.utils.CustomInstantSerializer;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    private static final long serialVersionUID = 6582417733966386540L;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonDeserialize(using = CustomInstantDeSerializer.class)
    private Instant createdAt;

    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonIgnore
    private Instant updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @CreatedBy
    @JsonIgnore
    private User createdBy;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @LastModifiedBy
    @JsonIgnore
    private User modifyBy;

    @JsonIgnore
    private boolean deleted;

    @Column(unique = true)
    private String slug;

    @JsonGetter
    @JsonSerialize(using = CustomInstantSerializer.class)
    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @JsonIgnore
    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(User modifyBy) {
        this.modifyBy = modifyBy;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @PrePersist
    public void generateSlug() {
        this.slug = UUID.randomUUID().toString();
    }
}
