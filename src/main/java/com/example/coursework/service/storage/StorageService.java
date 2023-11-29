package com.example.coursework.service.storage;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

@Service
public class StorageService {

    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    public String uploadFile(MultipartFile file) {

        String contentType = file.getContentType();

        String uuidFile = UUID.randomUUID().toString();
        String resultFilename = uuidFile + "." + contentType.substring(contentType.lastIndexOf("/") + 1);

        File fileObj = convertMultipartFileToFile(file);

        PutObjectRequest objectRequest = new PutObjectRequest(bucketName, resultFilename, fileObj);
        objectRequest.setCannedAcl(CannedAccessControlList.PublicRead);

        s3Client.putObject(objectRequest);
        fileObj.delete();

        return resultFilename;
    }

    public URL getUrl(String fileName) {

        return s3Client.getUrl(bucketName,fileName);
    }

    public byte[] downloadFile(String fileName) {

        S3Object s3Object = s3Client.getObject(bucketName, fileName);

        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String deleteFile(String fileName) {

        s3Client.deleteObject(bucketName,fileName);
        return fileName + " is removed";
    }

    private File convertMultipartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try(FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return convertedFile;
    }
}
