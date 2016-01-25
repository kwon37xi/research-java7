package ch06.asyncsocketchannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketOption;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class UsageAsynchronousServerSocketChannel {
    public static void main(String[] args) throws IOException, InterruptedException {
        final AsynchronousServerSocketChannel listener =
                AsynchronousServerSocketChannel.open();

        InetSocketAddress address = new InetSocketAddress("localhost", 5000);
        listener.bind(address);

        listener.setOption(StandardSocketOptions.SO_RCVBUF, 123456);

        final Set<SocketOption<?>> socketOptions = listener.supportedOptions();
        for (SocketOption<?> socketOption : socketOptions) {
            System.out.println(socketOption.toString() + ": " + listener.getOption(socketOption));
        }


        listener.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
            @Override
            public void completed(AsynchronousSocketChannel channel, Void attachment) {
                try {
                    System.out.println("Server: completed method executing");
                    while (true) {
                        ByteBuffer buffer = ByteBuffer.allocate(32);
                        Future<Integer> readFuture = channel.read(buffer); // 소켓에서 읽어 buffer에 저장함.
                        Integer number = readFuture.get(); // 읽은 byte 수를 리턴함.
                        if (number < 0) {
                            break;
                        }
                        System.out.println("Server: Message received: " + number + " - " + new String(buffer.array()));
                    }
                    System.out.println("closing connection..");
                    channel.close();
                } catch (IOException | ExecutionException | InterruptedException e) {
                    throw new IllegalStateException(e.getMessage(), e);
                }
            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                System.out.println("Server: CompletionHandler exception");
                exc.printStackTrace();
            }
        });

        while (listener.isOpen()) { // 리스너가 살아 있는 동안 계속 실행. 죽으면 종료.
            TimeUnit.MILLISECONDS.sleep(100);
        }
    }
}