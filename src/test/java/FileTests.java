
import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.opencsv.CSVReader;
import domain.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;


import static org.assertj.core.api.Assertions.assertThat;

public class FileTests {

    OpenZip zipFile = new OpenZip();
    private String zipArchiveName = "test.zip", pdfFileName = "UEM.pdf", xlsxFileName = "stringsxlxs.xlsx",
            csvFileName = "test.csv", jsonFileName = "student.json";

    @Test
    void pdfTest() throws Exception {
        try (InputStream isPDF = zipFile.getFile(zipArchiveName, pdfFileName)) {
            PDF pdfFile = new PDF(isPDF);
            assertThat(pdfFile.text).contains("Подача заявлений, ходатайств");
        }
    }

    @Test
    void xlsTest() throws Exception {
        try (InputStream isXLS = zipFile.getFile(zipArchiveName, xlsxFileName)) {
            XLS xlsFile = new XLS(isXLS);
            assertThat(xlsFile.excel.getSheetAt(0).getRow(1).getCell(1).getStringCellValue()).contains("Новый");
        }
    }


    @Test
    void csvTest() throws Exception {
        try (InputStream isCSV = zipFile.getFile(zipArchiveName, csvFileName)) {
            CSVReader csvFile = new CSVReader(new InputStreamReader(isCSV, StandardCharsets.UTF_8));
            List <String[]> content = csvFile.readAll();
            assertThat(content).contains(new String[]{"name","age","active"}, new String[]{"Karl","23","true"});
        }
    }

    @Test
    void jsonTest() throws Exception {
        try (InputStream isJson=FileTests.class.getClassLoader().getResourceAsStream(jsonFileName)) {
            ObjectMapper objectMapper = new ObjectMapper();
            Student student = objectMapper.readValue(isJson,Student.class);
            assertThat(student.getName()).isEqualTo("Karl");
            assertThat(student.getAge()).isEqualTo(20);
            assertThat(student.getActive()).isEqualTo(true);
        }
    }}






