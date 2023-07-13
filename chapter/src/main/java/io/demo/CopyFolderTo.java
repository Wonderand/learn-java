package io.demo;

import java.io.*;

public class CopyFolderTo {

    public static void main(String[] args) {
        // 指定文件夹路径
        String folderPath = "D:\\jad\\test\\";

        // 遍历文件夹下的所有文件
        for (String filename : listFileNames(folderPath)) {
            // 如果是以.txt结尾的文件
            if (filename.endsWith(".jad")) {
                // 读取文件内容
                String content = null;
                try {
                    content = readFile(folderPath + "/" + filename);
                    File outputFile = new File(folderPath + "/" + filename);
                    outputFile.createNewFile();
                    writeFile(outputFile, content);
                    // 修改文件后缀名
                    String newFilename = filename.replace(".jad", ".java");
                    outputFile.renameTo(new File(folderPath + "/" + newFilename));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                // 将文件内容追加到新文件中
                //writeFile();

            }
        }
    }

    /**
     * 读取文件内容
     * @param filePath 文件路径
     * @return 文件内容
     * @throws IOException 读取文件失败时抛出
     */
    public static String readFile(String filePath) throws IOException {
        FileInputStream inputStream = new FileInputStream(filePath);
        byte[] buffer = new byte[1024];
        int length = inputStream.read(buffer);
        inputStream.close();
        return new String(buffer, "UTF-8");
    }

    /**
     * 将文件内容追加到新文件中
     * @param outputFile 输出文件路径
     * @param content 文件内容
     * @throws IOException 将文件内容写入文件失败时抛出
     */
    public static void writeFile(File outputFile, String content) throws IOException {
        OutputStream outputStream = new FileOutputStream(outputFile);
        outputStream.write(content.getBytes());
        outputStream.close();
    }
    public static String[] listFileNames(String folderPath) {
        File[] files = new File(folderPath).listFiles();
        String[] fileNames = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            fileNames[i] = files[i].getName();
        }
        return fileNames;
    }
}