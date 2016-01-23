package ch06;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class UsageBufferedWriter {
    public static void main(String[] args) {
        String newName = "Vivian";
        Path file = Paths.get("/tmp/docs/users.txt");

        try(BufferedWriter writer = Files.newBufferedWriter(file, Charset.defaultCharset(), StandardOpenOption.APPEND)) {
            writer.newLine();
            writer.write(newName, 0, newName.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
