package ch06.asyncsocketchannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class UsageAsynchronousSocketChannel {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress("localhost", 5000);

        Future<Void> future = client.connect(address);
        System.out.println("Client: Waiting for the connection to the complete");
        future.get(); // 접속 될 때까지 기다림. 성공적으로 접속하면 null을 리턴함.

        String message;
        do {
            System.out.println("Enter a message: ");
            Scanner scanner = new Scanner(System.in);
            message = scanner.nextLine();
            System.out.println("Client: Sending ...");
            ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
            System.out.println("Client: Message sent: " + new String(buffer.array()));
            client.write(buffer);
        } while (!"quit".equals(message));
    }
}
