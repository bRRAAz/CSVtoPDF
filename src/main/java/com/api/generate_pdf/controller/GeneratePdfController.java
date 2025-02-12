package com.api.generate_pdf.controller;
import com.api.generate_pdf.DTOs.ConfigurationsDocument;
import com.api.generate_pdf.mapping.ConvertMultipartfileTo;
import com.api.generate_pdf.service.SelectTemplate;
import com.api.generate_pdf.service.StartDocument;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class GeneratePdfController {

    @PostMapping
    public ResponseEntity<Resource> generatePdf(
            @RequestParam("file") MultipartFile multipartFile,
            @RequestParam("title") String title,
            @RequestParam("rotate") String rotate
    ) throws IOException {
        //Arquivo
        File file = ConvertMultipartfileTo.convertMultipartFileToFile(multipartFile);

        //Configurações do documento
        ConfigurationsDocument configurationsDocument = new ConfigurationsDocument();
        configurationsDocument.setFile(file);
        configurationsDocument.setTitle(title);
        configurationsDocument.setRotate(rotate);

        //Selecionar Template
        SelectTemplate document = new SelectTemplate();

        //Iniciar o PDF
        StartDocument startDocument = document.SelectTemplate(configurationsDocument);

        //Criação do arquivo de retorno
        ByteArrayOutputStream outputStream = startDocument.getOutputStream();
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

        //Resposta
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + title + ".pdf")
                .contentLength(resource.contentLength())
                .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                .body(resource);
    }

}
