package ch06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ReadAllBytes {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/tmp/docs/users.txt");
        byte[] contents = Files.readAllBytes(path);

        System.out.println(new String(contents));

        // write to a new file
        Path newPath = Paths.get("/tmp/docs/newUsers.txt");
        byte[] newContents = "Chrispoher".getBytes();
        Files.write(newPath, contents, StandardOpenOption.CREATE);
        Files.write(newPath, newContents, StandardOpenOption.APPEND);
    }
}
