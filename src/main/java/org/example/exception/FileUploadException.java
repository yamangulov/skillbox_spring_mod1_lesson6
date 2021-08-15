package org.example.exception;

import java.io.IOException;

/**
 * Created by Andrey.Yamangulov
 * Date: 15.08.2021
 * Time: 15:49
 */
public class FileUploadException extends Exception {
    public FileUploadException() {
    }

    public FileUploadException(String message) {
        super(message);
    }
}
