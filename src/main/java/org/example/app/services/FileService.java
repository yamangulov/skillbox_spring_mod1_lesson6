package org.example.app.services;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class FileService {

    public List<String> getAllFiles() {
        File rootDir = new File(System.getProperty("user.home") + File.separator + "external_uploads");
        if (rootDir.list() == null) {
            return null;
        }
        return Arrays.asList(Objects.requireNonNull(rootDir.list()));
    }

    public FileSystemResource downloadFile(String fileName) {
        return new FileSystemResource(new File(System.getProperty("user.home") + File.separator + "external_uploads" + File.separator + fileName));
    }
}
