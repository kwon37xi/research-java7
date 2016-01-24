package ch06;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class UsageSeekableByteChannel {
    public static void main(String[] args) throws IOException {
        int bufferSize = 8;
        Path path = Paths.get("/tmp/docs/users.txt");

        try (SeekableByteChannel sbc = Files.newByteChannel(path, StandardOpenOption.READ)) {
            ByteBuffer buffer = ByteBuffer.allocate(bufferSize);

            sbc.position(4); // 2번째 줄로 위치 이동
            sbc.read(buffer);
            for (int i = 0; i < 5; i++) {
                System.out.print((char) buffer.get(i));
            }
            System.out.println();

            buffer.clear();
            sbc.position(0); // 다시 처음으로 돌아감.
            // read()는 현재 위치에서부터 버퍼가 다 차거나 파일의 끝에 도달할 때까지 읽는다.
            // 읽을게 없으면 -1을 리턴한다.
            sbc.read(buffer);
            for (int i = 0; i < 4; i++) {
                System.out.print((char) buffer.get(i));
            }
            System.out.println();

            System.out.println("Contents of File");
            sbc.position(0);
            buffer = ByteBuffer.allocate(bufferSize);
            String encoding = System.getProperty("file.encoding");
            int numberOfBytesRead = sbc.read(buffer);
            System.out.println("Number of bytes read: " + numberOfBytesRead);

            while (numberOfBytesRead > 0) {
                // flip()은 현재 buffer position을 limit으로 지정하고, buffer position을 0으로 만든다.
                // 즉, 현재까지 버퍼에 씌여진 내용만 decode하고자 할 때 사용하는 기능이다.
                buffer.flip();
                System.out.println("[" + Charset.forName(encoding).decode(buffer) + "]");
//                buffer.rewind(); // buffer position을 0으로 바꾼다. 즉, 처음부터 버퍼에 새로운 내용을 쓰고자 할 때사용한다.
                buffer.clear(); // 버퍼 초기화.
                numberOfBytesRead = sbc.read(buffer);
                System.out.println("\nNumber of bytes read: " + numberOfBytesRead);
            }
        }

        final String newLine = System.lineSeparator();

        try (SeekableByteChannel sbc = Files.newByteChannel(path, StandardOpenOption.APPEND)) {
            String output = newLine + "Paul" + newLine + "Carol" + newLine + "Fred";
            ByteBuffer buffer = ByteBuffer.wrap(output.getBytes());
            sbc.write(buffer);
        }
    }
}