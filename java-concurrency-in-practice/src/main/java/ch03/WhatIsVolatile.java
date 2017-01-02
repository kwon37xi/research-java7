package ch03;

/*
64bit 숫자형 연산은 volatile을 사용하지 않으면 fetch/store가 단일하지 않게 실행된다. 두번의 32bit 연산이 사용될 수 있다.
따라서 volatile을 지정하지 않은 long 변수의 값을 쓰기/읽기가 서로 다른 쓰레드에서 동작한다면 각각 32bit 씩 읽을 가능성이
생긴다.
따라서 volatile로 지정하지도 않고, 락을 사용해 동기화하지도 않은 상태로 long, double 값을 동시에 여러
쓰레드에서 사용한다면 항상 이상한 문제가 발생할 가능성이 있다.

volatile로 선언된 변수의 값을 바꿨을 때 다른 쓰레드에서 항상 최신 값을 읽어갈 수 있게 해준다.
volatile을 사용하면 : 컴파일러와 런타임 모두 "이 변수는 공유해 사용하고, 실행 순서를 재배치 해서는 안된다"라고 인식.
   이 변수는 프로세서의 레지스터에 캐시되지도 않고, 프로세서 외부 캐시에도 안 들어감.
   메모리 가시성 입장에서 본다면 volatile 변수를 사용하는 것과 synchronized 키워드로 특정 코드를 묶는게 비슷한 효과.
   그렇다고 volatile 에 너무 의존하지 말것. synchronized보다 가독성이 떨어짐.

volatile이 적절한 때: 일반적으로 변수에 보관된 클래스의 상태에 대한 가시성을 확보하거나, 중요한 이벤트(초기화, 종료 등)가
발생했다는 등의 정보를 정확하게 전달하고자 할 때. 상태 보관 플래그 변수등.
증감 연사자에 대한 동기화는 안됨.

결론적으로 volatile은
 - 변수에 값을 저장하는 작업이 해당 변수의 현재 값과 관련이 없거나(증감연산자는 관련이 있으므로 안됨), 해당 변수의
   값을 변경하는 쓰레드가 하나만 존재
 - 해당 변수가 객체의 불변조건을 이루는 다른 변수와 달리 불변 조건에 관련되어 있지 않을 때
 - 해당 변수를 사용하는 동안에는 어떤 경우라도 락을 걸 필요가 없을 때

 */
public class WhatIsVolatile {
    private volatile boolean asleep;

    public void countSheep() {
        while (!asleep)
            countSomeSheep();
    }

    private void countSomeSheep() {
        // no op
    }
}
