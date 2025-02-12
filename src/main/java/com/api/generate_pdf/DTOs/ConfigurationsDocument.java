package com.api.generate_pdf.DTOs;


import java.io.File;

public class ConfigurationsDocument {
    private String title;
    private String rotate = "portrait";
    private String footerText = "Documento gerado automaticamente - 2025-02-07";
    private String templateName = "Default";
    private File file;

    public ConfigurationsDocument() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRotate() {
        return rotate;
    }

    public void setRotate(String rotate) {
        this.rotate = rotate;
    }

    public String getFooterText() {
        return footerText;
    }

    public void setFooterText(String footerText) {
        this.footerText = footerText;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
