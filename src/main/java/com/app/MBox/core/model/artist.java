package com.app.MBox.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
public class artist extends users {

    @Column(length = 500)
    private String bio;
    @Column(nullable = false)
    private boolean isDeleted;
    @OneToMany(mappedBy = "artist",cascade = CascadeType.ALL)
    private Set<song> songs;

    public artist () {
        super();
    }

}


