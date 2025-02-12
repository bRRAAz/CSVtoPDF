package com.api.generate_pdf.interfaces;


import com.api.generate_pdf.DTOs.ConfigurationsDocument;
import com.api.generate_pdf.service.StartDocument;
import com.lowagie.text.Document;

import java.io.File;


public interface Report {

    public default StartDocument startDocumentPDF(ConfigurationsDocument configurationsDocument){
        StartDocument pdfCreator = new StartDocument(configurationsDocument);
        Document document = pdfCreator.getDocument();
        document.open();
        return pdfCreator;
    }
    public void generateHeader(Document document, String title);
    public void generateBody(Document document, File file);
    public void generateFooter(Document document);
    public default void printReport(Document document){
        document.close();
    };
}
