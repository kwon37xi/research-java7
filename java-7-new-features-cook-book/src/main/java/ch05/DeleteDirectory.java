package ch05;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * walkFileTree는 깊이 우선탐색을 하기 때문에 최초에 만나는 파일은 시작 Path로부터 가장 깊은 곳에 있는 자식 디렉토리의 파일이다.
 * 따라서 하나씩 삭제해서 위로 올라오드보면 시작 디렉토리까지 삭제할 수 있게 된다.
 */
public class DeleteDirectory extends SimpleFileVisitor<Path> {
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        System.out.println("Deleting " + file.getFileName());
        Files.delete(file);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        if (exc == null) {
            System.out.println("Deleting " + dir.getFileName());
            Files.delete(dir);
            return FileVisitResult.CONTINUE;
        }
        throw exc;
    }

    public static void main(String[] args) throws IOException {
        Files.walkFileTree(Paths.get("/tmp/test"), new DeleteDirectory());
    }
}
