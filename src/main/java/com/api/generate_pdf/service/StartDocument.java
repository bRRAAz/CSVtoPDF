package com.api.generate_pdf.service;

import com.api.generate_pdf.DTOs.ConfigurationsDocument;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;


import java.io.ByteArrayOutputStream;

public class StartDocument {

    private Document documentpdf;
    private ByteArrayOutputStream outputStream;

    public StartDocument(ConfigurationsDocument configurationsDocument) {
        //Instanciar o novo documento
        this.documentpdf = new Document();

        //Definir a rotação do documento
        switch (configurationsDocument.getRotate()){
            case "portrait": documentpdf.setPageSize(PageSize.A4);
            break;
            case "landscape": documentpdf.setPageSize(PageSize.A4.rotate());
            break;
            default: documentpdf.setPageSize(PageSize.A4);
        }

        //Definir o output do documento
        this.outputStream = new ByteArrayOutputStream();

        //Criar o binary do do documento
        try {
            PdfWriter writer = PdfWriter.getInstance(this.documentpdf, this.outputStream);
            this.documentpdf.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Document getDocument() {
        return documentpdf;
    }

    public ByteArrayOutputStream getOutputStream() {
        return outputStream;
    }
}
