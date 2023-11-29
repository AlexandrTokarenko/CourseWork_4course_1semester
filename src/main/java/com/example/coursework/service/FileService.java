package com.example.coursework.service;

import com.example.coursework.entity.Image;
import com.example.coursework.entity.Realty;
import com.example.coursework.service.dataServices.ImageService;
import com.example.coursework.service.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    @Value("${cloud.aws.url}")
    private String path;

    @Autowired
    private ImageService imageService;

    @Autowired
    private StorageService storageService;

    public void save(int realtyId, MultipartFile[] files) {

        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String contentType = files[i].getContentType();
                if (contentType.equals("image/jpeg") || contentType.equals("image/png")) {
                    String filename = storageService.uploadFile(files[i]);
                    imageService.save(new Image(new Realty(realtyId), filename, i + 1));
                }
            }
        }
    }

    public void delete(String fileName) {

        storageService.deleteFile(fileName);
    }

    public void delete(List<String> fileNames){

        for (String fileName : fileNames) {
            delete(fileName);
        }
    }

    public List<String> getPathsByRealtyId(Integer realtyId) {

        return getPathByFileName(imageService.findAllFileNameByRealtyIdOrderByImageOrder(realtyId));
    }

    public List<String> getPathByFileName(List<String> fileNames) {

        List<String> paths = new ArrayList<>();
        for (String fileName : fileNames) {
            paths.add(getPathByFileName(fileName));
        }
        return paths;
    }

    public String getPathByFileName(String fileName) {

        return path + fileName;
    }
}
