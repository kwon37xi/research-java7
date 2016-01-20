package ch05;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

/**
 * {@link Path#relativize(Path)}를 사용하면 쉽게 복사 구현이 가능하다.
 */
public class CopyDirectory extends SimpleFileVisitor<Path> {
    private Path source;
    private Path target;

    public CopyDirectory(Path source, Path target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        Path targetDirectory = target.resolve(source.relativize(dir));

        try {
            System.out.println("Copying " + source.relativize(dir));
            Files.copy(dir, targetDirectory);
        } catch (FileAlreadyExistsException e) {
            // 이미 존재하는 경로가 디렉토리가 아니면 오류, 디렉토리이면 계속 복사 진행.
            if (!Files.isDirectory(targetDirectory)) {
                throw e;
            }
        }

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        System.out.println("Copying " + source.relativize(file));
        Files.copy(file, target.resolve(source.relativize(file)));
        return FileVisitResult.CONTINUE;
    }

    public static void main(String[] args) throws IOException {
        Path source = Paths.get("/home/kwon37xi/projects/research-java7");
        Path target = Paths.get("/tmp/test");

        Files.walkFileTree(source, EnumSet.of(FileVisitOption.FOLLOW_LINKS), Integer.MAX_VALUE, new CopyDirectory(source, target));
    }

}
