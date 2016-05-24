* [Inter Thread Communication in Java using Wait Notify Example](http://javarevisited.blogspot.kr/2013/12/inter-thread-communication-in-java-wait-notify-example.html)
* [Why wait notify and notifyAll called from synchronized block or method in Java](http://javarevisited.blogspot.kr/2011/05/wait-notify-and-notifyall-in-java.html)
* [Difference between notify and notifyAll in Java - When and How to use](http://javarevisited.blogspot.kr/2012/10/difference-between-notify-and-notifyall-java-example.html)
* [Why wait, notify and notifyAll is defined in Object Class and not on Thread class in Java](http://javarevisited.blogspot.kr/2012/02/why-wait-notify-and-notifyall-is.html)

## wait, notify 용도
쓰레드간의 커뮤니케이션에 사용한다. 하나의 쓰레드에서 기다리는 동안(wait), 다른 쓰레드가 공유 객체에 값을 넣고 notify를 콜해서 첫번째 쓰레드에 공유값을 체크해보라고 통지를 하는 방식.

공유 객체 검사는 Loop로 해야한다. 단순히 if 한번으로 처리할 수 없다.

## wait, notify 를 왜 synchronized에서 불러야 하나?
* 그러지 않으면 IllegalMonitorStateException 발생

