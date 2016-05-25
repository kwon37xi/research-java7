* [How to use wait, notify and notifyAll in Java - Producer Consumer Example](http://javarevisited.blogspot.kr/2015/07/how-to-use-wait-notify-and-notifyall-in.html) : 기초 정리가 잘 돼 있음.
* [Inter Thread Communication in Java using Wait Notify Example](http://javarevisited.blogspot.kr/2013/12/inter-thread-communication-in-java-wait-notify-example.html)
* [Why wait notify and notifyAll called from synchronized block or method in Java](http://javarevisited.blogspot.kr/2011/05/wait-notify-and-notifyall-in-java.html)
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
* `notifyAll` 은 모든 쓰레드에게 알림을 준다.
* 가급적 **`notifyAll`**을 호출하라.

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
* 그러지 않으면 `IllegalMonitorStateException` 발생
