package ch06;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SecureDirectoryStream;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Set;

public class UsageSecureDirectoryStream {
    public static void main(String[] args) throws Exception {
        Path path = Paths.get("/tmp/docs");

        SecureDirectoryStream<Path> sds = (SecureDirectoryStream) Files.newDirectoryStream(path);
        PosixFileAttributeView view = sds.getFileAttributeView(PosixFileAttributeView.class);
        PosixFileAttributes attributes = view.readAttributes();
        Set<PosixFilePermission> permissions = attributes.permissions();

        for (PosixFilePermission permission : permissions) {
            System.out.println(permission.toString() + ' ');
        }
        System.out.println();
    }
}
