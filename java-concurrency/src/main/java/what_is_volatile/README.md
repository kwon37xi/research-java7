* [How Volatile in Java works? Example of volatile keyword in Java](http://javarevisited.blogspot.kr/2011/06/volatile-keyword-java-example-tutorial.html)

* `volatile`(휘발성) 키워드는 Java Compiler와 Thread에게 해당 값을 캐시하지 말고, 항상 [메인 메모리](http://javarevisited.blogspot.com/2011/05/java-heap-space-memory-size-jvm.html)에서 읽어야 한다고 지시할 때 사용한다.
* 읽기/쓰기 연산을 atomic 하게 하려는 공유 변수가 존재한다면 그 때 `volatile`을 사용한다.
* 오직 변수에만 사용할 수 있다.
* 가시성과 순서를 보장한다.
** Java 5 이후부터는 `volatile` 변수에 대한 쓰기는 읽기보다 앞서 일어나게 보장한다.
** JVM과 컴파일러에게 `volatile`은 재정렬을 막고 synchronization barrier에서 빠지지 않게 보장해준다.
* long, double을 제외한 대부분의 primitive 변수들은 `volatile` 없이도 읽기/쓰기가 atomic 하다.
* `volatile` 변수에 대한 접근은 blocking을 일으키지 않는다.
* `volatile` 자바 객체에 null 도 허용된다.
* `volatile` 변수에 ``++`` 를 쓴다고 ``++`` 연산이 atomic 해지지는 않는다.
* 멀티 쓰레드에서 접근하지 않는 변수에 `volatile` 사용할 필요 없다.

## 언제 사용하나?
* 공유 long, double 변수(64bit 데이터타입)를 atomic 하게 접근하고자 할 때 사용한다. 여러 플랫폼에서 64bit 값 저장은 atomic하지 않게 이뤄진다.
  많은 플랫폼들이 long과 double 변수를 2단 계로 나눠 32bit 씩 쓰기한다. 따라서 여러 쓰레드가 각 32bit를 서로 다른 값으로 볼 가능성이 있다.
* `synchronization` 대신 사용할 수도 있다. 모든 읽기 쓰레드는 `volatile` 변수가 쓰기 연산이 끝난 수정된 값을 보게 보장된다. `volatile`이 없으면
  여러 읽기 쓰레드가 서로 다른 값을 볼 수도 있다.
* `volatile` 키워드를 통해 컴파일러에게 특정 필드가 멀티 쓰레드에서 접근 된다는 사실을 명시해줄 수 있다. 서
  그러면 컴파일러가 재정렬이나 멀티 쓰레드 환경에서 하면안되는 최적화에서 해당 필드를 제외시켜준다. `volatile`이 없으면 컴파일러가
  코드 재정렬이나 값 캐시 등을 할 수 있다.
* [DoubleCheckedLocking](https://en.wikipedia.org/wiki/Double-checked_locking) 시에 사용할 것.

## synchronized와의 차이점
* `volatile`은 필드에 `synchronized`는 코드 블럭이나 메소드에
* `synchronized`는 모니터에 락을 잡지만 `volatile`은 락을 잡지 않는다.
* `synchronized`는 java 쓰레드가 waiting하며 block될 수 있지만 `volatile`은 쓰레드를 block하지 않는다.
* `synchronized`는 `volatile`보다 성능에 영향을 더 많이 준다.
* `volatile`은 쓰레드 메모리와 메인 메모리간에 변수의 값 하나만 동기화하고, `synchronized`는 쓰레드 메모리와 메인 메모리간에 모든 변수의 값들을
 동기화하고 모니터를 Lock/Release한다. 이로인해 `synchronized`는 오버헤드가 더 크다.
* `null`값은 `synchronized` 할 수 없지만 `volatile`에서는 `null`이 허용된다.
* Java 5부터는 `volatile` 필드에 대한 쓰기는 모니터 release와 같은 메모리 효과를 갖고, 읽기는 모니터 acquire와 같은 효과를 갖는다.

* *결과적으로 `volatile`을 `synchronized` block/method 대용으로 사용은 하지 마라. 하지만 가끔씩 `volatile`로 성능 높은 동기화 도구가 될 수도 있다.
 
