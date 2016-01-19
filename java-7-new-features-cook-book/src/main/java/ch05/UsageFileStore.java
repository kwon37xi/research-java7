package ch05;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.text.NumberFormat;

public class UsageFileStore {
    public static final long kiloByte = 1024;

    public static void main(String[] args) throws IOException {
        String format = "%-16s %-40s %-18s %-8s %12s %12s %12s%n";

        System.out.printf(format, "Name", "Filesystem", "Type", "Readonly", "Size(KB)", "Used(KB)", "Available(KB)");
        FileSystem fileSystem = FileSystems.getDefault();

        for (FileStore fileStore : fileSystem.getFileStores()) {
            long totalSpace = fileStore.getTotalSpace() / kiloByte;
            long usedSpace = (fileStore.getTotalSpace() - fileStore.getUnallocatedSpace()) / kiloByte;
            long usableSpace = fileStore.getUsableSpace() / kiloByte;
            String name = fileStore.name(); // device name - proc, /dev/sda1, ...
            String type = fileStore.type(); // partition type - ext4, ntfs, fat32, ...
            boolean readOnly = fileStore.isReadOnly();

            NumberFormat numberFormat = NumberFormat.getInstance();
            System.out.printf(format, name, fileStore, type, readOnly, numberFormat.format(totalSpace),
                    numberFormat.format(usedSpace),
                    numberFormat.format(usableSpace));

        }
    }
}
