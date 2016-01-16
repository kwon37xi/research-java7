package ch04;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateFileAndDirectory {
    public static void main(String[] args) throws IOException {
        Path testDirectoryPath = Paths.get("/tmp/docs/test");
        Path testDirectory = Files.createDirectories(testDirectoryPath);
        System.out.println("Directory created successfully!");
        Path newFilePath = FileSystems.getDefault().getPath("/tmp/docs/newFile.txt");
        Path testFile = Files.createFile(newFilePath);
        System.out.println("File created successfully!");

    }
}
