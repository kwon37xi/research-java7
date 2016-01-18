package ch04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.*;
import java.util.Set;

public class UsagePosixFilePermissions {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/tmp/docs/users.txt");
        PosixFileAttributeView view = Files.getFileAttributeView(path, PosixFileAttributeView.class);

        PosixFileAttributes attributes = view.readAttributes();
        Set<PosixFilePermission> permissions = attributes.permissions();

        for (PosixFilePermission permission : permissions) {
            System.out.println(permission.toString() +  ' ');
        }

        System.out.println();

        final FileAttribute<Set<PosixFilePermission>> fileAttributes = PosixFilePermissions.asFileAttribute(permissions);
        final Set<PosixFilePermission> fileAttributeSet = fileAttributes.value();

        for (PosixFilePermission posixFilePermission : fileAttributeSet) {
            System.out.println(posixFilePermission.toString() + " ");
        }

        System.out.println();

        System.out.println(PosixFilePermissions.toString(permissions)); // rw-r--rw-
        permissions = PosixFilePermissions.fromString("rw-rw-r--");
        for (PosixFilePermission permission : permissions) {
            System.out.println(permission.toString() + ' ');
        }
        System.out.println();

    }
}
