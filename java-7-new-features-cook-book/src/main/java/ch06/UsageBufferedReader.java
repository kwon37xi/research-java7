package ch06;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UsageBufferedReader {
    public static void main(String[] args) {
        Path path = Paths.get("/tmp/docs/users.txt");
        Charset charset = Charset.forName("ISO-8859-1");

        try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.printf(" - %s%n", line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
