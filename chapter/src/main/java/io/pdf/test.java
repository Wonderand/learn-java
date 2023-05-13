package io.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.MathContext;

public class test {

    public static void main(String[] args) {
        String url = "E:\\jdk17\\test\\demofile\\4.mp4";
        String size = getSize(new File(url));
        System.out.println(size);
//        try {
//            FileOutputStream fos = new FileOutputStream(url);
//            Zkrpdf(fos);
//            //pdftest(fos);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }

    /**
     * 加密的pdf
     * @param ops
     */
    public static void Zkrpdf(FileOutputStream ops) {
        try {
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, ops);
            writer.setEncryption("userpass".getBytes(), "ownerpass".getBytes(),
                    PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
            document.open();
            Paragraph para = new Paragraph("Hello World!");
            document.add(para);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     *
     */
    public static void pdftest(FileOutputStream ops){

        try {
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, ops);
            document.open();
            Paragraph para = new Paragraph("Hello World!");
            document.add(para);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //获取文件大小
    public static String getSize(File file) {
        double result = 0;
        String unit = "字节";
        long length = file.length();
        if (length < 1024) {
            result = length;
        } else if (length < 1024 * 1024) {
            result = length / 1024.0;
            unit = "KB";
        } else if (length < 1024 * 1024 * 1024) {
            result = length / 1024.0 / 1024;
            unit = "MB";
        } else {
            result = length / 1024.0 / 1024 / 1024;
            unit = "GB";
        }
        BigDecimal bigDecimal = new BigDecimal(result + "", new MathContext(3));
        return bigDecimal.doubleValue() + unit;
    }

}
