package io.pdf;


import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class PdfUtils {

    public static final String BASEPATH = "E:/upload_pdfjs/test/";

    // 水印字符串
    public static final String WATERMARK = "测试水印";


    // 基本中文字体
    public static BaseFont bfChinese;
    // 标题字体
    public static Font titleFont;
    // 内容字体
    public static Font textFont;

    static {
        try {
        // 使用iTextAsian.jar中的中文字体,解决中文不能显示问题，BaseFont.NOT_EMBEDDED为新宋体
//            bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        /**
         * 扩展知识，其他字体
         */
//            2、使用Windows系统字体(TrueType)
            bfChinese = BaseFont.createFont("C:/WINDOWS/Fonts/SIMYOU.TTF", BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
//            3、使用资源字体(ClassPath)
//            bfChinese = BaseFont.createFont("/SIMYOU.TTF", BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);

//        titleFont = new Font(bfChinese, 16 , Font.BOLD);
//        textFont = new Font(bfChinese, 10 , Font.NORMAL);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws Exception {
        /**
         * 5. TODO 把代码和jar包整理成压缩包，到时候也一起上传
         */

        /**
         * 创建一个简单的pdf文件
         */
//        createPdf1(BASEPATH + "test.pdf");
        /**
         //         * 给PDF文件设置文件属性
         //         */
//        createPdf2(BASEPATH + "test2.pdf");
//        /**
//         * PDF中添加图片
//         */
        /**createPdf3(BASEPATH + "test3.pdf");*/
//        PdfReader reader = new PdfReader(BASEPATH + "/renren-fast开发文档3.0最新版.pdf");

        //createPdf9(BASEPATH+"a.pdf" , BASEPATH+"renren-fast开发文档3.0最新版.pdf");
//        //
//        /**
//         * PDF中创建表格
//         */
//        createPdf4(BASEPATH + "test4.pdf");
//        /**
//         * PDF中创建列表
//         */
//        createPdf5(BASEPATH + "test5.pdf");
//        /**
//         * PDF中设置样式/格式化输出，输出中文内容，涉及字体的大小和颜色设置,是否加粗等,必须引入itext-asian.jar
//         */
//        createPdf6(BASEPATH + "test6.pdf");
//        /**
//         * PDF设置密码与文件权限
//         */
//        createPdf7(BASEPATH + "test7.pdf");
//        /**
//         * 创建PDF时添加水印
//         */
//        createPdf8(BASEPATH + "test8.pdf");
//        //
//        /**
//         * 读取/修改已有的PDF文件： 1.添加内容；2.添加水印；3.添加页数
//         */
//        createPdf9(BASEPATH + "test9.pdf", BASEPATH + "test6.pdf");
//        /**
//         * 创建表单填充pdf模板,优点: 代码量减少, 添加的域可以直接设置需要的格式; 某些使用代码不容易实现的格式可以使用
//         */
//        createPdf10(BASEPATH + "test10.pdf", BASEPATH + "模板.pdf");
//        /**
//         *  多个PDF文件合并
//         */
//        createPdf11(BASEPATH + "test11.pdf");
    }



    /**
     * @description 多个PDF文件合并
     * @param filePath String
     * @return void
     * @author lt
     * @date 2023/6/13 11:54
     */
    private static void createPdf11(String filePath) throws Exception {
        java.util.List<File> filePathList = new ArrayList<File>();
        String temp;
        for (int i = 4; i <= 6; i++) {
            temp = BASEPATH + "test" + i + ".pdf";
            filePathList.add(new File(temp));
        }
        mergePdfFiles(filePathList, filePath);
    }

    /**
     * @description 合并文件
     * @param files List<File> 多个pdf路径
     * @param finalFilePath String 合成后的pdf文件路径
     * @return File
     * @author lt
     * @date 2023/6/13 11:44
     */
    public static File mergePdfFiles(java.util.List<File> files, String finalFilePath) throws Exception {
        if (files == null || files.size() == 0) {
            return null;
        } else if (files.size() == 1) {
            return files.get(0);
        }
        Document document = null;
        File resultfile = null;
        InputStream fis = null;
        try {
            resultfile = new File(finalFilePath);
            System.out.println("合并文件地址：-------------------" + resultfile.getAbsolutePath());
            File firstFile = files.get(0);
            fis = new FileInputStream(firstFile);
            document = new Document(new PdfReader(fis).getPageSize(1));
            /**
             * PdfCopy对象和最终输出的PDF文件关联
             */
            PdfCopy copy = new PdfCopy(document, new FileOutputStream(resultfile));
            document.open();
            for (File file : files) {
                System.out.println("****合并的文件名称****" + file.getName());
                /**
                 * 每一个PDF一个输入流，读取该PDF的总页数，循环合并
                 */
                InputStream is = new FileInputStream(file);
                PdfReader reader = new PdfReader(is);
                int n = reader.getNumberOfPages();
                for (int j = 1; j <= n; j++) {
                    document.newPage();
                    /**
                     * 依次读取被合并的pdf文件，添加进新合并文件
                     */
                    PdfImportedPage page = copy.getImportedPage(reader, j);
                    copy.addPage(page);
                }
                if (is != null) {
                    is.close();
                }
            }
        } finally {
            if (null != document) {
                document.close();
            }
            if (fis != null) {
                fis.close();
            }
            /*for (File file : files) {
                if (file.isFile()) {
                    file.delete();
                }
            }*/
        }
        return resultfile;
    }

    public static void insertTextToPdfs(String text, int fontSize, float X, float Y, PdfContentByte overContent, int rotation) {
        overContent.setFontAndSize(bfChinese, fontSize);
        overContent.setTextMatrix(30, 30);
        overContent.beginText();
        overContent.showTextAligned(Element.ALIGN_LEFT, text, X, Y, rotation);
        overContent.endText();
    }


    /**
     * @description 读取/修改已有的PDF文件， 1.添加内容；2.添加水印；3.添加页数
     * @param filePath String   修改后pdf路径
     * @param readFilePath String   读取的pdf文件路径
     * @return void
     * @author lt
     * @date 2023/6/13 18:19
     */
    public static void createPdf9(String filePath, String readFilePath) throws DocumentException, IOException {
        PdfReader pdfReader = new PdfReader(readFilePath);
        PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(filePath));
        // PDF总页数
        int numberOfPages = pdfReader.getNumberOfPages();
        /**
         * 添加满屏水印
         */
        addWaterMark(pdfReader, pdfStamper, numberOfPages);

        /**
         * 添加图片
         */
        //addPicture(pdfStamper, numberOfPages);

        /**
         * 添加总页数、第几页
         */
        addPageNumber(pdfStamper, numberOfPages);

        /**
         * 先关闭修改器, 在关闭读取
         */
        pdfStamper.close();
        pdfReader.close();
    }

    /**
     * @description 添加满屏水印
     * @param pdfReader PdfReader
     * @param pdfStamper PdfStamper
     * @param numberOfPages int
     * @return void
     * @author lt
     * @date 2023/6/13 14:18
     */
    private static void addWaterMark(PdfReader pdfReader, PdfStamper pdfStamper, int numberOfPages) {
        //  watermark为水印字符串
        String watermark = "测试水印";
        /**
         * PDF矩形区域对象
         */
        Rectangle pageRect = null;
        /**
         * 水印透明度对象
         */
        PdfGState gs = new PdfGState();
        /**
         * 设置填充不透明度
         */
        gs.setFillOpacity(0.3f);
        /**
         * 设置笔画不透明度
         */
        gs.setStrokeOpacity(0.4f);

        /**
         * 计算水印高度和宽度
         */
        JLabel label = new JLabel();
        FontMetrics metrics;
        int textH = 0;
        int textW = 0;
        label.setText(watermark);
        metrics = label.getFontMetrics(label.getFont());
        textH = metrics.getHeight();
        textW = metrics.stringWidth(label.getText());

        /**
         * pdf每一页加水印
         */
        PdfContentByte under;
        for (int i = 1; i <= numberOfPages; i++) {
            pageRect = pdfReader.getPageSizeWithRotation(i);
            under = pdfStamper.getOverContent(i);
            under.saveState();
            under.setGState(gs);
            under.beginText();
            under.setFontAndSize(bfChinese, 20);
            under.setRGBColorFill(145, 145, 145);
            /**
             * 设置水淹颜色
             */
            under.setColorFill(BaseColor.RED);
            /**
             * 页面填满屏充水印, height: 水印间隔高度； width： 水印间隔高度
             * 填充单个水印时，PdfContentByte.showTextAligned()只需要执行一次
             */
            for (int height = textH; height < pageRect.getHeight(); height = height + textH * 10) {
                for (int width = textW; width < pageRect.getWidth() + textW; width = width + textW * 3) {
                    /**
                     * x,y 是水印坐标
                     * rotation是水印倾斜角度
                     */
                    int x = width - textW;
                    int y = height - textH;
                    under.showTextAligned(Element.ALIGN_LEFT, watermark, x, y, 30);
                }
            }
            under.endText();
            under.restoreState();
        }
    }

    /**
     * @description 添加图片
     * @param pdfStamper PdfStamper
     * @param numberOfPages int
     * @return void
     * @author lt
     * @date 2023/6/13 14:17
     */
    private static void addPicture(PdfStamper pdfStamper, int numberOfPages) throws IOException, DocumentException {
        Image image = Image.getInstance(BASEPATH + "picture1.png");
//        image.scaleAbsolute(50, 50);
        image.scalePercent(50);
        image.setAbsolutePosition(100f, 300f);

        for (int i = 1; i <= numberOfPages; i++) {
            PdfContentByte content = pdfStamper.getUnderContent(i);
            content.addImage(image);
        }
    }

    /**
     * @description 添加总页数、第几页
     * @param pdfStamper PdfStamper
     * @param numberOfPages int
     * @return void
     * @author lt
     * @date 2023/6/13 14:11
     */
    private static void addPageNumber(PdfStamper pdfStamper, int numberOfPages) {
        for (int i = 1; i <= numberOfPages; i++) {
            PdfContentByte overContent = pdfStamper.getOverContent(i);
            // X坐标位置
            float xPosition = (float) (500);
            // Y坐标位置
            float yPosition2 = (float) (40);
            // 当前页
            insertTextToPdfs("共  " + numberOfPages + "  页" + "/" + "第  " + i + "  页", 12, xPosition, yPosition2, overContent, 0);
        }
    }

    /**
     * @description 读取/修改已有的PDF文件
     * @param filePath String   修改后pdf路径
     * @param readFilePath String   读取的pdf文件路径
     * @return void
     * @author lt
     * @date 2023/6/13 15:26
     */
    private static void createPdf10(String filePath, String readFilePath) throws IOException, DocumentException {
        // 填充创建pdf
        PdfReader reader = null;
        PdfStamper stamp = null;
        try {
            reader = new PdfReader(readFilePath);
            stamp = new PdfStamper(reader, new FileOutputStream(filePath));
            /**
             * 取出报表模板中的所有字段
             */
            AcroFields form = stamp.getAcroFields();
            /**
             * 设置字体, 否则不会显示中文
             */
            form.addSubstitutionFont(bfChinese);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");

            // 填充数据，对应文本域名称
            form.setField("公司编号", "GSBH公司");
            form.setField("机型", "JX");
            form.setField("有效批架次", "effValue");
            form.setField("填写日期", sdf.format(new Date()));
            form.setField("零件名称", "partName");
            form.setField("零件图号", "partNumber");
            form.setField("询问内容", "从数据库读取数据，然后填充到从模板读取到的表格中，再把处理完后的内容重新写到一个新建的PDF中。从数据库读取数据，然后填充到从模板读取到的表格中，再把处理完后的内容重新写到一个新建的PDF中。从数据库读取数据，然后填充到从模板读取到的表格中，再把处理完后的内容重新写到一个新建的PDF中");
            form.setField("解答编号", "explainNumber-3123213");
            form.setField("解答意见", "explainOpinion解答意见解答意见解答意见解答意见解答意见解答意见解答意见解答意见解答意见解答意见解答意见解答意见解答意见解答意见解答意见解答意见解答意见解答意见解答意见解答意见解答意见解答意见解答意见");
            form.setField("执行结论", "processConclusion");
            form.setField("密级", "公开");
            form.setField("分厂技术员", "user");
            form.setField("主管技术员", "user");
            form.setField("主管技术员1", "user");
            form.setField("审定", "user");
            form.setField("批准1", "user");
            form.setField("主管技术员1", "user");
            form.setField("主管技术员2", "user");

            // 这里true表示pdf可编辑
            stamp.setFormFlattening(true);
            stamp.flush();
            stamp.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /**
             *  注意: 关闭流有区分先后顺序： 先关闭修改器stamper，再关闭读取器
             */
            // 先关闭修改器stamper
            if (stamp != null) {
                try {
                    stamp.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // 再关闭读取器
            if (reader != null) {
                reader.close();
            }
        }
    }

    /**
     * @description 创建PDF时添加水印
     * @param filePath String
     * @return void
     * @author lt
     * @date 2023/6/13 15:13
     */
    private static void createPdf8(String filePath) throws DocumentException, IOException {
        //创建文件
        Document document = new Document();
        //建立一个书写器
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
        // 设置页面事件，在页面被创建的时候添加水印, 重写onStartPage方法
//        writer.setPageEvent(new PageEvent());
        document.open();


        //蓝色字体
        Font blueFont = new Font(bfChinese);
        blueFont.setColor(BaseColor.BLUE);
        //段落文本
        Paragraph paragraphBlue = new Paragraph("蓝色段落字体", blueFont);
        document.add(paragraphBlue);

        //绿色字体
        Font greenFont = new Font(bfChinese);
        greenFont.setColor(BaseColor.GREEN);

        /**
         * Itext的com.itextpdf.text.Chapter类设置章，com.itextpdf.text.Section类设置节。
         */

        //创建章节
        Paragraph chapterTitle = new Paragraph("段落标题xxxx", greenFont);
        Chapter chapter1 = new Chapter(chapterTitle, 1);
        chapter1.setNumberDepth(0);

        Paragraph sectionTitle = new Paragraph("部分标题", greenFont);
        Section section1 = chapter1.addSection(sectionTitle);

        Paragraph sectionContent = new Paragraph("部分内容", blueFont);
        section1.add(sectionContent);

        //将章节添加到文章中
        document.add(chapter1);

        //关闭文档
        document.close();
        //关闭书写器
        writer.close();
    }

    /**
     * @description PDF设置密码与编辑权限，需要引入bcprov-jdk15on.jar包, 未导入包时会报以下异常:java.lang.NoClassDefFoundError: org/bouncycastle/asn1/ASN1OctetString
     *              iText也提供了对PDF文件的安全保护，通过书写器（Writer）的setEncryption方法，可以设定文档的用户口令、只读、可打印等属性。
     * @param filePath String
     * @return void
     * @author lt
     * @date 2023/6/13 13:39
     */
    private static void createPdf7(String filePath) throws FileNotFoundException, DocumentException {
        // 创建文件
        Document document = new Document();
        // 建立一个书写器
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));

        //用户密码
        String userPassword = "123456";
        //拥有者密码
        String ownerPassword = "lt";

        /**
         * 1. 如果是读取本地pdf文件则可以用 pdfStamper.setEncryption方法
         * 2. 如果是创建的pdf则可以用 pdfWriter.setEncryption方法
         * 3. setEncrption有4个参数：userPassword, ownerPassword, permissions, encryptionType
         *      userPassword为用户密码，当用户打开时输入此密码将被赋予相应权限，该选项可以填null，表示无密码
         *      ownerPassword为管理员密码，将获得文档全部权限，也可以填null
         *      permissions为用户权限，多个权限以或(|)连接
         *          PdfWriter.ALLOW_PRINTING：允许打印
         *          PdfWriter.ALLOW_MODIFY_CONTENTS：允许编辑内容
         *          PdfWriter.ALLOW_COPY：允许复制
         *          PdfWriter.ALLOW_MODIFY_ANNOTATIONS：允许编辑注释
         *          PdfWriter.ALLOW_FILL_IN：允许填充表单（128位加密）
         *          PdfWriter.ALLOW_SCREENREADERS：允许用户提取文本和图形以供易访问性设备使用（128位加密）
         *          PdfWriter.ALLOW_ASSEMBLY：允许用户插入、删除和旋转页面和添加书签（128位加密）
         *          PdfWriter.ALLOW_DEGRADED_PRINTING：允许低质量打印（128位加密）
         *      encryptionType为加密方式，有4种:
         *           PdfWriter.STANDARD_ENCRYPTION_40
         *           PdfWriter.STANDARD_ENCRYPTION_128
         *          PdfWriter.ENCRYPTION_AES_128
         *          PdfWriter.ENCRYPTION_AES_256
         */
        // 赋值密码、权限、加密方式
        writer.setEncryption(userPassword.getBytes(), ownerPassword.getBytes()
                ,  PdfWriter.ALLOW_PRINTING | PdfWriter.ALLOW_MODIFY_ANNOTATIONS | PdfWriter.ALLOW_MODIFY_CONTENTS  | PdfWriter.ALLOW_FILL_IN
//                   | PdfWriter.ALLOW_COPY | PdfWriter.ALLOW_SCREENREADERS | PdfWriter.ALLOW_ASSEMBLY | PdfWriter.ALLOW_DEGRADED_PRINTING
                , PdfWriter.ENCRYPTION_AES_128);
        // 打开文件
        document.open();
        // 添加内容
        document.add(new Paragraph("Test Permission"));
        // 关闭文档
        document.close();
        // 关闭书写器
        writer.close();
    }

    /**
     * @description PDF中设置样式/格式化输出，输出中文内容, 字体的大小和颜色设置,是否加粗等,必须引入itext-asian.jar
     * @param filePath String
     * @return void
     * @author lt
     * @date 2023/6/13 11:48
     */
    private static void createPdf6(String filePath) throws DocumentException, IOException {
        /**
         *float llx, float lly: 两个为默认坐标，创建PDF时，Rectangle rectangle = new Rectangle(x, y, pdfWidth, pdfHeight);，尽量不要修改默认坐标位置。即，使用Rectangle rectangle = new Rectangle(0, 0, 595.27563, 841.8898F);这种方式创建，如果修改了x、y参数，虽然pdf在阅读器中的显示没有问题，但是对pdf做电子签名时，签章坐标会受到x、y参数的影响。
         *  float urx,  PDF宽度
         *  float ury   PDF高度
         */
        Rectangle rectangle = new Rectangle(0, 0, PageSize.A4.getWidth(), PageSize.A4.getHeight());
        //创建文件
        Document document = new Document(rectangle);
        //建立一个书写器
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
        //打开文件
        document.open();

        //蓝色加粗字体
        Font blueFont = new Font(titleFont);
        blueFont.setColor(BaseColor.BLUE);

        //段落文本
        Paragraph paragraphBlue = new Paragraph("蓝色段落字体(加粗)", blueFont);
        paragraphBlue.setAlignment(Element.ALIGN_LEFT);
        document.add(paragraphBlue);

        /**
         * 文本块(Chunk)是处理文本的最小单位，有一串带格式（包括字体、颜色、大小）的字符串组成。如以下代码就是产生一个字体为HELVETICA、大小为12、带下划线的字符串
         * 多个Chunk添加时不会自动换行
         */
        /*Font font = new Font(bfChinese, 12, Font.UNDERLINE);
        Chunk chunk = new Chunk("This text is Chunk,一个字体为HELVETICA，大小为12，带下划线的字符串。", font);
        document.add(chunk);*/
        /**
         * 將文本块添加进短语Phrase
         */
        Phrase phrase = new Phrase(new Chunk("测试文本块", new Font(bfChinese, 10, Font.UNDERLINE)));

        Paragraph paragraph = new Paragraph();
        paragraph.add(phrase);
        /**
         * setAlignment(): 设置段落对齐方式，默认左对齐
         */
        paragraph.setAlignment(Element.ALIGN_LEFT);
        document.add(paragraph);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        document.add(paragraph);
        paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
        document.add(paragraph);
        paragraph.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
        document.add(paragraph);
        /**
         * setSpacingBefore(): 设置段落上面空白宽度
         */
        paragraph.setSpacingBefore(20);
        document.add(paragraph);
        paragraph.setSpacingBefore(0);
        /**
         * setSpacingAfter(): 设置段落下面空白宽度
         */
        paragraph.setSpacingAfter(20);
        document.add(paragraph);
        /**
         *  setIndentationLeft(): 设置段落左边缩进
         */
        paragraph.setIndentationLeft(20);
        document.add(paragraph);
        /**
         * setIndentationRight(): 设置段落右边缩进
         */
        paragraph.setIndentationRight(20);
        document.add(paragraph);
        document.add(paragraph);


        //绿色字体
        Font greenFont = new Font(bfChinese);
        greenFont.setColor(BaseColor.BLACK);
        /**
         * Itext的com.itextpdf.text.Chapter类设置章，com.itextpdf.text.Section类设置节。
         * 新的Chapter章会换页
         */
        Paragraph chapterTitle = new Paragraph("段落标题绿色xxxx", greenFont);
        /**
         * 创建章节1
         */
        Chapter chapter1 = new Chapter( new Paragraph("第一章", greenFont), 1);
        chapter1.setNumberDepth(0);

        Paragraph sectionTitle = new Paragraph("节标题", greenFont);
        /**
         * 节的序号是自动生成的
         */
        // 第1节
        Section section1 = chapter1.addSection(new Paragraph("第一章第一节", greenFont));
        Paragraph sectionContent = new Paragraph("部分内容", textFont);
        sectionContent.setIndentationLeft(20);
        section1.add(sectionContent);
        // 第2节
        Section section2 = chapter1.addSection(new Paragraph("第一章第二节", greenFont));
        section2.add(sectionContent);
        /**
         * 创建章节2
         */
        Chapter chapter2 = new Chapter( new Paragraph("第二章", greenFont), 1);
        chapter2.setNumberDepth(0);
        // 第1节
        Section section3 = chapter2.addSection(new Paragraph("第二章第一节", greenFont));
        section3.add(sectionContent);

        /**
         * 将章节添加到文章中
         */
        document.add(chapter1);
        document.add(chapter2);

        //关闭文档
        document.close();
        //关闭书写器
        writer.close();
    }

    /**
     * @description PDF中创建列表
     * @param filePath String
     * @return void
     * @author lt
     * @date 2023/6/13 11:40
     */
    private static void createPdf5(String filePath) throws FileNotFoundException, DocumentException {
        //创建文件
        Document document = new Document();
        //建立一个书写器
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
        //打开文件
        document.open();
        //添加内容
        document.add(new Paragraph("有序列表", titleFont));

        /**
         * 有序列表(List(List.ORDERED))
         */
        List orderedList = new List(List.ORDERED);
        orderedList.add(new ListItem("Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one"));
        orderedList.add(new ListItem("Item two"));
        orderedList.add(new ListItem("Item three"));
        document.add(orderedList);

        /**
         * 无序列表(List(List.UNORDERED))
         */
        // 添加空白行
        document.add(new Paragraph(""));
        document.add(new Paragraph("无序列表", titleFont));
        List unOrderedList = new List(List.UNORDERED);
        unOrderedList.add(new ListItem("Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one"));
        unOrderedList.add(new ListItem("Item two"));
        unOrderedList.add(new ListItem("Item three"));
        document.add(unOrderedList);

        /**
         * 罗马数字列表(RomanList)
         */
        // 添加空白行
        document.add(new Paragraph(""));
        document.add(new Paragraph("罗马数字列表", titleFont));
        RomanList romanList = new RomanList();
        romanList.add(new ListItem("Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one"));
        romanList.add(new ListItem("Item two"));
        romanList.add(new ListItem("Item three"));
        document.add(romanList);

        /**
         * 希腊字母列表（GreekList）
         */
        // 添加空白行
        document.add(new Paragraph(""));
        document.add(new Paragraph("希腊字母列表", titleFont));
        GreekList greekList = new GreekList();
        greekList.add(new ListItem("Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one"));
        greekList.add(new ListItem("Item two"));
        greekList.add(new ListItem("Item three"));
        document.add(greekList);

        /**
         * ZapfDingbats列表
         */
        // 添加空白行
        document.add(new Paragraph(""));
        document.add(new Paragraph("ZapfDingbats列表", titleFont));
        /**
         * int zn: 不同数字表示不同的图标
         * int symbolIndent: 表示每行内容离图标的距离
         */
        ZapfDingbatsList zapfDingbatsList = new ZapfDingbatsList(80, 20);
        zapfDingbatsList.add(new ListItem("Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one Item one"));
        zapfDingbatsList.add(new ListItem("Item two"));
        zapfDingbatsList.add(new ListItem("Item three"));
        document.add(zapfDingbatsList);

        /***
         * 列表嵌套
         */
        // 添加空白行
        document.add(new Paragraph(""));
        document.add(new Paragraph("列表嵌套", titleFont));
        List nestedList = new List(List.UNORDERED);
        nestedList.add(new ListItem("Item 1 "));
        // 子列表1
        List subList = new List(true, false, 30);
        // 设置内容和序号距离
        Chunk chunk = new Chunk("", FontFactory.getFont(FontFactory.HELVETICA, 6));
        subList.setListSymbol(chunk);
        subList.add("AAAAAA");
        subList.add("BBBBBB");
        nestedList.add(subList);
        nestedList.add(new ListItem("Item 2 "));
        // 子列表2
        subList = new List(true, false, 30);
        // 设置内容和序号距离
        subList.setListSymbol(chunk);
        subList.add("CCCCCC");
        subList.add("DDDDDD");
        nestedList.add(subList);
        document.add(nestedList);

        //关闭文档
        document.close();
        //关闭书写器
        writer.close();
    }

    /**
     * @description PDF中创建表格
     * @param filePath String
     * @return void
     * @author lt
     * @date 2023/6/13 11:17
     */
    private static void createPdf4(String filePath) throws FileNotFoundException, DocumentException {
        /**
         * 设置纸张为A4纸大小,如果没有传入参数，默认是A4大小
         */
        Rectangle pageSize = new Rectangle(PageSize.A4.getWidth(), PageSize.A4.getHeight());
        // 创建文件
        /**
         * 设置文件的参数,pageSize是文档页面的大小; 参数marginLeft、marginRight、marginTop、marginBottom分别为左、右、上、下的 页边距
         */
        Document document = new Document(pageSize, 70f, 70f, 120f, 139f);
        //建立一个书写器
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
        //打开文件
        document.open();
        //添加内容
        document.add(new Paragraph("一般表格", textFont));
        /**
         * 一般表格
         */
        PdfPTable normalPdfPTable = getNormalPdfPTable(writer);
        /**
         * 生成带有格式的表格
         */
        PdfPTable table = getPdfPTable(writer);
        /**
         *    把表格添加到文件中
         */
        document.add(normalPdfPTable);
        document.add(new Paragraph("帶有格式的表格", textFont));
        document.add(table);
        //关闭文档
        document.close();
        //关闭书写器
        writer.close();
    }

    /**
     * @description 生成嵌套表
     * @return void
     * @author lt
     * @date 2023/6/13 17:23
     */
    private static PdfPTable createNestedTable() throws DocumentException {
        // Creating nested table for contact
        float [] pointColumnWidths2 = {150f, 150f};
        PdfPTable nestedTable = new PdfPTable(pointColumnWidths2);
        // Populating row 1 and adding it to the nested table
        PdfPCell nestedCell1 = createPdfCell("nestedCell1", textFont, 2, 1, 0, 0, 0, 0, 0, BaseColor.BLACK, BaseColor.GREEN);
        nestedTable.addCell(nestedCell1);
        // Populating row 1 and adding it to the nested table
        PdfPCell nestedCell2 = createPdfCell("nestedCell2", textFont, 2, 1, 0, 0, 0, 0, 0, BaseColor.BLACK, BaseColor.GREEN);
        nestedTable.addCell(nestedCell2);
        PdfPCell nestedCell3 = createPdfCell("nestedCell3", textFont, 2, 1, 0, 0, 0, 0, 0, BaseColor.BLACK, BaseColor.GREEN);
        nestedTable.addCell(nestedCell3);
        // Populating row 1 and adding it to the nested table
        PdfPCell nestedCell4 = createPdfCell("nestedCell4", textFont, 2, 1, 0, 0, 0, 0, 0, BaseColor.BLACK, BaseColor.GREEN);
        nestedTable.addCell(nestedCell4);
        return nestedTable;
    }

    /**
     * @description 正常表格
     * @param writer PdfWriter
     * @return PdfPTable
     * @author lt
     * @date 2023/6/13 16:52
     */
    private static PdfPTable getNormalPdfPTable(PdfWriter writer) throws DocumentException {
        // 3列的表.
        PdfPTable table = new PdfPTable(3);
        // 宽度100%填充
        table.setWidthPercentage(100);
        // 设置表格的宽度 根据实际需要设置
//        table.setTotalWidth(500);
        /**
         * 锁住表格宽度，注意：根据实际需要设置，如果锁住宽度，有可能出现表格宽度过宽，超出页面的问题
         */
//        table.setLockedWidth(true);
        // 每列分别设置列宽
        table.setWidths(new float[]{3f, 1f, 2f});
        // 设置表格上面空白宽度
        table.setSpacingBefore(10f);
        // 设置表格下面空白宽度
        table.setSpacingAfter(10f);
        // 设置表格默认为无边框, 根据实际需求调整
        table.getDefaultCell().setBorder(0);
        PdfContentByte directContentUnder = writer.getDirectContentUnder();

        PdfPCell cell1 = createPdfCell("Cell1", textFont, 1, 1, 0, 0, 0, 0, 0, BaseColor.BLACK, BaseColor.WHITE);
        table.addCell(cell1);
        PdfPCell cell2 = createPdfCell("Cell2", textFont, 1, 1, 0, 0, 0, 0, 0, BaseColor.BLACK, BaseColor.WHITE);
        table.addCell(cell2);
        PdfPCell cell3 = createPdfCell("Cell3", textFont, 1, 1, 0, 0, 0, 0, 0, BaseColor.BLACK, BaseColor.WHITE);
        table.addCell(cell3);
        PdfPCell cell4 = createPdfCell("Cell4", textFont, 1, 1, 0, 0, 0, 0, 0, BaseColor.BLACK, BaseColor.WHITE);
        table.addCell(cell4);
        PdfPCell cell5 = createPdfCell("Cell5", textFont, 1, 1, 0, 0, 0, 0, 0, BaseColor.BLACK, BaseColor.WHITE);
        table.addCell(cell5);
        PdfPCell cell6 = createPdfCell("Cell6", textFont, 1, 1, 0, 0, 0, 0, 0, BaseColor.BLACK, BaseColor.WHITE);
        table.addCell(cell6);
        PdfPCell cell7 = createPdfCell("Cell7", textFont, 1, 1, 0, 0, 0, 0, 0, BaseColor.BLACK, BaseColor.WHITE);
        table.addCell(cell7);
        PdfPCell Cell8 = createPdfCell("Cell8", textFont, 1, 1, 0, 0, 0, 0, 0, BaseColor.BLACK, BaseColor.WHITE);
        table.addCell(Cell8);
        PdfPCell Cell9 = createPdfCell("Cell9", textFont, 1, 1, 0, 0, 0, 0, 0, BaseColor.BLACK, BaseColor.WHITE);
        table.addCell(Cell9);
        return table;
    }

    /**
     * @description 生成带有格式的表格
     * @param writer PdfWriter
     * @return PdfPTable
     * @author lt
     * @date 2023/6/13 16:51
     */
    private static PdfPTable getPdfPTable(PdfWriter writer) throws DocumentException {
        // 3列的表.
        PdfPTable table = new PdfPTable(3);
        // 宽度100%填充
        table.setWidthPercentage(100);
        // 设置表格的宽度 根据实际需要设置
//        table.setTotalWidth(500);
        /**
         * 锁住表格宽度，注意：根据实际需要设置，如果锁住宽度，有可能出现表格宽度过宽，超出页面的问题
         */
//        table.setLockedWidth(true);
        // 每列分别设置列宽
        table.setWidths(new float[]{3f, 1f, 2f});
        // 设置表格上面空白宽度
        table.setSpacingBefore(10f);
        // 设置表格下面空白宽度
        table.setSpacingAfter(10f);
        // 设置表格默认为无边框, 根据实际需求调整
        table.getDefaultCell().setBorder(0);
        PdfContentByte directContentUnder = writer.getDirectContentUnder();

        // 合并第一列的1,2两行
        Font font = new Font(bfChinese, 18);
        font.setColor(BaseColor.RED);
        font.setStyle(Font.BOLD);
        PdfPCell cell1 = createPdfCell("Cell1", font, 2, 1, 0, 0, 0, 0, 0, BaseColor.BLUE, BaseColor.ORANGE);
        table.addCell(cell1);
        PdfPCell cell2 = createPdfCell("Cell2", textFont, 1, 1, 0, 0, 0, 0, 0, BaseColor.GREEN, BaseColor.WHITE);
        table.addCell(cell2);
        PdfPCell cell3 = createPdfCell("Cell3", textFont, 1, 1, 0, 0, 0, 0, 0, BaseColor.RED, BaseColor.WHITE);
        table.addCell(cell3);

        // 合并第二行的2,3列
        PdfPCell cell4 = createPdfCell("Cell4", textFont, 1, 2, 0, 0, 0, 0, 0, BaseColor.BLACK, BaseColor.WHITE);
        table.addCell(cell4);
        PdfPCell cell5 = createPdfCell("Cell5", textFont, 1, 1, 0, 0, 0, 0, 0, BaseColor.BLACK, BaseColor.WHITE);
        /**
         * 设置背景橘色
         */
        cell5.setBackgroundColor(BaseColor.ORANGE);
        table.addCell(cell5);
        PdfPCell cell6 = createPdfCell("Cell6", textFont, 1, 1, 0, 0, 0, 0, 0, BaseColor.BLACK, BaseColor.WHITE);
        table.addCell(cell6);
//        PdfPCell cell7 = createPdfCell("Cell7", textFont, 1, 1, 0, 0, 0, 0, 0, BaseColor.BLACK, BaseColor.WHITE);
        /**
         * 单元格嵌套表, 嵌套时，单元格没有设置固定行高（setFixedHeight()），所以会被拉伸，根据实际情况决定是否设置
         */
        PdfPTable nestedTable = createNestedTable();
        PdfPCell cell7 = new PdfPCell();
        // 設置固定行高
//        cell7.setFixedHeight(20f);
        cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell7.addElement(nestedTable);
        table.addCell(cell7);

        return table;
    }

    /**
     * @param pdfPTable         PdfPTable
     * @param value             String
     * @param font              Font
     * @param rowspan           int   合并行
     * @param colsspan          int  合并列
     * @param minimumHeight     int 单元格最小高度
     * @param borderWidthTop    float  单元格上边界框粗细
     * @param borderWidthLeft   float  单元格左边界框粗细
     * @param borderWidthRight  float    单元格右边界框粗细
     * @param borderWidthBottom float   单元格下边界框粗细
     * @param borderColor       BaseColor 边框颜色
     * @param backgroundColor   BaseColor   单元格背景颜色
     * @return void
     * @description 创建单元格
     * @author lt
     * @date 2023/6/13 17:30
     */
    private static PdfPCell createPdfCell(String value, Font font, int rowspan, int colsspan, int minimumHeight, float borderWidthTop, float borderWidthLeft,
                                          float borderWidthRight, float borderWidthBottom, BaseColor borderColor, BaseColor backgroundColor) {
        PdfPCell pdfPCell = createCell(value, font);
        if (0 != rowspan) {
            /**
             * 合并行
             */
            pdfPCell.setRowspan(rowspan);
        }
        if (0 != colsspan) {
            /**
             * 合并列
             */
            pdfPCell.setColspan(colsspan);
        }
        if (0 != minimumHeight) {
            /**
             * 最小高度，注意: 一般根据需要设置单元格最小高度,内容多了页面能自动延伸; 如果采用设置单元格固定高度(pdfPCell.setFixedHeight();)时,需要计算,同时如果单元格内容如果过多, 单元格就不会自动扩展, 多余内容会看不见,根据需求使用
             */
            pdfPCell.setMinimumHeight(minimumHeight);
        }
        if (0 != borderWidthTop) {
            /**
             * 单元格上边界框粗细
             */
            pdfPCell.setBorderWidthTop(borderWidthTop);
        }
        if (0 != borderWidthLeft) {
            /**
             * 单元格左边界框粗细
             */
            pdfPCell.setBorderWidthLeft(borderWidthLeft);
        }
        if (0 != borderWidthRight) {
            /**
             * 单元格右边界框粗细
             */
            pdfPCell.setBorderWidthRight(borderWidthRight);
        }
        if (0 != borderWidthBottom) {
            /**
             * 单元格下边界框粗细
             */
            pdfPCell.setBorderWidthBottom(borderWidthBottom);
        }
        /**
         * 边框颜色
         */
        pdfPCell.setBorderColor(borderColor);
        /**
         * 背景颜色
         */
        pdfPCell.setBackgroundColor(backgroundColor);
        /**
         * 注意: 设置对齐方式时, 要使内容居中, setHorizontalAlignment()和setVerticalAlignment()只要其中一个设置Element.ALIGN_CENTER就行,另外一个方法就不用设置, 两个都设置会达不到效果,
         */
        // 设置内容水平对齐方式， Element.ALIGN_CENTER：水平居中
        pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        // 设置内容垂直对齐方式，Element.ALIGN_MIDDLE： 垂直居中
        pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return pdfPCell;
    }

    /**
     * @description 创建单元格并填充数据
     * @param value String  单元格数据
     * @param font Font     字体
     * @return PdfPCell
     * @author lt
     * @date 2023/6/13 17:28
     */
    public static PdfPCell createCell(String value, Font font) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPhrase(new Phrase(value, font));
        return cell;
    }

    /**
     * @description PDF中添加图片
     * @param s String
     * @return void
     * @author lt
     * @date 2023/6/13 10:52
     */
    private static void createPdf3(String filePath) throws DocumentException, IOException {
        //创建文件
        Document document = new Document();
        //建立一个书写器
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
        //打开文件
        document.open();
        //添加内容
        document.add(new Paragraph("ADD picture here"));

        // 本地图片1
        Image image1 = Image.getInstance(BASEPATH + "picture1.png");
        // 注意: setAlignment和setAbsolutePosition选任一
        image1.setAlignment(Image.ALIGN_CENTER);
        //设置图片位置的x轴和y周
//        image1.setAbsolutePosition(100f, 550f);
        //设置图片的宽度和高度
        image1.scaleAbsolute(200, 200);
        //将图片1添加到pdf文件中
        document.add(image1);

        // 服务器图片2
        Image image2 = Image.getInstance(new URL("https://www.baidu.com/img/flexible/logo/pc/result.png"));
//        image2.setAlignment(Image.ALIGN_CENTER);
        //设置图片位置的x轴和y周
        image2.setAbsolutePosition(100f, 300f);
        //将图片2添加到pdf文件中
        document.add(image2);

        //关闭文档
        document.close();
        //关闭书写器
        writer.close();
    }

    /**
     * @description 给PDF文件设置文件属性
     * @param filePath String
     * @return void
     * @author lt
     * @date 2023/6/13 10:42
     */
    private static void createPdf2(String filePath) throws FileNotFoundException, DocumentException {
        //创建文件
        Document document = new Document();
        //建立一个书写器
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
        //打开文件
        document.open();
        //添加内容
        document.add(new Paragraph("Some content here"));

        /**
         * 设定文档属性
         *
         * ，可以设定文档的标题、主题、作者、关键字、装订方式、创建者、生产者、创建日期等属性，调用的方法分别如下, 其中方法addHeader对于PDF文档无效，addHeader仅对html文档有效，用于添加文档的头信息。
         * 　　public boolean addTitle(String title)
         * 　　public boolean addSubject(String subject)
         * 　　public boolean addKeywords(String keywords)
         * 　　public boolean addAuthor(String author)
         * 　　public boolean addCreator(String creator)
         * 　　public boolean addProducer()
         * 　　public boolean addCreationDate()
         * 　　public boolean addHeader(String name, String content)
         */

        //设置属性
        //标题
        document.addTitle("标题");
        //主题
        document.addSubject("主题");
        //关键字
        document.addKeywords("关键字");
        //作者
        document.addAuthor("刘廷");
        //创建人
        document.addCreator("刘廷");
        //创建时间
        document.addCreationDate();

        //关闭文档
        document.close();
        //关闭书写器
        writer.close();
    }

    /**
     * @description 创建一个简单的pdf文件
     * @param
     * @return void
     * @author lt
     * @date 2023/6/13 10:41
     */
    private static void createPdf1(String filePath) throws DocumentException, FileNotFoundException {
        //用iText生成PDF文档需要5个步骤：
//        ①建立Document对象的实例。
        Document document = new Document();
//        ②建立一个书写器(Writer) 与document对象关联，通过书写器(Writer) 可以将文档写入到磁盘中。
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
//        ③打开文档。
        document.open();
//        ④向文档中添加内容。
        document.add(new Paragraph("Hello World"));
//        ⑤关闭文档。
        document.close();
    }


}
