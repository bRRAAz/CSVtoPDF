package com.api.generate_pdf.template;

import com.api.generate_pdf.DTOs.ConfigurationsDocument;
import com.api.generate_pdf.interfaces.Report;
import com.api.generate_pdf.service.CsvProcessor;
import com.api.generate_pdf.service.StartDocument;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class DefaultTemplate implements Report {

    private Document documentPDF;
    private ByteArrayOutputStream outputStream;

    public StartDocument GenerateDocument(ConfigurationsDocument configurationsDocument){
        StartDocument startDocument = startDocumentPDF(configurationsDocument);
        this.documentPDF = startDocument.getDocument();
        this.outputStream = startDocument.getOutputStream();
        generateHeader(documentPDF, configurationsDocument.getTitle());
        generateBody(documentPDF, configurationsDocument.getFile());
        generateFooter(documentPDF);
        printReport(documentPDF);
        return startDocument;
    }


    @Override
    public StartDocument startDocumentPDF(ConfigurationsDocument configurationsDocument) {
        return Report.super.startDocumentPDF(configurationsDocument);
    }


    @Override
    public void generateHeader(Document document, String title) {
        try {
            // Criando uma tabela para estruturar o cabeçalho
            PdfPTable headerTable = new PdfPTable(1);
            headerTable.setWidthPercentage(100); // Largura total da página
            headerTable.setSpacingAfter(10); // Espaço após o cabeçalho

            // Fonte para o título
            Font titleFont = new Font(Font.HELVETICA, 16, Font.BOLD, Color.BLACK);
            Paragraph titleParagraph = new Paragraph(title, titleFont);
            titleParagraph.setAlignment(Element.ALIGN_CENTER);

            // Adicionando o título na tabela
            PdfPCell titleCell = new PdfPCell();
            titleCell.addElement(titleParagraph);
            titleCell.setBorder(Rectangle.NO_BORDER);
            titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            titleCell.setPaddingBottom(5);
            headerTable.addCell(titleCell);

            // Linha separadora abaixo do título
            PdfPCell lineCell = new PdfPCell();
            lineCell.setBorder(Rectangle.BOTTOM);
            lineCell.setBorderWidthBottom(2);
            lineCell.setPaddingTop(5);
            headerTable.addCell(lineCell);

            // Adicionando a tabela ao documento
            document.add(headerTable);

        } catch (DocumentException e) {
            System.err.println("Erro ao gerar o cabeçalho: " + e.getMessage());
        }
    }

    @Override
    public void generateBody(Document document, File file) {
        try {
            // Pegando os nomes das colunas
            List<String> columnNames = CsvProcessor.getColumnNames(file);
            // Pegando os registros do CSV
            List<Map<String, String>> records = CsvProcessor.getRecords(file);

            PdfPTable table = new PdfPTable(columnNames.size());
            table.setWidthPercentage(100); // Largura total da página
            table.setSpacingBefore(10f);   // Espaço antes da tabela
            table.setSpacingAfter(10f);    // Espaço depois da tabela
            Font headerFont = new Font(Font.HELVETICA, 8, Font.BOLD, Color.WHITE);
            Font cellFont = new Font(Font.HELVETICA, 6, Font.NORMAL, Color.BLACK);


            PdfPCell headerCell;
            for(int h = 0; h<columnNames.size(); h++){
                headerCell = new PdfPCell(new Phrase(columnNames.get(h), headerFont));
                headerCell.setBackgroundColor(Color.DARK_GRAY); // Cor de fundo
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER); // Alinhamento centralizado
                headerCell.setPadding(4); // Espaçamento interno
                table.addCell(headerCell);
            }



            for(Map<String, String> row: records){
                for(int i = 0; i<columnNames.size(); i++){
                    PdfPCell cell = new PdfPCell(new Phrase(row.get(columnNames.get(i)), cellFont));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setPadding(4);
                    cell.setBorderColor(Color.BLACK);
                    table.addCell(cell);
                }
            }
            document.add(table);

        } catch (IOException e) {
            System.err.println("Erro ao processar o CSV: " + e.getMessage());
        }
    }

    @Override
    public void generateFooter(Document document) {
        Font footerFont = new Font(Font.HELVETICA, 8, Font.ITALIC, Color.GRAY);
        Paragraph footer = new Paragraph("Documento gerado automaticamente - " + java.time.LocalDate.now(), footerFont);
        footer.setAlignment(Element.ALIGN_CENTER);
        footer.setSpacingBefore(10);

        try {
            document.add(footer);
        } catch (DocumentException e) {
            System.err.println("Erro ao adicionar o rodapé: " + e.getMessage());
        }
    }

    @Override
    public void printReport(Document document) {
        Report.super.printReport(document);
    }

    public Document getDocumentPDF() {
        return documentPDF;
    }
}
