package ch02;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SymbolicLink {
    public static void main(String[] args) throws Exception {
        Path path1 = Paths.get("/tmp/docs/users.txt");
        Path path2 = Paths.get("/tmp/music/users.txt");

        System.out.println(Files.isSymbolicLink(path1)); // false
        System.out.println(Files.isSymbolicLink(path2)); // true

        Path path = Paths.get("/tmp/./music/users.txt");
        final Path normalized = path.normalize();
        System.out.println("Normalized: " + normalized); // /tmp/music/users.txt
        System.out.println("Absolute path: " + path.toAbsolutePath()); // /tmp/./music/users.txt
        System.out.println("URI : " + path.toUri()); // file:///tmp/./music/users.txt
        System.out.println("toRealPath (Do not follow links):" + path.toRealPath(LinkOption.NOFOLLOW_LINKS)); // /tmp/music/users.txt : link를 가리킴

        Path firstPath = Paths.get("/tmp/music/users.txt");
        Path secondPath = Paths.get("/docs/status.txt");
        System.out.println("From firstPath to secondPath: " + firstPath.relativize(secondPath)); // ../../../docs.status.txt
        System.out.println("From secondPath to firstPath: " + secondPath.relativize(firstPath)); // ../../tmp/music/users.txt
        System.out.println("exists (Do not follow links): " + Files.exists(firstPath, LinkOption.NOFOLLOW_LINKS)); // true
        System.out.println("exists : " + Files.exists(firstPath)); // link가 가리키는 파일이 존재하면 true 아니면 false
        System.out.println("notExists (Do not follow links): " + Files.notExists(firstPath, LinkOption.NOFOLLOW_LINKS)); // false
        System.out.println("notExists : " + Files.notExists(firstPath)); // false

    }
}
