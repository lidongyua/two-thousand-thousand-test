package com.pitch;

import com.utils.FileIOUtils;

import java.io.File;
import java.util.List;

/**
 * @author lidongy
 * @version 10.0
 * Created by lidongy on 2020/5/9
 */
public class PitchPieFactory {
    public static void pitch(String projectPath) throws Exception {
        File root = new File(projectPath);
        List<File> fileList = FileIOUtils.getAllFileBySuffix(root, "java");
        for (File file : fileList) {

        }
    }

    public static void pitchPie2File(File file, String targetPosition, String pie) throws Exception {
        FileIOUtils.addContainsToFile(file, File.createTempFile("sss", ".temp"), 500, pie);
    }

    public static void main(String[] args) throws Exception {
        pitchPie2File(new File("d:/新建文本文档.txt"), "111", "");
    }
}
