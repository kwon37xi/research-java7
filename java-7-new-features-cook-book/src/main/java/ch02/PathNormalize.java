package ch02;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathNormalize {
    public static void main(String[] args) {
        Path path = Paths.get("/tmp/docs/../music/Space Machine A.mp3");
        System.out.println("Absolute path : " + path.toAbsolutePath());
        System.out.println("URI : " + path.toUri());
        System.out.println("Normalized Path: " + path.normalize());
        System.out.println("Normalized URI: " + path.normalize().toUri());
        System.out.println();

        path = Paths.get("/tmp/./music/ Robot Brain A.mp3");
        System.out.println("Absolute path: " + path.toAbsolutePath());
        System.out.println("URI : " + path.toUri());
        System.out.println("Normalized Path: " + path.normalize());
        System.out.println("Normalized URI: " + path.normalize().toUri());

        try {
            Path nonExistPath = Paths.get("/tmp/docs/../music/NonExistentFile.mp3");
            System.out.println("Absolute path: " + nonExistPath.toAbsolutePath());
            System.out.println("Real path : " + path.toRealPath());
        } catch (IOException e) {
            System.out.println("The file does not exist!");
            e.printStackTrace(System.out);
        }
    }
}
