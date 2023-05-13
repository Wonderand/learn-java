package io.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class test {

    public static void main(String[] args) {
        String url = "E:\\jdk17\\test\\demofile\\b.pdf";
        try {
            FileOutputStream fos = new FileOutputStream(url);
            Zkrpdf(fos);
            //pdftest(fos);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
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

}
