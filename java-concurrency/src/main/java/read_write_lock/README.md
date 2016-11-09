# ReadWriteLock / ReentrantReadWriteLock
[Example of ReadWriteLock and ReentrantReadWriteLock in Java](http://www.concretepage.com/java/example_readwriteLock_java)



[ReentrantReadWriteLock](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/locks/ReentrantReadWriteLock.html) 은 [ReadWriteLock](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/locks/ReadWriteLock.html)의 구현체이다.

* ReadLock 잡힌 상태 : 여러 쓰레드에서 읽을 수 있다. 그러나 Write는 block된다.
* WriteLock 잡힌 상태 : 단 하나의 쓰레드에서 Write할 수 있다. 모든 Read는 block 된다.

쓰기는 적고 읽기가 많은 상황에서 사용한다.

* Reentrancy : 락을 여러번 다시 잡을 수 있다는 의미.
* fairness
  * non-fair : 락 할당 순서가 임의로 이뤄짐
  * fair : 제일 오래기다린 것이 다음 락을 잡음.
* Lock downgrading : write lock을 read lock으로 변경 가능함.
  * write lock 잡기
  * write 끝내고서
  * read lock 잡기
  * 그리고서 write unlock
  * 이제는 write에서 락을 뺐기지 않은 상태로 read lock을 잡은 상태가됨.

