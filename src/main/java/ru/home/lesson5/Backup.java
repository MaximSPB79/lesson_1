package ru.home.lesson5;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Backup {

    private static String path;

    private static String nameDir;

    public static void copy(String path, String nameDir){

       File srcFile = new File(path);

       File destDir = new File(nameDir);

       try {
           FileUtils.copyFileToDirectory(srcFile, destDir);
       } catch (IOException ex) {
           throw new RuntimeException(ex);
       }


       System.out.println("Файл успешно скопирован в каталог назначения " + destDir);

   }

}
