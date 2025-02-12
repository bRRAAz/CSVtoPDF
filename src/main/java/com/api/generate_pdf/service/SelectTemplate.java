package com.api.generate_pdf.service;

import com.api.generate_pdf.DTOs.ConfigurationsDocument;
import com.api.generate_pdf.template.DefaultTemplate;

import java.io.File;

public class SelectTemplate {
    private StartDocument startDocument;

    public StartDocument SelectTemplate(ConfigurationsDocument configurationsDocument){
        switch (configurationsDocument.getTemplateName()){
            default:
                DefaultTemplate defaultTemplate = new DefaultTemplate();
                this.startDocument = defaultTemplate.GenerateDocument(configurationsDocument);
                return startDocument;
        }
    }

}
