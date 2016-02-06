package ch02_recursion_backtracking;

/**
 * n 비트의 모든 문자열을 생성하라. A[0..n-1]은 크기 n인 배열이라고 가정하라.
 * -- 책이 제대로 안돼 있음.
 */
public class BackTracking02 {
    public static int[] arr = new int[10];
    public static void binary(int n) {
        if (n < 1) {
            for (int i : arr) {
                System.out.println(arr[i]);
            }
            System.out.println();
        }

        arr[n - 1] = 0;
        binary(n - 1);
        arr[n - 1] = 1;
        binary(n - 1);
    }

    public static void main(String[] args) {
        binary(10);
    }
}
