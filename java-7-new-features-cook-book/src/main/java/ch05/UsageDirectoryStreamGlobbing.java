package ch05;

import java.io.IOException;
import java.nio.file.*;

/**
 * @see FileSystem#getPathMatcher(java.lang.String)
 */
public class UsageDirectoryStreamGlobbing {
    public static void main(String[] args) {
        Path directory = Paths.get("/home/kwon37xi/projects");

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directory, "research-*")) {
            for (Path file : directoryStream) {
                System.out.println(file.getFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
