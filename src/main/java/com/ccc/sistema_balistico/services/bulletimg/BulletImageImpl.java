package com.ccc.sistema_balistico.services.bulletimg;

import com.ccc.sistema_balistico.dto.BulletDTO;
import com.ccc.sistema_balistico.dto.ImageDTO;
import com.ccc.sistema_balistico.dto.mapper.BulletMapper;
import com.ccc.sistema_balistico.entities.BulletEntity;
import com.ccc.sistema_balistico.entities.BulletImagesEntity;
import com.ccc.sistema_balistico.exceptions.custom.BulletNotFound;
import com.ccc.sistema_balistico.repositories.bullet.BulletRepository;
import com.ccc.sistema_balistico.repositories.bulletimg.BulletImageRepository;
import com.ccc.sistema_balistico.services.storage.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class BulletImageImpl implements BulletImagesService {

    @Autowired
    private BulletImageRepository bulletImageRepository;
    @Autowired
    private BulletRepository bulletRepository;
    @Autowired
    private FileStorageService fileStorageService;

    private BulletImagesEntity saveImage(MultipartFile file, BulletEntity bullet) {
        UUID imageUuid = UUID.randomUUID();
        String imagePath = fileStorageService.saveImageFile(file, imageUuid.toString());
        BulletImagesEntity bulletImages = BulletImagesEntity.builder().
                uuidBulletImages(imageUuid).
                idBullet(bullet).
                pathImage(imagePath).
                build();

        return bulletImageRepository.save(bulletImages);

    }

    @Transactional
    @Override
    public BulletDTO saveImageList(List<MultipartFile> file, Long idBullet) {
        BulletEntity bullet = bulletRepository.findById(idBullet).orElseThrow(() -> new BulletNotFound("Bullet Not Found for save image"));
        return saveImageList(file, bullet);

    }

    @Transactional
    @Override
    public BulletDTO saveImageList(List<MultipartFile> file, BulletEntity bulletEntity) {
        List<BulletImagesEntity> imagePath = file.stream().filter(img -> img != null && !img.isEmpty()).map(img -> this.saveImage(img, bulletEntity)).toList();
        bulletEntity.getImagePaths().addAll(imagePath);


        return BulletMapper.toDTO(bulletEntity);

    }

    @Override
    public ImageDTO loadImage(String img) {
        Resource imageFile = fileStorageService.loadImageFile(img);
        String contentType = fileStorageService.getContentType(img);
        return ImageDTO.builder().img(imageFile).contentType(contentType).build();
    }


    @Override
    public Void deleteImage(String path) {
        return null;
    }
}
