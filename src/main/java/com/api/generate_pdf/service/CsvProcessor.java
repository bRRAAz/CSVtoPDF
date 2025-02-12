package com.api.generate_pdf.service;
import org.apache.commons.csv.*;

import java.io.*;
import java.util.*;

public class CsvProcessor {

    public static List<String> getColumnNames(File file) throws IOException {
        try (Reader reader = new FileReader(file);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            return new ArrayList<>(csvParser.getHeaderMap().keySet());
        }
    }

    public static List<Map<String, String>> getRecords(File file) throws IOException {
        try (Reader reader = new FileReader(file);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            List<Map<String, String>> records = new ArrayList<>();
            List<String> columnNames = new ArrayList<>(csvParser.getHeaderMap().keySet());

            for (CSVRecord record : csvParser) {
                Map<String, String> row = new LinkedHashMap<>();
                for (String column : columnNames) {
                    row.put(column, record.get(column));
                }
                records.add(row);
            }

            return records;
        }
    }
}
