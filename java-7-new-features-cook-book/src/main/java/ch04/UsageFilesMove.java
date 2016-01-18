package ch04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UsageFilesMove {
    public static void main(String[] args) throws IOException {
        Path sourceFile = Paths.get("/tmp/docs/users.txt");
        Path destinationFile = Paths.get("/tmp/music/users.txt");
//        Files.move(sourceFile, destinationFile);
        Files.move(destinationFile, destinationFile.resolveSibling(destinationFile.getFileName() + ".bak")); // rename to users.txt.bak
    }
}
