package com.ccc.sistema_balistico.services.bulletimg;

import org.springframework.web.multipart.MultipartFile;

public interface BulletImagesService {
    String saveImage(MultipartFile file,Long idBullet);
    Void deleteImage(String path);
}
