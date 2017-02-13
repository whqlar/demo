package com.dianping.study.biz.util.excel;

import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by wu on 17/2/9.
 */
public class ImportExcelUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImportExcelUtil.class);

    public static <T> List<T> parseExcel(File file, Class<T> clazz) {
        try {
            String fileName = file.getName();
            FileInputStream inputStream = new FileInputStream(file);

            Workbook wb;
            if (fileName.endsWith(".xls")) {
                wb = new HSSFWorkbook(inputStream);
            } else if (fileName.endsWith(".xlsx")) {
                wb = new XSSFWorkbook(inputStream);
            } else {
                throw new IllegalArgumentException("excel版本不对");
            }

            Sheet sheet = wb.getSheetAt(0);
            return parseSheet(sheet, clazz);
        } catch (Exception e) {
            LOGGER.error("parseExcel exception", e);
        }
        return Lists.newArrayList();
    }

    private static <T> List<T> parseSheet(Sheet sheet, Class<T> clazz) {
        List<Field> hitFields = findHitFields(sheet.getRow(0), clazz);
        List results = Lists.newArrayList();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            results.add(parseCells(sheet.getRow(i), hitFields, clazz));
        }
        return results;
    }

    private static <T> T parseCells(Row cells, List<Field> hitFields, Class<T> clazz) {
        try {
            T instance = clazz.newInstance();
            for (int i = 0; i < cells.getLastCellNum(); i++) {
                Method method = findMatchedMethod(hitFields.get(i), clazz);
                method.invoke(instance, buildValue(cells.getCell(i), method));
            }
            return instance;
        } catch (Exception e) {
            LOGGER.error("parseCells error", e);
            throw new IllegalArgumentException("parseCells error", e);
        }
    }

    private static Object buildValue(Cell cell, Method method) {
        Object result = null;

        for (Class<?> parameterType : method.getParameterTypes()) {
            String param = parameterType.getSimpleName();
            if ("String".equals(param)) {
                result = cell.getStringCellValue();
            } else if ("Boolean".equals(param) || "boolean".equals(param)) {
                result = cell.getBooleanCellValue();
            } else if ("Date".equals(param)) {
                result = cell.getDateCellValue();
            } else {
                Double num = Double.valueOf(cell.getNumericCellValue());
                if ("Byte".equals(param) || "byte".equals(param)) {
                    result = num.byteValue();
                } else if("Short".equals(param) || "short".equals(param)) {
                    result = num.shortValue();
                } else if ("Integer".equals(param) || "int".equals(param)) {
                    result = num.intValue();
                } else if ("Long".equals(param) || "long".equals(param)) {
                    result = num.longValue();
                } else if ("Float".equals(param) || "float".equals(param)) {
                    result = num.floatValue();
                } else if ("Double".equals(param) || "double".equals(param)) {
                    result = cell.getNumericCellValue();
                }
            }
        }
        return result;
    }

    private static Method findMatchedMethod(Field field, Class clazz) {
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            String methodName = declaredMethod.getName().toLowerCase();
            if (methodName.startsWith("set") && methodName.endsWith(field.getName().toLowerCase())) {
                return declaredMethod;
            }
        }
        throw new IllegalArgumentException("找不到setter方法," + field.getName());
    }

    private static <T> List<Field> findHitFields(Row row, Class<T> clazz) {
        List<Field> hitFields = Lists.newArrayList();
        Field[] fields = clazz.getDeclaredFields();
        for (Cell cell : row) {
            for (Field field : fields) {
                if (field.getName().equalsIgnoreCase(cell.getStringCellValue())) {
                    hitFields.add(field);
                    break;
                }
            }
        }
        if (row.getLastCellNum()!= hitFields.size()) {
            throw new IllegalArgumentException("文档格式不匹配");
        }

        return hitFields;
    }
}
