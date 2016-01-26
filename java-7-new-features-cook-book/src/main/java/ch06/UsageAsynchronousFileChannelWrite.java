package ch06;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class UsageAsynchronousFileChannelWrite {
    public static void main(String[] args) throws Exception {
        try (AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(Paths.get("/tmp/docs/asynchronous.txt"),
                StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {

            CompletionHandler<Integer, Object> handler = new CompletionHandler<Integer, Object>() {
                @Override
                public void completed(Integer result, Object attachment) {
                    System.out.println("Attachment: " + attachment + " " + result + " bytes written");
                    System.out.println("CompletionHandler Thread ID: " + Thread.currentThread().getId());
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    System.out.println("Attachment: " + attachment + " failed with: ");
                    exc.printStackTrace();
                }
            };

            System.out.println("Main Thread ID: " + Thread.currentThread().getId());
            // handler가 없을 경우에만 Future<Integer> 를 리턴한다.
            // handler가 있으면, handler가 Future의 역할을 대신하므로 불필요한듯.
//            fileChannel.write(ByteBuffer.wrap("Sample".getBytes()), 0, "First Write", handler);
//            fileChannel.write(ByteBuffer.wrap("Box".getBytes()), 0, "Second Write", handler);
            final Future<Integer> writeFuture1 = fileChannel.write(ByteBuffer.wrap("Sample".getBytes()), 0);
            final Future<Integer> writeFuture2 = fileChannel.write(ByteBuffer.wrap("Box".getBytes()), 0);

            int result = writeFuture1.get();
            System.out.println("Sample write completed with " + result + " bytes written");
            result = writeFuture2.get();
            System.out.println("Box write completed with " + result + " byte written");

            // CompletionHandler 사용시 1초 정도 쉬어줘야 올바르게 모든 값을 Write한다.
//            TimeUnit.SECONDS.sleep(1);
        }
    }
}
