package com.app.MBox.core.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
public class role {

    private int id;

    private String name;

    private Set<userRoles> userRoles;

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

    @Column(nullable = false,unique = true,length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @OneToMany(mappedBy = "role")
    public Set<com.app.MBox.core.model.userRoles> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<com.app.MBox.core.model.userRoles> userRoles) {
        this.userRoles = userRoles;
    }
}
