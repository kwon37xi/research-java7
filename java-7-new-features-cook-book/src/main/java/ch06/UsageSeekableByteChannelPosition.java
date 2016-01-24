package ch06;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class UsageSeekableByteChannelPosition {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/tmp/docs/users.txt");

        final String newLine = System.lineSeparator();

        try (SeekableByteChannel sbc = Files.newByteChannel(path, StandardOpenOption.WRITE)) {
            ByteBuffer buffer;
            long position = sbc.size();
            sbc.position(position);
            System.out.println("Position: " + sbc.position());

            buffer = ByteBuffer.wrap((newLine + "Paul").getBytes());
            sbc.write(buffer);
            System.out.println("Position: " + sbc.position());
            buffer = ByteBuffer.wrap((newLine + "Carol").getBytes());
            sbc.write(buffer);
            System.out.println("Position: " + sbc.position());
            buffer = ByteBuffer.wrap((newLine + "Fred").getBytes());
            sbc.write(buffer);
            System.out.println("Position: " + sbc.position());
        }
    }
}
