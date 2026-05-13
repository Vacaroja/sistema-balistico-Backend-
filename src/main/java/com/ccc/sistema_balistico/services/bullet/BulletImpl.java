package com.ccc.sistema_balistico.services.bullet;

import com.ccc.sistema_balistico.dto.BulletDTO;
import com.ccc.sistema_balistico.dto.mapper.BulletMapper;
import com.ccc.sistema_balistico.entities.BulletEntity;
import com.ccc.sistema_balistico.entities.CaliberEntity;
import com.ccc.sistema_balistico.exceptions.custom.BulletIsDeleted;
import com.ccc.sistema_balistico.exceptions.custom.BulletNotFound;
import com.ccc.sistema_balistico.exceptions.custom.caliber.CaliberIsDeleted;
import com.ccc.sistema_balistico.exceptions.custom.caliber.CaliberNotFound;
import com.ccc.sistema_balistico.repositories.bullet.BulletRepository;
import com.ccc.sistema_balistico.repositories.caliber.CaliberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

@Service
public class BulletImpl implements BulletService{

    @Autowired
    BulletRepository bulletRepository;
    @Autowired
    CaliberRepository caliberRepository;


    @Transactional(readOnly = true)
    @Override
    public Page<BulletDTO> getAll(Pageable pageable) {
        Page<BulletEntity> bulletEntityList = bulletRepository.findByIsDeleteFalse(pageable);
        return bulletEntityList.map(BulletMapper::toDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public BulletDTO getBullet(Long idBullet) {
        BulletEntity bulletEntity = bulletRepository.findById(idBullet).orElseThrow(()-> new BulletNotFound("Bullet Not Found"));
        if (bulletEntity.getIsDelete()) throw new BulletIsDeleted();
        return BulletMapper.toDTO(bulletEntity);
    }

    @Transactional
    @Override
    public BulletDTO createBullet(BulletDTO bulletDTO) {
        CaliberEntity caliber = caliberRepository.findById(bulletDTO.getCaliber()).orElseThrow(()-> new CaliberNotFound("Caliber Not Found"));
        if (caliber.getIsDelete()) throw new CaliberIsDeleted();

        bulletDTO.setIdBullet(null);
        bulletDTO.setCreatedAt(LocalDateTime.now());

        BulletEntity bulletEntity = BulletMapper.toEntity(bulletDTO,caliber);
        return BulletMapper.toDTO(bulletRepository.save(bulletEntity));
    }

    @Transactional
    @Override
    public BulletDTO updateBullet(Long idBullet, BulletDTO bulletDTO) {
        BulletEntity bulletEntity = bulletRepository.findById(idBullet).orElseThrow(()-> new BulletNotFound("Bullet Not Found"));
        bulletEntity.setManufacturer(bulletDTO.getManufacturer());
        bulletEntity.setLandsAndGrooves(bulletDTO.getLandsAndGrooves());
        bulletEntity.setPercussionType(bulletDTO.getPercussionType());
        bulletEntity.setTwistDirection(bulletDTO.getTwistDirection());

        return BulletMapper.toDTO(bulletRepository.save(bulletEntity));
    }

    @Transactional
    @Override
    public void deleteBullet(Long idBullet) {
        BulletEntity bulletEntity = bulletRepository.findById(idBullet).orElseThrow(()-> new BulletNotFound("Bullet Not Found"));
        bulletEntity.setIsDelete(true);
        bulletRepository.save(bulletEntity);
    }
}
