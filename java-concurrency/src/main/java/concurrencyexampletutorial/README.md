* [What is CountDownLatch in Java](http://javarevisited.blogspot.kr/2012/07/countdownlatch-example-in-java.html)
* [What is CyclicBarrier Example in Java 5](http://javarevisited.blogspot.kr/2012/07/cyclicbarrier-example-java-5-concurrency-tutorial.html)
* [Counting Semaphore Example in Java 5](http://javarevisited.blogspot.kr/2012/05/counting-semaphore-example-in-java-5.html)
* [Difference between ConcurrentHashMap, Hashtable and Synchronized Map in Java](http://javarevisited.blogspot.kr/2011/04/difference-between-concurrenthashmap.html)
* [Producer Consumer Design Pattern with Blocking Queue Example in Java](http://javarevisited.blogspot.kr/2012/02/producer-consumer-design-pattern-with.html)

## CountDownLatch
* [CountDownLatch](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/CountDownLatch.html)는 일종의 synchronizer로
  메인 쓰레드가 처리를 시작하기 전에 하나 혹은 그 이상의 다른 쓰레드들이 특정 조건을 만족시킬 때까지 기다리도록 해주는 역할을 한다.
* `wait`과 `notify`로도 비슷한 일을 할 수 있지만 CountDownLatch가 훨씬 간편하다.
* 어떤 쓰레드(보통 애플리케이션의 main 쓰레드)는 `CountDownLatch.await()`을 호출하고 값이 0 이 되거나 다른 쓰레드에 의해 interrupted 될 때까지 기다린다.
* 다른 쓰레드들은 작업이 끝나거나 준비가 되면 `CountDownLatch.countDown()`을 호출 하여 카운트 다운을 해야한다.
* 카운트가 0이 되면 main 쓰레드가 시작된다.
* 일단 0이된 CountDownLatch는 재사용할 수 없다.

## CyclicBarrier
* [CyclicBarrier](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/CyclicBarrier.html)는 CountDownLatch와 유사하게
  다중 쓰레드가 처리를 시작하기전에 서로를 기다릴 수 있는 방법은 제공한다.
* 개별 태스크 실행을 마친뒤에 마지막 태스크를 수행하고자 할 때 사용할 수 있다.
* 서로를 기다리며 barrier에 도달할 때까지 기다리는 모든 쓰레드를 파티(party)라고 부른다.
* `CyclicBarrier`는 wait할 파티 갯수로 초기화된다. 각 파티는 `CyclicBarrier.await()` 메소드를 호출하여 서로를 기다린다.
  모든 쓰레드 혹은 파티가 `await()`을 호출할 때까지 블록킹한다.
* 일반적으로는 `await()` 호출은 쓰레드가 barrier에서 기다리고 있다고 알리는 행위이다.
* `await()`은 Blocking 호출이지만 다른 쓰레드에 의해 interrupted 될 수 있다.
* `reset()` 메소드를 호출하여 객체를 초기화하면 재사용 가능하다.

## Counting Semaphore
* Counting [Semaphore](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/Semaphore.html) CountDownLatch, CyclicBarrier, Exchanger 같은
  동기화 도우미이다.
* 공유 리소스에 접근하는데 있어 허용 숫자를 제한하고 관리하는 역할을 한다.
* 현재 쓰레드는 허가권을 얻어야한다.
* 만약 허가권이 다른 쓰레드에 의해 모두 소진된 상태라면 다른 쓰레드에서 허가권을 놔줄 때까지 기다린다(`wait`).
* 이는 Producer/Consumer 패턴이나 Thread Pool, DB Connection Pool 구현하는데 유용하다.
* [Semaphore](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/Semaphore.html) 는 허가권 갯수로 초기화된다.
* `Semaphore.acquire()` : 허가권이 생길 때 까지 `wait`.
* Semaphore.release()` 허가권을 반환한다.
* blocking/unblocking 방식 모두 제공.
* *Binary Semahpore* : 허가권이 1개인 Semaphore. 상호 배제 접근(mutual exclusive access) 구현(한번에 동시 접근 불가).
* `tryAcquire()` : 요청시점에 존재하는 허가권 요청. 허가권 없으면 바로 false 리턴.
* `acquireUninterruptibly()` : blocking call. 다른 쓰레드가 interrupt를 걸어도 무시하고 허가권이 생길때까지 혹은 제한 시간까지 계속 `wait`.
