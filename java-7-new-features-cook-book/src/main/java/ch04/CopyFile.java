package ch04;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class CopyFile {
    public static void main(String[] args) throws IOException {
        Path newFile = FileSystems.getDefault().getPath("/tmp/docs/newFile.txt");
        Path copiedFile = FileSystems.getDefault().getPath("/tmp/docs/copiedFile.txt");

        Files.createFile(newFile);
        System.out.println("File created successfully!");
        Files.copy(newFile, copiedFile, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
        System.out.println("File copied successfully!");


    }
}
