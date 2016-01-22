package ch05;

import java.nio.file.*;

public class UsageWatchService {
    public static void main(String[] args) throws Exception {
        FileSystem fileSystem = FileSystems.getDefault();
        WatchService watchService = fileSystem.newWatchService();
        Path directory = Paths.get("/tmp/docs");

        WatchEvent.Kind<?>[] events = {
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY
        };
        directory.register(watchService, events); // 디렉토리에 WatchService 등록

        while (true) {
            System.out.println("Waiting for a watch event");
            WatchKey watchKey = watchService.take(); // 여기서 이벤트 발생시까지 대기.
            System.out.println("Path being watched: " + watchKey.watchable());
            System.out.println();

            if (watchKey.isValid()) {
                // pollEvents 로 이벤트 목록을 구한다.
                for (WatchEvent<?> event : watchKey.pollEvents()) {
                    System.out.println("Kind: " + event.kind());
                    System.out.println("Context: " + event.context());
                    System.out.println("Count: " + event.count()); // 이벤트 발생횟수. watchKey.reset()을 호출하면 초기화됨.
                    System.out.println();
                }
            }

            // watchKey를 reset하면 키 값을 다시 사용하기 전까지 ready 상태로 바꾼다는 의미이다.
            // 이 메소드는 false를 리턴하며 키 값이 더이상 유효하지 않다는 의미이다.
            boolean valid = watchKey.reset();
            if (!valid) {
                // watchKey에 대한 등록이 해제됨.
            }
        }
    }
}
