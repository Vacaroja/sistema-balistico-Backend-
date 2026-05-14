package com.ccc.sistema_balistico.services.bulletimg;

import com.ccc.sistema_balistico.entities.BulletEntity;
import com.ccc.sistema_balistico.entities.BulletImagesEntity;
import com.ccc.sistema_balistico.exceptions.custom.BulletNotFound;
import com.ccc.sistema_balistico.repositories.bullet.BulletRepository;
import com.ccc.sistema_balistico.repositories.bulletimg.BulletImageRepository;
import com.ccc.sistema_balistico.services.storage.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class BulletImageImpl implements BulletImagesService {

    @Autowired
    private BulletImageRepository bulletImageRepository;
    @Autowired
    private BulletRepository bulletRepository;
    @Autowired
    private FileStorageService fileStorageService;

    @Transactional
    @Override
    public String saveImage(MultipartFile file, Long idBullet) {
        BulletEntity bullet = bulletRepository.findById(idBullet).orElseThrow(() -> new BulletNotFound("Bullet Not Found for save image"));
        UUID imageUuid = UUID.randomUUID();
        String imagePath = fileStorageService.saveImageFile(file, imageUuid.toString());
        BulletImagesEntity bulletImages = BulletImagesEntity.builder().
                uuidBulletImages(imageUuid).
                idBullet(bullet).
                pathImage(imagePath).
                build();
        bulletImageRepository.save(bulletImages);

        return "File saved in " + imagePath;

    }

    @Override
    public Void deleteImage(String path) {
        return null;
    }
}
