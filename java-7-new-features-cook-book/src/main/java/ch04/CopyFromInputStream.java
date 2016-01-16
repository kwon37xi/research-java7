package ch04;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class CopyFromInputStream {
    public static void main(String[] args) throws URISyntaxException, IOException {
        Path newFile = FileSystems.getDefault().getPath("/tmp/docs/java7website.html");
        URI uri = URI.create("https://jdk7.java.net/");
        try (InputStream inputStream = uri.toURL().openStream()) {
            Files.copy(inputStream, newFile);
            System.out.println("Site copied successfully.");
        }
    }
}
