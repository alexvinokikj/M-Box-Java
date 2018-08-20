package com.app.MBox.core.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class song {

    private int id;

    private String name;

    private String albumName;

    private String lyrics;

    private String image;

    private Date dateOfRelease;

    private String youtubeLink;

    private String vimeoLink;

    private String genre;

    private artist artist;

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

    @Column(length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = true,length = 50)
    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }


    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    @Column(length = 50,unique = true)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(Date dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }
    @Column(length = 100)
    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }
    @Column(length = 100)
    public String getVimeoLink() {
        return vimeoLink;
    }

    public void setVimeoLink(String vimeoLink) {
        this.vimeoLink = vimeoLink;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "artistId")
    public com.app.MBox.core.model.artist getArtist() {
        return artist;
    }

    public void setArtist(com.app.MBox.core.model.artist artist) {
        this.artist = artist;
    }

    public song () {

    }

}
