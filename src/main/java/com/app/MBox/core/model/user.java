package com.app.MBox.core.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class user {

    @Id
    @GeneratedValue
    @NotNull
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Column(nullable = false,length = 320)
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(nullable = false,length = 50,unique = true)
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Column(nullable = false,length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false,length =30)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Column(nullable = false)
    public boolean getIsActivated() {
        return isActivated;
    }

    public void setIsActivated(boolean activated) {
        isActivated = activated;
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

    private int id;

    private String email;

    private String picture;

    private String name;

    private String password;

    private boolean isActivated;

    private int createdBy;

    private int modifiedBy;

    private Date dateCreated;

    private Date dateModified;

    private Set<verificationToken> tokens;

    @OneToMany(mappedBy = "user")
    public Set<com.app.MBox.core.model.userRoles> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<com.app.MBox.core.model.userRoles> userRoles) {
        this.userRoles = userRoles;
    }

    private Set<userRoles> userRoles;

    @OneToMany(mappedBy = "user")
    public Set<verificationToken> getTokens() {
        return tokens;
    }

    public void setTokens(Set<verificationToken> tokens) {
        this.tokens = tokens;
    }
}
