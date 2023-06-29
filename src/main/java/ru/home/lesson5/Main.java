package ru.home.lesson5;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {

        File path = new File(new File(".").getCanonicalPath());
        File[] dir = path.listFiles();
        for (File file : Objects.requireNonNull(dir)) {
            if (!file.isDirectory()) {
                Backup.copy(file.getPath(), "backup");
            }
        }
    }
}
