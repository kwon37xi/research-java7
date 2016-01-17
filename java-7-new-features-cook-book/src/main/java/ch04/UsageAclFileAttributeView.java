package ch04;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.*;
import java.util.List;
import java.util.Set;

public class UsageAclFileAttributeView {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/tmp/docs/users.txt");

        AclFileAttributeView view = Files.getFileAttributeView(path, AclFileAttributeView.class);
        System.out.println("view : " + view);
        List<AclEntry> aclEntryList = view.getAcl();

        displayAclEntries(aclEntryList);

        UserPrincipalLookupService lookupService = FileSystems.getDefault().getUserPrincipalLookupService();
        final UserPrincipal userPrincipal = lookupService.lookupPrincipalByName("users");

        AclEntry.Builder builder = AclEntry.newBuilder();
        builder.setType(AclEntryType.ALLOW);
        builder.setPrincipal(userPrincipal);
        builder.setPermissions(AclEntryPermission.WRITE_ACL, AclEntryPermission.DELETE);

        AclEntry entry = builder.build();
        aclEntryList.add(0, entry);
        view.setAcl(aclEntryList);
    }

    private static void displayAclEntries(List<AclEntry> aclEntryList) {
        System.out.println("ACL Entry List size: " + aclEntryList.size());

        for (AclEntry entry : aclEntryList) {
            System.out.println("User Principal Name: " + entry.principal().getName());
            System.out.println("ACL Entry Type: " + entry.type());
            displayEntryFlags(entry.flags());
            displayPermissions(entry.permissions());
        }
    }

    private static void displayEntryFlags(Set<AclEntryFlag> flags) {
        if (flags.isEmpty()) {
            System.out.println("NO ACL Entry Flags present");
            return;
        }

        System.out.println("ACL Entry Flags");
        for (AclEntryFlag flag : flags) {
            System.out.println(flag.name() + " ");
        }
        System.out.println();
    }

    private static void displayPermissions(Set<AclEntryPermission> permissions) {
        if (permissions.isEmpty()) {
            System.out.println("No permissions present");
        }

        System.out.println("Permissions");
        for (AclEntryPermission permission : permissions) {
            System.out.println(permission.name() + " ");
        }
        System.out.println();
    }
}
