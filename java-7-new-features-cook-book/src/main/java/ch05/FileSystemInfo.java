package ch05;

import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.spi.FileSystemProvider;

public class FileSystemInfo {
    public static void main(String[] args) {
        FileSystem fileSystem = FileSystems.getDefault();
        FileSystemProvider provider = fileSystem.provider();

        System.out.println("Provider: " + provider.toString()); // sun.nio.fs.LinuxFileSystemProvider
        System.out.println("Open: " + fileSystem.isOpen());
        System.out.println("Read Only: " + fileSystem.isReadOnly());

        Iterable<Path> rootDirectories = fileSystem.getRootDirectories();
        System.out.println();
        System.out.println("Root directories"); // /, 윈도우의 경우 C:\, D:\, ... 형태로 나옴.

        for (Path rootDirectory : rootDirectories) {
            System.out.println(rootDirectory);
        }

        final Iterable<FileStore> fileStores = fileSystem.getFileStores();
        System.out.println();
        System.out.println("File Stores");
        for (FileStore fileStore : fileStores) {
            System.out.println(fileStore.name());
        }
    }
}
