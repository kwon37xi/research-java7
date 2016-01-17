package ch04;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.UserPrincipal;
import java.nio.file.attribute.UserPrincipalLookupService;

public class OwnerAttributes {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/tmp/docs/users.txt");

        FileOwnerAttributeView view = Files.getFileAttributeView(path, FileOwnerAttributeView.class);
        UserPrincipalLookupService lookupService = FileSystems.getDefault().getUserPrincipalLookupService();
        UserPrincipal userPrincipal = lookupService.lookupPrincipalByName("jennifer");

        view.setOwner(userPrincipal);
        System.out.println("Owner : " + view.getOwner().getName());
    }

}
