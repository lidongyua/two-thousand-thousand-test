package com.utils;

import com.Global;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 读写
 *
 * @author lidongy
 * @version 10.0
 * Created by lidongy on 2020/5/9
 */
public class FileIOUtils {

    /**
     * 相对路径读取
     */
    public static Properties readProperties(String path) throws Exception {
        return readPropertiesFile(pathJoin(Global.projectPath, path));
    }

    public static String readString(String path) throws Exception {
        return readStringFile(pathJoin(Global.projectPath, path));
    }


    /**
     * 绝对路径读取
     */
    public static Properties readPropertiesFile(String path) throws Exception {
        Properties properties = new Properties();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        properties.load(bufferedReader);
        return properties;
    }

    public static String readStringFile(String path) throws Exception {
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        FileInputStream inputStream = new FileInputStream(file);
        int length = inputStream.available();
        byte bytes[] = new byte[length];
        inputStream.read(bytes);
        inputStream.close();
        String str = new String(bytes, StandardCharsets.UTF_8);
        return str;
    }

    public static List<File> getAllFileBySuffix(File file, String suffix) throws Exception {
        File[] childFileList = file.listFiles();
        List<File> result = new ArrayList<>();
        for (File childFile : childFileList) {
            if (childFile.isDirectory()) {
                result.addAll(getAllFileBySuffix(childFile, suffix));
            } else if (childFile.getName().endsWith("." + suffix)) {
                result.add(childFile);
            }
        }
        return result;
    }

    public static String pathJoin(String path1, String path2) {
        if (path1.endsWith("/") || path1.endsWith("\\")) {
            return path1 + path2;
        }
        return path1 + "/" + path2;
    }

    public static void addContainsToFile(File file, File tempFile, int position, String contents) throws IOException {

        FileOutputStream outputStream = new FileOutputStream(tempFile);
        FileInputStream inputStream = new FileInputStream(tempFile);
        tempFile.deleteOnExit();
        RandomAccessFile rw = new RandomAccessFile(file, "rw");
        rw.seek(position);

        int tmp;
        while ((tmp = rw.read()) != -1) {
            outputStream.write(tmp);
        }
        rw.seek(position);
        rw.write(contents.getBytes());

        while ((tmp = inputStream.read()) != -1) {
            rw.write(tmp);
        }
        rw.close();
        outputStream.close();
        inputStream.close();
    }

    public static boolean isFileContentEqual(File oldFile, File newFile) {
        FileInputStream oldInStream = null;
        FileInputStream newInStream = null;
        try {
            oldInStream = new FileInputStream(oldFile);
            newInStream = new FileInputStream(newFile);

            int oldStreamLen = oldInStream.available();
            int newStreamLen = newInStream.available();
            //check the file size first.
            if (oldStreamLen > 0 && oldStreamLen == newStreamLen) {
                //read file data with a buffer.
                int cacheSize = 128;
                byte[] data1 = new byte[cacheSize];
                byte[] data2 = new byte[cacheSize];
                do {
                    int readSize = oldInStream.read(data1);
                    newInStream.read(data2);

                    for (int i = 0; i < cacheSize; i++) {
                        if (data1[i] != data2[i]) {
                            return false;
                        }
                    }
                    if (readSize == -1) {
                        break;
                    }
                } while (true);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //release the stream resource.
            try {
                if (oldInStream != null)
                    oldInStream.close();
                if (newInStream != null)
                    newInStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return false;
    }
}
