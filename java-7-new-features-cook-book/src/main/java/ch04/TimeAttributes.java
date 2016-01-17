package ch04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Calendar;

public class TimeAttributes {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/tmp/docs/users.txt");
        BasicFileAttributeView view = Files.getFileAttributeView(path, BasicFileAttributeView.class);

        FileTime lastModifiedTime;
        FileTime lastAccessTime;
        FileTime createTime;

        BasicFileAttributes attributes = view.readAttributes();
        lastModifiedTime = attributes.lastModifiedTime();
        createTime = attributes.creationTime();

        long currentTime = Calendar.getInstance().getTimeInMillis();
        lastAccessTime = FileTime.fromMillis(currentTime);

        view.setTimes(lastModifiedTime, lastAccessTime, createTime);
        System.out.println(attributes.lastAccessTime());
    }
}
