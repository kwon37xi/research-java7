package ch02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConvertPathTypes {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("build.gradle");
        System.out.println("URI path: " + path.toUri());
        System.out.println("Absolute path: " + path.toAbsolutePath());
        System.out.println("File exists : " + Files.exists(path));
        System.out.println("Real path: " + path.toRealPath(LinkOption.NOFOLLOW_LINKS));

    }
}
