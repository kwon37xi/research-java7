package ch02_recursion_backtracking;

/**
 * 재귀 메모리 시각화
 */
public class RecursionMemoryView {
    public static int print(int n) {
        if (n == 0) {
            return 0;
        } else {
            System.out.println(n);
            return print(n - 1);
        }
    }

    public static void main(String[] args) {
        print(10);
    }
}
