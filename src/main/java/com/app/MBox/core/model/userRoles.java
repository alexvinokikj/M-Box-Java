package com.app.MBox.core.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class userRoles {

private int id;

private users users;

private role role;

private int createdBy;

private int modifiedBy;

private Date dateCreated;

private Date dateModified;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="userId")
    public users getUsers() {
        return users;
    }

    public void setUsers(users users) {
        this.users = users;
    }
    @ManyToOne(cascade = CascadeType.ALL)
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

    @Column(nullable = true)
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

    public userRoles() {

    }
}
