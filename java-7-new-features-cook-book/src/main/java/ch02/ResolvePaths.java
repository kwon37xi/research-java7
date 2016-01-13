package ch02;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ResolvePaths {
    public static void main(String[] args) {
        Path rootPath = Paths.get("/tmp/docs");
        Path partialPath = Paths.get("stauts.txt");
        Path resolvedPath = rootPath.resolve(partialPath);
//        Path resolvedPath = rootPath.resolve("status.txt");
//        Path resolvedPath = partialPath.resolve(partialPath);
        System.out.println("rootPath : " + rootPath);
        System.out.println("partialPath: " + partialPath);
        System.out.println("resolvedPath: " + resolvedPath); // /tmp/docs/stauts.txt
        System.out.println("Resolved Absolute Path: " + resolvedPath.toAbsolutePath());
    }
}
