package concurrencyexampletutorial;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(3); // 총 3개 카운트
        Thread cacheService = new Thread(new Service("CacheService", 3000, latch));
        Thread alertService = new Thread(new Service("AlertService", 2000, latch));
        Thread validationService = new Thread(new Service("ValidationService", 5000, latch));

        cacheService.start();
        alertService.start();
        validationService.start();

        // 모든 서비스가 up 을 출력할 때까지 애플리케이션은 시작되지 않는다.
        // 메인 쓰레드는 count 3 에서 시작되어 각 쓰레드가 Up을 출력하면서 countDown()을 호출하여  0이 될때까지 기다리고 있는다.
        // 이를통해 메인 쓰레드가 모든 서비스가 Up 될 때까지 처리를 보류하도록 보장해준다.
        try {
            latch.await(); // CountDownLatch 가 종료되어 0이 될 때까지 기다린다.
            System.out.println("All services are up, Application is starting now.");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}

