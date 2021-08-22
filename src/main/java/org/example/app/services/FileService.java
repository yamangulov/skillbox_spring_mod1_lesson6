package org.example.app.services;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {
    public List<String> getAllFiles() {
        File rootDir = new File(System.getProperty("user.home") + File.separator + "external_uploads");
        String[] source = rootDir.list();
        List<String> result = new ArrayList<>();
        if (source == null) {
            return null;
        }
        for (String name : source) {
            result.add(name);
        }
        return result;
    }

    public ResponseEntity<FileSystemResource> downloadFile(String fileName) throws IOException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDispositionFormData("attachment", fileName);
        return new ResponseEntity<>(new FileSystemResource(new File(System.getProperty("user.home") + File.separator + "external_uploads" + File.separator + fileName)), httpHeaders, HttpStatus.OK);
    }
}
