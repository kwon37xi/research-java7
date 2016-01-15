package ch03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Set;

public class UsagePosixFileAttributeView {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/tmp/docs/users.txt");
        PosixFileAttributeView view = Files.getFileAttributeView(path, PosixFileAttributeView.class);
        // view.setPermissions();, view.setGroup();, view.setOwner();
        PosixFileAttributes attributes = view.readAttributes();
        System.out.println("Group: " + attributes.group());
        System.out.println("Owner: " + attributes.owner());

        final Set<PosixFilePermission> permissions = attributes.permissions();
        for (PosixFilePermission permission : permissions) {
            System.out.print(permission.name() + " ");
        }
    }
}
