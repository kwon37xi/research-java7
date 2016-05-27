* [How to use wait, notify and notifyAll in Java - Producer Consumer Example](http://javarevisited.blogspot.kr/2015/07/how-to-use-wait-notify-and-notifyall-in.html) : 기초 정리가 잘 돼 있음.
* [Inter Thread Communication in Java using Wait Notify Example](http://javarevisited.blogspot.kr/2013/12/inter-thread-communication-in-java-wait-notify-example.html)
* [Difference between notify and notifyAll in Java - When and How to use](http://javarevisited.blogspot.kr/2012/10/difference-between-notify-and-notifyall-java-example.html)
* [Why wait, notify and notifyAll is defined in Object Class and not on Thread class in Java](http://javarevisited.blogspot.kr/2012/02/why-wait-notify-and-notifyall-is.html)

## wait, notify 용도
쓰레드간의 커뮤니케이션에 사용한다. 하나의 쓰레드에서 기다리는 동안(`wait`), 다른 쓰레드가 공유 객체에 값을 넣고 `notify`를 콜해서 첫번째 쓰레드에 공유값을 체크해보라고 통지를 하는 방식.

공유 객체 검사는 Loop로 해야한다. 단순히 if 한번으로 처리할 수 없다.

## wait() 의 호출
* `wait()` 은 두 쓰레드가 공유하는 객체의 메소드를 호출해야한다.
* 항상 `synchronized` 블록에서 호출해야한다.
* 항상 `while` 문으로 조건을 체크하는 블럭 안의 맨 마지막에서 호출한다(깨어났을 때 즉시 다시 조건 체크를 하도록). `if` 블록에서 호출해서는 안 된다.
* 조건이 만족되지 않아도 waiting에서 깨어나는 경우가 있을 수 있는데, 이 때 루프를 통해 다시 한 번 조건을 체크하지 않으면 버그가 될 수 있다.
* 혹은 `if`를 사용했을 때 조건을 만족하기 이전에 먼저 `notify`가 호출된 상태에서 `if` 블록에 진입했다면 데드락에 걸릴 수 있다.

## notify/notifyAll 차이점
* `notify`는 기다리는(waiting) 쓰레드(monitor라고 부름) 딱 한 개에게만 알림을 준다. 뭐가 될지는 알 수 없다.
* `notifyAll` 은 동일 모니터/Lock 을 waiting 하고 있는 모든 쓰레드에게 알림을 준다. 그러나 깨어나자 마자 Lock을 잡으려고 경쟁하며 Lock을 잡은
쓰레드만이 나머지 코드를 실행하고, 다른 쓰레드들은 조건 체크 loop에 의해 다시 `wait` 상태로 들어가야한다.
* 가급적 **`notifyAll`**을 호출하라. 명백히 하나의 쓰레드가 요청을 소비하는게 확실 할 때만 `notify`를 호출한다.

## Producer/Consumer pattern
```java
// Consumer
synchronized (obj) {
    while(!workTodo) {
        obj.wait();
    }
    // get next item from the queue and do work on the item
    workTodo = false;
}

// Producer
synchronized (obj) {
    while (workToDo) {
        obj.wait();
    }
    // add work to queue(do producing job)
    workToDo = true;
    obj.notifyAll();
}
```

## wait, notify 를 왜 synchronized에서 불러야 하나?
* [Why wait notify and notifyAll called from synchronized block or method in Java](http://javarevisited.blogspot.kr/2011/05/wait-notify-and-notifyall-in-java.html)
* 그러지 않으면 `IllegalMonitorStateException` 발생
* 동기화를 하지 않으면
* producer가`wait` 상태에 빠지기 직전에 consumer가 `notify` 하는 상태가 될 수 있고
* 이 상황에서 producer가 `wait`하게 되면 consumer는 이미 `notify`를 했기 때문에 더이상 다시 `notify`안 함.
* 데드락 상태에 빠짐.
* 따라서 먼저 대상 공유객체를 `synchronized`에서 lock을 획득해야만 wait/notify를 호출 할 수 있도록 한다.
* 맞나??

## wait, sleep, yield의 차이점
* [Difference between Wait and Sleep, Yield in Java](http://javarevisited.blogspot.kr/2011/12/difference-between-wait-sleep-yield.html)
* `sleep`, `yield`는 Thread 클래스에, `wait`은 Object 에 존재하는 메소드
* `wait`은 쓰레드간 통신용도, `sleep`은 쓰레드 멈충 용도. `synchronized` 블럭에서 호출해야한다.
* 쓰레드에서 `wait`이 호출되면 잡고있던 monitor 혹은 lock을 해제한다.
* `sleep`은 lock을 해제하지 않고 기다리기만 한다. `synchronized` 블럭에서 호출할 필요가 없다.
* `yield`는 쓰레드가 잡고있는 CPU를 해제하고 다른 쓰레드에게 CPU를 할당할 수 있게 해준다.
  하지만 어느 쓰레드가 CPU를 받게될지는 알 수 없다.
  쓰레드 스케줄러가 알아서 하며 `yield`를 호출한 쓰레드가 다시 할당받을 가능성도 있다.
  따라서 `yield`에 의존하여 로직을 처리하는 코드를 작성하면 안된다.
* `sleep`과 `yield`는 항상 해당 메소드를 호출한 쓰레드에 적용한다. Thread 객체에 대고 실행한다고 해서 해당 객체의 쓰레드에 적용되는 것이 아니다. static 이므로.
