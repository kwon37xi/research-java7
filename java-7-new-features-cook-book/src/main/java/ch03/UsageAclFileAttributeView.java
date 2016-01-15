package ch03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.AclEntry;
import java.nio.file.attribute.AclEntryFlag;
import java.nio.file.attribute.AclEntryPermission;
import java.nio.file.attribute.AclFileAttributeView;
import java.util.List;
import java.util.Set;

public class UsageAclFileAttributeView {
    // Windows 계열에서만 작동하는 것으로 보인다.
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/media/data/Documents/desktop.ini");

        AclFileAttributeView view = Files.getFileAttributeView(path, AclFileAttributeView.class);
        final List<AclEntry> aclEntryList = view.getAcl();
        for (AclEntry aclEntry : aclEntryList) {
            System.out.println("User Principal Name: "+ aclEntry.principal().getName());
            System.out.println("ACL Entry Type: " + aclEntry.type());
            displayEntryFlags(aclEntry.flags());
            displayPermissions(aclEntry.permissions());
            System.out.println();
        }
    }

    private static void displayEntryFlags(Set<AclEntryFlag> flags) {
        if (flags.isEmpty()) {
            System.out.println("No ACL Entry Flags present");
        } else {
            System.out.println("ACL Entry Flags");
            for (AclEntryFlag flag : flags) {
                System.out.println(flag.name() + " ");
            }
            System.out.println();
        }
    }


    private static void displayPermissions(Set<AclEntryPermission> permissions) {
        if (permissions.isEmpty()) {
            System.out.println("No Permissions present");
        } else {
            System.out.println("Permissions");
            for (AclEntryPermission permission : permissions) {
                System.out.println(permission.name() + " ");
            }
            System.out.println();
        }
    }
}
