package fr.cfdt.gasel.groupeldap.service;

import au.com.bytecode.opencsv.CSVWriter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

@Service
public class CsvWriterService {

    private static final char DELIMITER = ';';
    private static final char QUOTE = '\"';
    private static final char ESCAPE_CHAR = '\0';
    private static final String LINE_END = "\r\n";

    public byte[] generateCsv(List<String[]> datas , HttpServletResponse response) throws IOException {
        //writer
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Writer writer = new OutputStreamWriter(outputStream, "ISO-8859-15");
        response.setHeader("Content-Disposition", "attachment; filename=liste_membres.csv");
        // serialisation avec opencsv
        CSVWriter csvWriter = new CSVWriter(writer, DELIMITER, QUOTE, ESCAPE_CHAR, LINE_END);
        csvWriter.writeAll(datas);
        csvWriter.close();
        return  outputStream.toByteArray();
    }
}
