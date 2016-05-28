* [What is CountDownLatch in Java](http://javarevisited.blogspot.kr/2012/07/countdownlatch-example-in-java.html)
* [What is CyclicBarrier Example in Java 5](http://javarevisited.blogspot.kr/2012/07/cyclicbarrier-example-java-5-concurrency-tutorial.html)
* [Counting Semaphore Example in Java 5](http://javarevisited.blogspot.kr/2012/05/counting-semaphore-example-in-java-5.html)
* [Difference between ConcurrentHashMap, Hashtable and Synchronized Map in Java](http://javarevisited.blogspot.kr/2011/04/difference-between-concurrenthashmap.html)
* [Producer Consumer Design Pattern with Blocking Queue Example in Java](http://javarevisited.blogspot.kr/2012/02/producer-consumer-design-pattern-with.html)

## CountDownLatch
* [CountDownLatch](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/CountDownLatch.html)는 일종의 synchronizer로
  하나 혹은 그 이상의 쓰레드가 처리를 시작하기 전에 특정 조건을 만족시킬 때까지 기다리도록 해주는 역할을 한다.
* `wait`과 `notify`로도 비슷한 일을 할 수 있지만 CountDownLatch가 훨씬 간편하다.
* 어떤 쓰레드(보통 애플리케이션의 main 쓰레드)는 `CountDownLatch.await()`을 호출하고 값이 0 이 되거나 다른 쓰레드에 의해 interrupted 될 때까지 기다린다.
* 다른 쓰레드들은 작업이 끝나거나 준비가 되면 `CountDownLatch.countDown()`을 호출 하여 카운트 다운을 해야한다.
* 카운트가 0이 되면 main 쓰레드가 시작된다.
* 일단 0이된 CountDownLatch는 재사용할 수 없다.
