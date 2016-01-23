package ch06;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ReadAllLines {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/tmp/docs/users.txt");
        // Files.readAllLines() 는 파일 내용을 줄 단위 리스트로 리턴한다.
        // EOL 문자는 빼고 리턴한다.
        List<String> contents = Files.readAllLines(path, Charset.defaultCharset());
        for (String line : contents) {
            System.out.printf("- %s%n", line);
        }
    }
}
