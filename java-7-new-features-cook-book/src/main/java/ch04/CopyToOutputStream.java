package ch04;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class CopyToOutputStream {
    public static void main(String[] args) throws IOException {
        Path sourceFile = FileSystems.getDefault().getPath("/tmp/docs/java7website.html");

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Files.copy(sourceFile, outputStream);
            byte[] arr = outputStream.toByteArray();
            System.out.println("The contents of " + sourceFile.getFileName());
            System.out.println(new String(arr));
            System.out.println();
        }
    }
}
