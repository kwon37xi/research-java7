package ch04;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Set;

public class UsagePosixAttributes {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/tmp/docs/users.txt");
        FileSystem fileSystem = FileSystems.getDefault();
        PosixFileAttributeView view = Files.getFileAttributeView(path, PosixFileAttributeView.class);

        PosixFileAttributes attributes = view.readAttributes();
        Set<PosixFilePermission> permissions = attributes.permissions();
        listPermissions(permissions);

        permissions.add(PosixFilePermission.OTHERS_WRITE);
//        Files.setAttribute(path, "posix:permission", PosixFilePermission.GROUP_WRITE); // 작동안함.
        view.setPermissions(permissions);

        System.out.println();
        listPermissions(permissions);
    }

    private static void listPermissions(Set<PosixFilePermission> permissions) {
        System.out.println("Permissions: ");

        for (PosixFilePermission permission : permissions) {
            System.out.println(permission.name() + " ");
        }
        System.out.println();
    }
}
