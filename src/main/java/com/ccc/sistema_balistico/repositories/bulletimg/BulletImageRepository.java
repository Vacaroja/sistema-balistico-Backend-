package com.ccc.sistema_balistico.repositories.bulletimg;

import com.ccc.sistema_balistico.entities.BulletImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BulletImageRepository extends JpaRepository<BulletImagesEntity, UUID> {

    List<BulletImagesEntity> findByIdBullet(Long id);
}
