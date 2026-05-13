package com.ccc.sistema_balistico.repositories.bullet;

import com.ccc.sistema_balistico.entities.BulletEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BulletRepository extends JpaRepository<BulletEntity,Long> {

    Page<BulletEntity> findByIsDeleteFalse(Pageable pageable);

}
