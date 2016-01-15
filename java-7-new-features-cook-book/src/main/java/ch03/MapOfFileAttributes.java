package ch03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class MapOfFileAttributes {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/tmp/docs/users.txt");
        final Map<String, Object> attributes = Files.readAttributes(path, "*");

        for (Map.Entry<String, Object> attribute : attributes.entrySet()) {
            System.out.println(attribute.getKey() + " : " + attribute.getValue());
        }
    }
}
