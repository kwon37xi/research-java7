package ch03;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilesGetAttribute {
    public static void main(String[] args) throws IOException {
        Path path = FileSystems.getDefault().getPath("/tmp/docs/users.txt");
        System.out.println(Files.getAttribute(path, "size"));
    }
}
