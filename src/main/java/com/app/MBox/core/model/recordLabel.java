package com.app.MBox.core.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class recordLabel extends users {

    private String aboutInfo;

    private Set<recordLabelArtists> recordLabelArtists;

    @Column(length = 500)
    public String getAboutInfo() {
        return aboutInfo;
    }

    public void setAboutInfo(String aboutInfo) {
        this.aboutInfo = aboutInfo;
    }

    @OneToMany(mappedBy = "recordLabel",cascade = CascadeType.ALL)
    public Set<com.app.MBox.core.model.recordLabelArtists> getRecordLabelArtists() {
        return recordLabelArtists;
    }

    public void setRecordLabelArtists(Set<com.app.MBox.core.model.recordLabelArtists> recordLabelArtists) {
        this.recordLabelArtists = recordLabelArtists;
    }

    public recordLabel() {
    }
}
