package ch06;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class UsageInputOutputStream {
    public static void main(String[] args) throws IOException {
        Path file = Paths.get("/tmp/docs/users.txt");
        Path newFile = Paths.get("/tmp/docs/newUsers.txt");

        try (InputStream in = Files.newInputStream(file);
             OutputStream out = Files.newOutputStream(newFile, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            int data = in.read();
            while (data != -1) {
                out.write(data);
                data = in.read();
            }
        }
    }
}
