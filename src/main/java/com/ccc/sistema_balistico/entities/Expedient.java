package com.ccc.sistema_balistico.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Expedient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCaliber;
    private LocalDateTime createdAt;
}
