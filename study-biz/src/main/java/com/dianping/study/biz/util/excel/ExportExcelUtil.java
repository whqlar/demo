package com.dianping.study.biz.util.excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created by wu
 * excel导出工具
 */
public class ExportExcelUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExportExcelUtil.class);

    /**
     * 生成2007 Excel文件
     * @return
     * @throws Exception
     */
    public static <T> InputStream createExcelInputStream(List<T> records) {
        Workbook wb = new XSSFWorkbook();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            wb.write(out);
            createWorkBook(wb, records);
            out.flush();
        } catch (Exception e) {
            LOGGER.error("createExcelInputStream error", e);
        }
        IOUtils.closeQuietly(out);

        return new ByteArrayInputStream(out.toByteArray());

    }

    private static <T> void createWorkBook(Workbook wb, List<T> records) {
        Sheet sheet = wb.createSheet("sheet1");
        createHeader(sheet);

        Row row;
        T record;
        for (int i = 0; i < records.size(); i++) {
            record = records.get(i);
            row = sheet.createRow(i + 1);
            if (record instanceof String) {
                row.createCell(0).setCellValue((String)record);
            } else if (record instanceof Double) {
                row.createCell(0).setCellValue((Double)record);
            }
        }
    }

    private static void createHeader(Sheet sheet) {
        Row row = sheet.createRow(0);
        for (ExcelHeader header : ExcelHeader.values()) {
            row.createCell(header.ordinal()).setCellValue(header.getColumnName());
        }
    }

    private static enum  ExcelHeader {

        code("code"),

        ;

        private String columnName;

        ExcelHeader(String columnName) {
            this.columnName = columnName;
        }

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }
    }

}
