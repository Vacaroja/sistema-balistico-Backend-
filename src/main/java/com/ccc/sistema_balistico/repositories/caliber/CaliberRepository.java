package com.ccc.sistema_balistico.repositories.caliber;

import com.ccc.sistema_balistico.entities.CaliberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CaliberRepository extends JpaRepository<CaliberEntity,Long> {

    List<CaliberEntity> findByIsDeleteFalse();
}
