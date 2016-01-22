package ch05;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UsageDirectoryStreamFilter {
    public static void main(String[] args) {
        DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(Path entry) throws IOException {
                return Files.isHidden(entry);
            }
        };

        Path directory = Paths.get("/home/kwon37xi");

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directory, filter)) {
            for (Path file : directoryStream) {
                System.out.println(file.getFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
