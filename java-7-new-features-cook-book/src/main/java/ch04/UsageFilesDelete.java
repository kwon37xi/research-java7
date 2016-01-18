package ch04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UsageFilesDelete {
    public static void main(String[] args) throws IOException {
        Path sourceFile = Paths.get("/tmp/docs/users.txt");
        Files.delete(sourceFile);
    }
}
