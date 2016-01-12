package ch01.tryewithresources;

public class TryWithResourcesSuppressedExceptions {
    // try 구문이 끝나면 close 메소드가 스택을 통해 처리되기 때문에 선언된 순서의 반대로 호출된다.
    // 따라서 resource2.close() -> resource1.close() 순서로 호출된다.
    public static void main(String[] args) {
        try (FirstAutoCloseableResource resource1 = new FirstAutoCloseableResource();
        SecondAutoCloseableResource resource2 = new SecondAutoCloseableResource()) {
            resource1.manipulateResource();
            resource2.manipulateResource();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            for (Throwable throwable : e.getSuppressed()) {
                System.out.println(throwable);
            }
        }
    }
}
