package ch02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ComparePaths {
    public static void main(String[] args) {
        Path path1 = Paths.get("/tmp/docs/users.txt");
        Path path2 = Paths.get("/tmp/docs/users.txt");
        Path path3 = Paths.get("/tmp/music/Future Setting A.mp3");

        // 경로에 대한 비교이지 파일 컨텐츠에 대한 비교가 아니다.
        // equals, compareTo 는 서로 다른파일시스템의 경로값은 비교하지 못함. 하지만 파일의 존재는 무관함.
        // isSameFile은 파일에 대한 접근을 한다.
        testEquals(path1, path2);
        testEquals(path1, path3);

        testCompareTo(path1, path2);
        testCompareTo(path1, path3);

        testSameFile(path1, path2);
        testSameFile(path1, path3);
    }

    private static void testEquals(Path path1, Path path2) {
        if (path1.equals(path2)) {
            System.out.printf("%s and %s are equal%n", path1, path2);
        } else {
            System.out.printf("%s and %s are NOT equal%n", path1, path2);
        }
    }

    public static void testCompareTo(Path path1, Path path2) {
        if (path1.compareTo(path2) == 0) {
            System.out.printf("%s and %s are identical%n", path1, path2);
        } else {
            System.out.printf("%s and %s are NOT identical%n", path1, path2);
        }
    }

    public static void testSameFile(Path path1, Path path2) {
        try {
            if (Files.isSameFile(path1, path2)) {
                System.out.printf("%s and %s are the same file%n", path1, path2);
            } else {
                System.out.printf("%s and %s are NOT the same file%n", path1, path2);
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
