package ch04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UsageSymbolicLink {
    public static void main(String[] args) throws IOException {
        Path targetFile = Paths.get("/tmp/docs/users.txt");
        Path linkFile = Paths.get("/tmp/docs/userslink.txt");

        Files.createSymbolicLink(linkFile, targetFile);
    }
}
