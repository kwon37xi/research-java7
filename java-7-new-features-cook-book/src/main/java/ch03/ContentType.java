package ch03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ContentType {
    public static void main(String[] args) throws IOException {
        displayContentType("/tmp/docs/users.txt"); // text/plain
        displayContentType("/tmp/docs/test.pdf"); // application/pdf
        displayContentType("/tmp/docs/java"); // application/x-executable
    }

    private static void displayContentType(String pathText) throws IOException {
        Path path = Paths.get(pathText);
        String type = Files.probeContentType(path);
        System.out.println(type);
    }
}
