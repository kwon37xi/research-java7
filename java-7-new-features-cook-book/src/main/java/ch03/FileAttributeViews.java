package ch03;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.Set;

public class FileAttributeViews {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/tmp/docs/users.txt");
        FileSystem fileSystem = path.getFileSystem();
        Set<String> supportedViews = fileSystem.supportedFileAttributeViews();
        for (String supportedView : supportedViews) {
            System.out.println(supportedView);
        }

        FileStore fileStore = Files.getFileStore(path);

        System.out.println("FileAttributeView supported: " + fileStore.supportsFileAttributeView(FileAttributeView.class));
        System.out.println("BasicFileAttributeView supported: " + fileStore.supportsFileAttributeView(BasicFileAttributeView.class));
        System.out.println("FileOwnerAttributeView supported: " + fileStore.supportsFileAttributeView(FileOwnerAttributeView.class));
        System.out.println("AclFileAttributeView supported: " + fileStore.supportsFileAttributeView(AclFileAttributeView.class));
        System.out.println("PosixFileAttributeView supported: " + fileStore.supportsFileAttributeView(PosixFileAttributeView.class));
        System.out.println("UserDefinedFileAttributeView supported: " + fileStore.supportsFileAttributeView(UserDefinedFileAttributeView.class));
        System.out.println("DosFileAttributeView supported: " + fileStore.supportsFileAttributeView(DosFileAttributeView.class));

        System.out.println("FileAttributeView supported: " + fileStore.supportsFileAttributeView("file"));
        System.out.println("BasicFileAttributeView supported: " + fileStore.supportsFileAttributeView("basic"));
        System.out.println("FileOwnerAttributeView supported: " + fileStore.supportsFileAttributeView("owner"));
        System.out.println("AclFileAttributeView supported: " + fileStore.supportsFileAttributeView("acl"));
        System.out.println("PosixDefinedFileAttributeView supported: " + fileStore.supportsFileAttributeView("posix"));
        System.out.println("UserDefinedFileAttributeView supported: " + fileStore.supportsFileAttributeView("user"));
        System.out.println("DosFileAttributeView supported: " + fileStore.supportsFileAttributeView("dos"));

    }
}
