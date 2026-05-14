package com.ccc.sistema_balistico.entities;


import com.ccc.sistema_balistico.entities.enums.PercussionType;
import com.ccc.sistema_balistico.entities.enums.TwistDirection;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BulletEntity {

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
    private CaliberEntity caliberEntity;

    private String manufacturer;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "idBullet",orphanRemoval = true)
    private List<BulletImagesEntity> imagePaths;

    private Boolean isDelete ;




}
