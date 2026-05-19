package com.ccc.sistema_balistico.services.bullet;

import com.ccc.sistema_balistico.dto.BulletDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface BulletService {
    Page<BulletDTO> getAll(Pageable pageable);
    BulletDTO getBullet(Long idBullet);
    BulletDTO createBullet(BulletDTO bulletDTO, List<MultipartFile> files);
    BulletDTO updateBullet(Long idBullet,BulletDTO bulletDTO);
    void deleteBullet(Long idBullet);
}
