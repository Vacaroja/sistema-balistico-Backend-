package com.ccc.sistema_balistico.services.bulletimg;

import com.ccc.sistema_balistico.dto.BulletDTO;
import com.ccc.sistema_balistico.dto.ImageDTO;
import com.ccc.sistema_balistico.entities.BulletEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BulletImagesService {
    BulletDTO saveImageList(List<MultipartFile> file, Long idBullet);
    BulletDTO saveImageList(List<MultipartFile> file, BulletEntity bulletEntity);
    ImageDTO loadImage(String img);
    Void deleteImage(String path);
}
