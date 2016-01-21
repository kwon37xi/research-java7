package ch05;

import java.io.IOException;
import java.nio.file.*;

public class UsagePathMatcher {
    public static void main(String[] args) {
        Path directory = Paths.get("/usr/lib/jvm/java-8-oracle/bin");
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:java?");

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directory, "java*")) {
            for (Path file : directoryStream) {
                if (pathMatcher.matches(file.getFileName())) {
                    System.out.println(file.getFileName());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
