package com.ccc.sistema_balistico.services.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String saveImageFile(MultipartFile file,String name);
    void deleteImage();
}
