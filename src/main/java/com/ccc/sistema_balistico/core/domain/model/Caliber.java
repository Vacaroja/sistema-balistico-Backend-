package com.ccc.sistema_balistico.core.domain.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Caliber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCaliber;
    private String name;

    @OneToMany(mappedBy = "caliber")
    private List<Bullet> bullets;
    private Boolean isDelete;
}
