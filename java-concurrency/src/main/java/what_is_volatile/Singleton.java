package what_is_volatile;

/**
 * volatile example
 */
public class Singleton {
    private static volatile Singleton _instance;

    public static Singleton getInstance() {

        if (_instance == null) { // 1. volatile을 사용하지 않으면, 이 지점에서 2.에서 초기화가 완료된 객체를 인지하지 못하고 null 로 간주할 수 있음.
            synchronized (Singleton.class) {
                if (_instance == null) {
                    _instance = new Singleton(); // 2. 최초의 객체 생성쓰레드가 객체생성해서 할당.
                }
            }
        }
        return _instance;
    }
}
