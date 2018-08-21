package com.app.MBox.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Getter @Setter @NoArgsConstructor
public class audit {

    @CreatedBy
    @Column(nullable = false)
    private int createdBy;

    @LastModifiedBy
    private int modifiedBy;

    @CreatedDate
    @Column(nullable = false)
    private Date dateCreated;

    @LastModifiedDate
    private Date dateModified;

}