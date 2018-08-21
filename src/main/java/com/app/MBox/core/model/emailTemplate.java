package com.app.MBox.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter @Setter @NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class emailTemplate extends audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(length = 50,unique = true,nullable = false)
    private String name;

    @Column(length = 50)
    private String subject;

    private String body;

}
