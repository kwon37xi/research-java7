package ch05;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class UsageSimpleFileVisitor {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/home/kwon37xi/projects");
        ListFiles listFiles = new ListFiles();
        Files.walkFileTree(path, listFiles);
    }

    private static class ListFiles extends SimpleFileVisitor<Path> {
        private final int indentionAmount = 3;
        private int indentionLevel;

        public ListFiles() {
            indentionLevel = 0;
        }

        private void indent() {
            for(int i = 0; i < indentionLevel; i++) {
                System.out.print(' ');
            }
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            indent();
            System.out.println("Visiting file:" + file.getFileName());
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            indentionLevel -= indentionAmount;
            indent();
            System.out.println("Finished with the directory: " + dir.getFileName());
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            System.out.println("About to traverse the directory: " + dir.getFileName());
            indentionLevel += indentionAmount;
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            System.out.println("A file traversal error occured");
            return super.visitFileFailed(file, exc);
        }
    }
}
