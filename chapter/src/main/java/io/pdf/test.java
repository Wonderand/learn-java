package io.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class test {

    public static void main(String[] args) {
//        String url = "E:\\VMware\\UbuntuISO\\ubuntu-22.04.2-desktop-amd64.iso";
//        String size = getSize(new File(url));
//        System.out.println(size);
//        ArrayList<String> result = new ArrayList<>();
//        String outputDirectory = "E:\\新建文件夹\\image";
//        getFiles("E:\\新建文件夹\\质检报告", result, ".pdf");
//        for (String pdfPath : result) {
//            try {
//                System.out.println("Converting PDF file: " + pdfPath);
//                convertPdfPagesToImages(pdfPath, outputDirectory);
//            } catch (IOException e) {
//                System.err.println("Error converting PDF file: " + pdfPath);
//                e.printStackTrace();
//            }
//        }
        //png压缩
        // 图片所在目录路径
        // 图片文件夹路径
        String sourceFolderPath = "E:\\新建文件夹\\image";
        // 输出压缩后的图片文件夹路径
        String targetFolderPath = "E:\\新建文件夹\\image\\compressed";

        // 图片所在目录路径
        String imageFolderPath = "E:\\jdk17\\test\\chapter\\src\\main\\resources\\";

        File folder = new File(imageFolderPath);
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png")); // 根据需要修改后缀名

            for (File file : files) {
                try {
                    ImageInfo imageInfo = Imaging.getImageInfo(file);

                    if (imageInfo != null) {
                        System.out.println("Processing file: " + file.getName());
                        System.out.println("Format: " + imageInfo.getFormat());
                        System.out.println("MimeType: " + imageInfo.getMimeType());
                        System.out.println("Width: " + imageInfo.getWidth());
                        System.out.println("Height: " + imageInfo.getHeight());
                        // ... 其他处理逻辑
                    } else {
                        System.out.println("无法识别或读取图像文件: " + file.getName());
                    }
                } catch (IOException e) {
                    System.err.println("处理[" + file.getName() + "]时发生错误: " + e.getMessage());
                } catch (ImageReadException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            System.out.println("指定的路径不是有效的目录");
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
     * 普通的pdf
     * @param ops
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

    //获取文件夹下的所有pdf文件
    public static void getFiles(String path, ArrayList<String> jpgFiles, String suffix) {
        File file = new File(path);
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isFile()) {
                if (f.getName().endsWith(suffix)) {
                    jpgFiles.add(f.getAbsolutePath());
                }
            } else {
                getFiles(f.getAbsolutePath(), jpgFiles, suffix);
            }
        }
    }
    //将文件夹下的所有pdf文件转换成图片
    public static void convertPdfPagesToImages(String pdfFilePath, String outputDirectory) throws IOException {
        // 加载PDF文档
        File pdfFile = new File(pdfFilePath);
        PDDocument document = Loader.loadPDF(pdfFile);

        try {
            // 创建PDF渲染器
            PDFRenderer pdfRenderer = new PDFRenderer(document);

            // 获取PDF页面数量
            int pageCount = document.getNumberOfPages();

            for (int i = 0; i < pageCount; i++) {
                // 获取当前时间作为唯一标识符
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");
                String uniqueId = now.format(formatter);

                // 设置文件名，包括PDF文件名、页码和唯一标识符
                String fileName = pdfFile.getName() + "_page_" + (i + 1) + "_" + uniqueId + ".png";
                String outputPath = outputDirectory + File.separator + fileName;

                // 渲染每一页为BufferedImage对象并保存为PNG图片
                BufferedImage bim = pdfRenderer.renderImageWithDPI(i, 300, ImageType.RGB);
                ImageIO.write(bim, "png", new File(outputPath));
            }

        } finally {
            // 关闭PDF文档以释放资源
            document.close();
        }
    }
}
