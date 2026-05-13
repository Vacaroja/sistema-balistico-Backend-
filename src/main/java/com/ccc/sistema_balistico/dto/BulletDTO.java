package com.ccc.sistema_balistico.dto;

import com.ccc.sistema_balistico.entities.enums.PercussionType;
import com.ccc.sistema_balistico.entities.enums.TwistDirection;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BulletDTO {

    private Long idBullet;

    private String caseFile;

    private Long landsAndGrooves;

    private PercussionType percussionType;

    private TwistDirection twistDirection;

    private Long caliber;

    private String manufacturer;

    private LocalDateTime createdAt;
}
