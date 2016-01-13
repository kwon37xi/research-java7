package ch02;

import java.nio.file.Path;
import java.nio.file.Paths;

public class RelativizePaths {
    public static void main(String[] args) {
        Path firstPath;
        Path secondPath;

        firstPath = Paths.get("music/Future Settings A.mp3");
        secondPath = Paths.get("docs");

        System.out.println("From firstPath to secondPath: " + firstPath.relativize(secondPath)); // ../../docs
        System.out.println("From secondPath to firstPath: " + secondPath.relativize(firstPath)); // ../music/Future Settings A.mp3
        System.out.println();

        firstPath = Paths.get("music/Future Setting A.mp3");
        secondPath = Paths.get("music");
        System.out.println("From firstPath to secondPath: " + firstPath.relativize(secondPath)); // ..
        System.out.println("From secondPath to firstPath: " + secondPath.relativize(firstPath)); // Future Setting A.mp3
        System.out.println();

        firstPath = Paths.get("music/Future Setting A.mp3");
        secondPath = Paths.get("docs/users.txt");
        System.out.println("From firstPath to secondPath: " + firstPath.relativize(secondPath)); // ../../docs/users.txt
        System.out.println("From secondPath to firstPath: " + secondPath.relativize(firstPath)); // ../../music/Future Setting A.mp3
        System.out.println();

        firstPath = Paths.get("music/Future Setting A.mp3");
        secondPath = Paths.get("music/Future Setting A.mp3");
        System.out.println("From firstPath to secondPath: " + firstPath.relativize(secondPath)); // empty string (not .)
        System.out.println("From secondPath to firstPath: " + secondPath.relativize(firstPath)); // empty string (not .)
        System.out.println();
    }
}
