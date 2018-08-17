package com.app.MBox.core.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class artist extends user {

    private String bio;

    private boolean isDeleted;

    private Set<song> songs;

    @Column(length = 500)
    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
    @Column(nullable = false)
    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @OneToMany(mappedBy = "artist")
    public Set<song> getSongs() {
        return songs;
    }

    public void setSongs(Set<song> songs) {
        this.songs = songs;
    }
}


