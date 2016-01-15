package ch03;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserDefinedFileAttributeView;

public class UsageUserDefinedFileAttributeView {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/tmp/docs/users.txt");

        UserDefinedFileAttributeView view = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
        view.write("publishable", Charset.defaultCharset().encode("true"));
        System.out.println("Publishable set");

        String name = "publishable";
        ByteBuffer buffer = ByteBuffer.allocate(view.size(name)); // view.size - size of the attribute value
        view.read(name, buffer);
        buffer.flip();
        String value = Charset.defaultCharset().decode(buffer).toString();
        System.out.println(value);

        //view.delete(); : delete attribute

    }
}
