package com.ccc.sistema_balistico.core.domain.model;


import com.ccc.sistema_balistico.core.domain.model.enums.PercussionType;
import com.ccc.sistema_balistico.core.domain.model.enums.TwistDirection;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bullet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBullet;

    private String caseFile;

    private Long landsAndGrooves;

    @Enumerated(EnumType.STRING)
    private PercussionType percussionType;

    @Enumerated(EnumType.STRING)
    private TwistDirection twistDirection;

    @ManyToOne
    @JoinColumn(name = "id_caliber")
    private Caliber caliber;

    private String manufacturer;

    private LocalDateTime createdAt;

    private Boolean isDeleted;




}
