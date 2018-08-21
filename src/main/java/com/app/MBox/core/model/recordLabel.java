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
public class recordLabel extends users {

    @Column(length = 500)
    private String aboutInfo;
    @OneToMany(mappedBy = "recordLabel",cascade = CascadeType.ALL)
    private Set<recordLabelArtists> recordLabelArtists;

    public recordLabel () {
        super();
    }
}
