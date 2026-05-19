package com.ccc.sistema_balistico.dto.mapper;

import com.ccc.sistema_balistico.dto.BulletDTO;
import com.ccc.sistema_balistico.entities.BulletEntity;
import com.ccc.sistema_balistico.entities.BulletImagesEntity;
import com.ccc.sistema_balistico.entities.CaliberEntity;

public class BulletMapper {
    public static BulletDTO toDTO(BulletEntity bulletEntity){
        return BulletDTO.builder().
                idBullet(bulletEntity.getIdBullet()).
                caseFile(bulletEntity.getCaseFile()).
                landsAndGrooves(bulletEntity.getLandsAndGrooves()).
                percussionType(bulletEntity.getPercussionType()).
                twistDirection(bulletEntity.getTwistDirection()).
                caliber(bulletEntity.getCaliberEntity().getIdCaliber()).
                manufacturer(bulletEntity.getManufacturer()).
                createdAt(bulletEntity.getCreatedAt()).
                images(bulletEntity.getImagePaths().stream().map(BulletImagesEntity::getPathImage).toList()).
                build();


    }
    public static BulletEntity toEntity(BulletDTO bulletEntity, CaliberEntity caliberEntity){

        return BulletEntity.builder().
                idBullet(bulletEntity.getIdBullet()).
                caseFile(bulletEntity.getCaseFile()).
                landsAndGrooves(bulletEntity.getLandsAndGrooves()).
                percussionType(bulletEntity.getPercussionType()).
                twistDirection(bulletEntity.getTwistDirection()).
                caliberEntity(caliberEntity).
                manufacturer(bulletEntity.getManufacturer()).
                createdAt(bulletEntity.getCreatedAt()).isDelete(false).
                build();

    }

}
