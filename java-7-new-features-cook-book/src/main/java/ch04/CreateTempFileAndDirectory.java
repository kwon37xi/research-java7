package ch04;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class CreateTempFileAndDirectory {
    public static void main(String[] args) throws IOException {
        Path rootDirectory = FileSystems.getDefault().getPath("/tmp/docs");
        Path tempDirectory = Files.createTempDirectory(rootDirectory, "");
        System.out.println("Temporary directory created successfully!");
        String direPath = tempDirectory.toString();
        System.out.println(direPath);

        Path tempFile = Files.createTempFile(tempDirectory, "", "");
        System.out.println("Temporary file created successfully!");
        String filePath = tempFile.toString();
        System.out.println(filePath);
    }
}
