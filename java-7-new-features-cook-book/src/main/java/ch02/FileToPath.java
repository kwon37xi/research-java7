package ch02;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileToPath {
    public static void main(String[] args) throws URISyntaxException {
        Path path = Paths.get(new URI("file:///tmp/docs/status.txt"));
        File file = new File("/tmp/docs/status.txt");
        Path toPath = file.toPath();
        System.out.println(toPath.equals(path)); // true
    }
}
