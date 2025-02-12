package com.api.generate_pdf.mapping;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ConvertMultipartfileTo {

    public static File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        // Criando um arquivo temporário no sistema
        File convFile = File.createTempFile("temp", multipartFile.getOriginalFilename());

        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(multipartFile.getBytes());
        }

        return convFile;
    }
}
