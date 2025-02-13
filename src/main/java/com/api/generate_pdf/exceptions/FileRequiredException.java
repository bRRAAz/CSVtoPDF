package com.api.generate_pdf.exceptions;


public class FileRequiredException extends RuntimeException{
    public FileRequiredException(){super("File is required");}
    public FileRequiredException(String message){super(message);}
}
