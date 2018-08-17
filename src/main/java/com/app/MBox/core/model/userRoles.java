package com.app.MBox.core.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class userRoles {

private int id;

private user user;

private role role;

private int createdBy;

private int modifiedBy;

private Date dateCreated;

private Date dateModified;

    @Id
    @GeneratedValue
    @NotNull
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId")
    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="roleId")
    public role getRole() {
        return role;
    }

    public void setRole(role role) {
        this.role = role;
    }

    @Column(nullable = false)
    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Column(nullable = false)
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }
}
