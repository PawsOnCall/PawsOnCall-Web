package com.pawsoncall.web.domain;

import java.io.Serializable;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.util.Date;
import java.util.Objects;

// simplify with baseEntity:
// https://medium.com/@kouomeukevin/create-a-base-entity-with-jpa-8adb35d2b7a3
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    private String createdBy;
    private String updatedBy;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpDate() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof BaseEntity))
            return false;
        if (!super.equals(o))
            return false;
        BaseEntity that = (BaseEntity) o;
        return createdBy.equals(that.createdBy) && updatedBy.equals(that.updatedBy)
                && createdAt.equals(that.createdAt) && updatedAt.equals(that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), createdBy, updatedBy, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "BaseEntity{" + "createdBy='" + createdBy + ", updatedBy='" + updatedBy
                + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "}" + super.toString();
    }
}
