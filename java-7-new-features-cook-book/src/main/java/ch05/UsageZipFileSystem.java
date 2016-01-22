package ch05;

import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

// zip 파일을 그 자체로 독립적인 하나의 FileSystem 으로써 사용할 수 있다.
public class UsageZipFileSystem {
    public static void main(String[] args) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("create", "true"); // 파일을 생성한다.

        URI zipFile = URI.create("jar:file:/tmp/test.zip");

        try (FileSystem zipFileSystem = FileSystems.newFileSystem(zipFile, attributes)) {
            Path path = zipFileSystem.getPath("docs");
            Files.createDirectory(path); // zip file 안에 디렉토리를 생성함.

            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(zipFileSystem.getPath("/"))) {
                for (Path file : directoryStream) {
                    System.out.println(file.getFileName());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
