package io.base64_img;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class test {

    public static void main(String[] args) {
        String s = base64Img("D:\\yitizi\\yitizi\\sword\\sworda\\sa00001\\001.jpg");
        System.out.println(s);
//        try (FileOutputStream fileOutputStream = new FileOutputStream("E:\\jdk17\\test\\chapter\\src\\main\\resources\\1.txt")){
//            //头部加入data:image/png;base64,
//            //FileUtils.writeStringToFile(new File("E:\\jdk17\\test\\chapter\\src\\main\\resources\\1.jpg"),s,"UTF-8");
//
//            //s = "data:image/png;base64," + s;
//            fileOutputStream.write(s.getBytes(),0,s.getBytes().length);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        String file ="E:\\jdk17\\test\\chapter\\src\\main\\resources\\1.txt";
//        base64ToImg(file,"E:\\jdk17\\test\\chapter\\src\\main\\resources\\2.jpg");
    }

    //读取文件夹下的所有jpg文件的路径
    @Test
    public void test1() throws IOException {
        //创建字符缓冲输出流对象
        BufferedWriter bw = new BufferedWriter(new FileWriter("E:\\jdk17\\test\\chapter\\src\\main\\file\\path.txt", true));
//        //写数据
//        bw.write("123");
//        bw.newLine();
//        bw.write("456");
//        //释放资源
//        bw.close();
        File file = new File("D:\\yitizi\\yitizi\\sword");
        ArrayList<String> jpgFiles = new ArrayList<>();
        getJpgFiles(file.getAbsolutePath(), jpgFiles);
        for (int i = 0; i < jpgFiles.size(); i++) {
//            System.out.println(jpgFiles.get(i));
            bw.write(jpgFiles.get(i));
            bw.newLine();
        }
        bw.close();
    }

    /**
     * 测试jdbc驱动
     *
     * @param
     * @param
     */
    @Test
    public void test2() {
        long start = System.currentTimeMillis();
        try {
            // 1. 加载JDBC驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. 创建数据库连接
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/yitizi", "root", "123");
            // 3. 关闭自动提交
            conn.setAutoCommit(false);
            // 4. 创建PreparedStatement对象
            String sql = "INSERT INTO ycan_yitizi (base64,key_index,path,create_time) VALUES ( ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            File file = new File("D:\\yitizi\\yitizi\\sword");
            // 获取文件夹下的所有jpg文件路径
            ArrayList<String> jpgFiles = new ArrayList<>();
            getJpgFiles(file.getAbsolutePath(), jpgFiles);
            // 5. 执行批处理
            int batchSize = 1000;
            for (int i = 0; i < jpgFiles.size(); i++) {
                String base64 = base64Img(jpgFiles.get(i));
                String s = jpgFiles.get(i);
                pstmt.setString(1, base64);
                pstmt.setString(2, "Key" + i);
                pstmt.setString(3, s);
                pstmt.setString(4, LocalDateTime.now().toString());
                pstmt.addBatch();

                if (i % batchSize == 0) {
                    if (i == 0) {
                        continue;
                    }
                    System.out.println("插入第" + i + "条数据");
                    pstmt.executeBatch();
                    conn.commit();
                }
            }
            // 6. 处理剩余的数据
            pstmt.executeBatch();
            conn.commit();
            // 7. 关闭连接
            pstmt.close();
            conn.close();
            System.out.printf(">> 总计花费: %dms\n", System.currentTimeMillis() - start);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * base64toimage
     */
    @Test
    public void test3() {
        base64ToImg("E:\\jdk17\\test\\chapter\\src\\main\\resources\\1.txt", "E:\\jdk17\\test\\chapter\\src\\main\\resources\\3.jpg");
    }

    public static void getJpgFiles(String path, ArrayList<String> jpgFiles) {
        File folder = new File(path);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    getJpgFiles(file.getAbsolutePath(), jpgFiles);
                } else {
                    String fileName = file.getName();
                    if (fileName.endsWith(".suffix")) {
                        jpgFiles.add(file.getAbsolutePath());
                    }
                }
            }
        }
    }

    /**
     * 获取文件夹下的所有html文件
     * 读取到的html文件中的a标签的值
     */
    @Test
    public void test4() throws Exception {

        ArrayList<String> list = new ArrayList<>();
        BufferedWriter writer = new BufferedWriter(new FileWriter("E:\\jdk17\\test\\chapter\\src\\main\\file\\异体字cn.txt", true));
        BufferedWriter fontwriter = new BufferedWriter(new FileWriter("E:\\jdk17\\test\\chapter\\src\\main\\file\\手写字cn.txt", true));

        getFiles("D:\\yitizi\\yitizi\\yitic\\wc", list, ".htm");
        for (int i = 0; i < list.size(); i++) {
            File s = new File(list.get(i));
            Document doc = Jsoup.parse(s, "Big5", "");
            Elements links = doc.select("a[target=down]");
//            Elements links = doc.select("a[border=1]");
            for (Element link : links) {
                if (StringUtils.isBlank(link.text())) {
                    System.out.println(link.text() + s.getAbsolutePath());
                    writer.write(s.getAbsolutePath() +":"+ (i + 1));
                    writer.newLine();
                } else {
                    System.out.println(i + link.text());
                    fontwriter.write(link.text() + ":" + (i + 1)+":"+s.getAbsolutePath());
                    fontwriter.newLine();
                }
            }
        }
        writer.close();
        fontwriter.close();
    }


    public static void getFiles(String path, ArrayList<String> jpgFiles, String suffix) {
        File folder = new File(path);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    getFiles(file.getAbsolutePath(), jpgFiles, suffix);
                } else {
                    String fileName = file.getName();
                    if (fileName.endsWith(suffix)) {
                        jpgFiles.add(file.getAbsolutePath());
                    }
                }
            }
        }
    }

    /**
     * stream流读取文件
     *
     * @param
     * @return
     */
    @Test
    public void getFiles() throws IOException {
        long start = System.currentTimeMillis();
//        Path path = Paths.get("D:\\yitizi\\yitizi\\sword");
//        try (Stream<Path> walk = Files.walk(path)) {
//            List<String> result = walk.filter(Files::isRegularFile)
//                    .map(Path::toString)
//                    .filter(s -> s.endsWith(".jpg"))
//                    .collect(Collectors.toList());
////            result.forEach(System.out::println);
//            BufferedWriter writer = new BufferedWriter(new FileWriter("E:\\jdk17\\test\\chapter\\src\\main\\file\\path1.txt", true));
//            for (String s : result) {
//                writer.write(s);
//                writer.newLine();
//            }
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        ArrayList<String> list = new ArrayList<>();
        getJpgFiles("D:\\yitizi\\yitizi\\sword", list);
        BufferedWriter writer = new BufferedWriter(new FileWriter("E:\\jdk17\\test\\chapter\\src\\main\\file\\path1.txt", true));
        for (int i = 0; i < list.size(); i++) {
            writer.write(list.get(i));
            writer.newLine();
        }
        writer.close();
        System.out.printf(">> 总计花费: %dms\n", System.currentTimeMillis() - start);
    }

    //
    public static String base64Img(String filePath) {
        File file = new File(filePath);

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] bytes = new byte[(int) file.length()];
            fileInputStream.read(bytes);
            String img = Base64.getEncoder().encodeToString(bytes).toString();
            return img;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    //base64转图片
    public static void base64ToImg(String bast64filePath, String imgPath) {
        // 解密
        try {
            // 读取base64image文件
            String str = FileUtils.readFileToString(new File(bast64filePath), "UTF-8");
            // 去掉base64前缀 data:image/jpeg;base64,
            //String s = "data:image/jpeg;base64,";
            //String imgbase64 = str.substring(s.length(),str.length());
            //FileUtils.writeStringToFile(new File("E:\\jdk17\\test\\chapter\\src\\main\\resources\\2.txt"), "\n"+imgbase64, "UTF-8");

            // 解密，解密的结果是一个byte数组
            System.out.println(str);
            byte[] imgbytes = Base64.getDecoder().decode(str);
//            // 转换成图片字节
////            for (int i = 0; i < imgbytes.length; ++i) {
////                if (imgbytes[i] < 0) {
////                    imgbytes[i] += 256;
////                }
////            }
//            // 生成图片
            OutputStream out = new FileOutputStream(imgPath);
            out.write(imgbytes);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
