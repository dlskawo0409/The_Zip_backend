package com.ssafy.thezip.common.storage.application;

import com.ssafy.thezip.common.storage.domain.StorageProperties;
import com.ssafy.thezip.common.storage.exception.StorageException;
import com.ssafy.thezip.common.storage.exception.StorageFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;


@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public void store(MultipartFile file, String fileName) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            Path destinationFile = this.rootLocation.resolve(
                            Paths.get(fileName))
                    .normalize().toAbsolutePath();
            System.out.println(destinationFile.getParent().getParent());
            System.out.println(Paths.get(this.rootLocation.toUri()).normalize());
            if (!destinationFile.getParent().getParent().equals(Paths.get(this.rootLocation.toUri()).normalize()) ){
                // This is a security check
                throw new StorageException(
                        "Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new StorageException("Failed to store file.", e);
        }
    }



    @Override
    public Path load(String fileName) {
        return rootLocation.resolve(fileName);
    }



    @Override
    public byte[] loadAsResource(String filename, String imageType) throws IOException {
        Path imagePath =Paths.get(rootLocation+"/"+imageType+"/"+filename);
        // 파일을 byte 배열로 읽어 반환
        System.out.println(imagePath);
        System.out.println(imagePath.getFileSystem());
        return Files.readAllBytes(imagePath);
    }

    @Override
    public void deleteOne(String fileName) {
        File file = new File(fileName);
        FileSystemUtils.deleteRecursively(file);
        System.out.println(file.getAbsolutePath());
    }

//    @Override
//    public void deleteAll() {
//        FileSystemUtils.deleteRecursively(rootLocation.toFile());}

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

}
