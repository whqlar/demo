package com.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by zhangmeng27 on 16/6/1.
 */
public class Main {


    public static void main(String[] args) throws Exception {

//
//        File file = new File("/Users/wu/Downloads/voucherId.txt");
//        File filecode = new File("/Users/wu/Downloads/code.txt");
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
//        BufferedReader readerCode = new BufferedReader(new InputStreamReader(new FileInputStream(filecode)));
//
//        StringBuffer buffer = new StringBuffer();
//        String line = null;
//        String lineCode = null;
//        boolean first = true;
//        while((line = reader.readLine()) != null && (lineCode = readerCode.readLine())!= null) {
//            if (first == true) {
//                first = false;
//                continue;
//            }
//            buffer.append("update cip_merchantvoucher_mervoucher set coupon_code='" +  lineCode + "' where voucher_id=" + line + ";\n");
//        }
//        System.out.println(buffer.toString());


        File file = new File("/Users/wu/Downloads/code.txt");

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        StringBuffer buffer = new StringBuffer();
        String line = null;
        boolean first = true;
        while((line = reader.readLine()) != null) {
            if (first == true) {
                first = false;
                continue;
            }
            buffer.append("update cip_merchantvoucher_code set status = 2 where template_id = 15 and code = '" + line +  "';\n");
        }
        System.out.println(buffer.toString());







//        Student student = Student.getDefaultStudent();
//
//        System.out.println(JSON.toJSONString(student));
//
//        List<String> records = Lists.newArrayList();
//        for (int i = 0; i < 504; i++) {
//            records.add(i + "i");
//        }
//
//        InputStream excelInputStream = ExportExcelUtil.createExcelInputStream(records);
//        FileOutputStream file = new FileOutputStream(new File("code.xlsx"));
//
//        byte[] b = new byte[500];
//        while (excelInputStream.available() > 0) {
//            excelInputStream.read(b);
//            file.write(b);
//            file.flush();
//        }
//        file.close();
//
//
    }
}
