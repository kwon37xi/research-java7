package ch02;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ResolveSibling {
    public static void main(String[] args) {
        Path rootPath = Paths.get("/home/music");

        Path resolvedPath = rootPath.resolve("tmp/Robot Brain A.mp3");
        System.out.println("rootPath: " + rootPath);
        System.out.println("resolvedPath: " + resolvedPath); // /home/music/tmp/Robot Brain A.mp3
        System.out.println();

        resolvedPath = rootPath.resolveSibling("tmp/Robot Brain A.mp3");
        System.out.println("rootPath: " + rootPath);
        System.out.println("resolvedPath: " + resolvedPath); // /home/tmp/Robot Brain A.mp3
    }
}
