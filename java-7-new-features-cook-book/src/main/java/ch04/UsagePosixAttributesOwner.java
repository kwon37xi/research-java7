package ch04;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.Set;

/**
 * Requires root privileges.
 */
public class UsagePosixAttributesOwner {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/tmp/docs/users.txt");
        FileSystem fileSystem = path.getFileSystem();
        PosixFileAttributeView view = Files.getFileAttributeView(path, PosixFileAttributeView.class);

        PosixFileAttributes attributes = view.readAttributes();
        final Set<PosixFilePermission> permissions = attributes.permissions();

        System.out.println("Old Group: " + attributes.group().getName());
        System.out.println("Old Owner: " + attributes.owner().getName());
        System.out.println();

        final UserPrincipalLookupService userPrincipalLookupService = FileSystems.getDefault().getUserPrincipalLookupService();
        final UserPrincipal userPrincipal = userPrincipalLookupService.lookupPrincipalByName("root");
        final GroupPrincipal groupPrincipal = userPrincipalLookupService.lookupPrincipalByGroupName("root");

        view.setOwner(userPrincipal);
        view.setGroup(groupPrincipal);

        attributes = view.readAttributes();

        System.out.println("New Group: " + attributes.group().getName()); // root
        System.out.println("New Owner: " + attributes.owner().getName()); // root
    }
}
