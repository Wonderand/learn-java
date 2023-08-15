package io.pdf;
import com.aspose.words.Document;
import com.aspose.words.FontSettings;
import com.aspose.words.PdfSaveOptions;
import com.deepoove.poi.XWPFTemplate;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Word工具类
 * @author donghx
 * @author 2023-06-14
 */
public class PdfToWord {

    public static void main(String[] args) throws Exception{
//        FileInputStream inputStream = new FileInputStream(new File("E:\\360驱动\\WeChat Files\\wxid_vktfb4rp5k5g22\\FileStorage\\File\\2023-08\\关于做好电子签名和电子档案系统应用工作的函.pdf"));

        String pdf = "E:\\360驱动\\WeChat Files\\wxid_vktfb4rp5k5g22\\FileStorage\\File\\2023-08\\关于做好电子签名和电子档案系统应用工作的函.pdf";
        String word = "D:\\zdzip\\关于做好电子签名和电子档案系统应用工作的函.doc";

//        PdfToWord(pdf,"");
        test(pdf,word);
    }

    /**
     * 填充数据
     * @param input 输入word文件数据流
     * @param data 需要替换的数据映射
     * @param output 输出的word文件数据量
     * @throws IOException
     */
    public static void fillData(InputStream input, Map<String, Object> data, OutputStream output) throws IOException {
        //使用poi-tl.jar中的类填充数据
        try(XWPFTemplate template = XWPFTemplate.compile(input) ){
            template.render(data).writeAndClose(output);
        }catch (Exception ex){
            throw ex;
        }
    }

    /**
     * word转换为pdf
     * @param wordInput word文件输入流
     * @param pdfOutput pdf文件输出流
     * @throws Exception
     */
    public static void wordToPdf(InputStream wordInput, OutputStream pdfOutput) throws Exception {
        //通过aspose-words.jar中的类转换文件
        Document wordDoc = new Document(wordInput);
//        if(SystemUtils.isLinux()){
//            //设置汉字字体，否则转换后的文档汉字会乱码。
//            FontSettings settings = new FontSettings();
//            settings.setFontsFolder("/mydata/fonts",false);
//            wordDoc.setFontSettings(settings);
//        }
        FontSettings settings = new FontSettings();
        settings.setFontsFolder("/mydata/fonts",false);
        wordDoc.setFontSettings(settings);
        PdfSaveOptions pso = new PdfSaveOptions();
        wordDoc.save(pdfOutput, pso);
    }

    public static void PdfToWord(String pdfPath,String docPath) {
        PDDocument pdf = null;
        FileOutputStream docFos = null;
        Writer docWriter = null;
        try {

            pdf = PDDocument.load(new File(pdfPath));
            int pagenumber = pdf.getNumberOfPages();
            String pdfFileName = pdfPath.substring(pdfPath.lastIndexOf("/") + 1, pdfPath.lastIndexOf("."));
            String docFileName = pdfFileName + ".doc";
            File docFile = new File(docPath + File.separator + docFileName);
            if (!docFile.exists()) {
                docFile.createNewFile();
            }

            docFos = new FileOutputStream(docFile);
            docWriter = new OutputStreamWriter(docFos, "UTF-8");
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setSortByPosition(true);// 排序
            stripper.setStartPage(1);// 设置转换的开始页
            stripper.setEndPage(pagenumber);// 设置转换的结束页
            stripper.writeText(pdf, docWriter);
//            logger.info(pdfFileName + "文件已转换完成");
            System.out.println(pdfFileName + "文件已转换完成");
        } catch (IOException e) {
//            logger.error("PDF转word失败:", e);
            System.out.println("PDF转word失败:" + e);
        } finally {
            if (docWriter != null) {
                try {
                    docWriter.close();
                } catch (IOException e) {
//                    logger.error("PDF转word后，关闭docWriter流失败:", e);
                    System.out.println("PDF转word后，关闭docWriter流失败:" + e);
                }
            }

            if (pdf != null) {
                try {
                    pdf.close();
                } catch (IOException e) {
//                    logger.error("PDF转word后，关闭pdf流失败:", e);
                    System.out.println("PDF转word后，关闭pdf流失败:" + e);
                }
            }
        }
    }

    public static void test(String pdf,String word) throws IOException {
        PdfReader reader = new PdfReader(pdf);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            TextExtractionStrategy strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
            sb.append(strategy.getResultantText());
        }
        reader.close();
        String text = sb.toString();
        FileOutputStream fos = new FileOutputStream(word);
        fos.write(text.getBytes());
        fos.close();

    }
}