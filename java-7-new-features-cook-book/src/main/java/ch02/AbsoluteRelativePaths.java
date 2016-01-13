package ch02;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AbsoluteRelativePaths {
    public static void main(String[] args) throws URISyntaxException {
        final String separator = FileSystems.getDefault().getSeparator();
        System.out.println("The separator is " + separator);

        Path path = Paths.get(new URI("file:///tmp/docs/status.txt"));
        System.out.println("subpath: " + path.subpath(0, 3));
        path = Paths.get("/tmp", "docs", "status.txt");
        System.out.println("Absolute path: " + path.toAbsolutePath());
        System.out.println("URI: " + path.toUri());
    }
}
