package com.app.MBox.core.model;

import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class recordLabelArtists {

    private int id;
    //I mean here
    private recordLabel recordLabel;

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

    //it is possible that ManyToOne should be up at the propertyyy
    @ManyToOne(cascade = CascadeType.ALL)
    public recordLabel getRecordLabel() {
        return recordLabel;
    }

    public void setRecordLabel(recordLabel recordLabel) {
        this.recordLabel = recordLabel;
    }

    @OneToOne(cascade=CascadeType.ALL)
    public artist getArtist() {
        return artist;
    }

    public void setArtist(artist artist) {
        this.artist = artist;
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

    public recordLabelArtists () {

    }
}
