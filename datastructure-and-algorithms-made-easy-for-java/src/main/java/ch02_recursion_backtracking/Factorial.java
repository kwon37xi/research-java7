package ch02_recursion_backtracking;

/**
 * Factorial example.
 *
 * n이 1보다 큰 재귀의 경우에는 함수는 (n-1)!의 값을 구하기 위해 자기 자신을 호출하며, 그 결과를 n에 곱한다.
 * n이 0이거나 1인 기본 경우에는, 함수는 1을 리턴한다.
 */
public class Factorial {
    public static long factorial(long n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        System.out.println("10! " + factorial(10));
        System.out.println("100! " + factorial(15));
    }
}
