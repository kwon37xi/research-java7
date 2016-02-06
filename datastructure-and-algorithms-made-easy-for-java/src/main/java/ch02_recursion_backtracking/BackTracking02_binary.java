package ch02_recursion_backtracking;

/**
 * n 비트의 모든 문자열을 생성하라. A[0..n-1]은 크기 n인 배열이라고 가정하라.
 * -- 책이 제대로 안돼 있음. <a href="http://www.studyalgorithms.com/theory/generate-all-strings-of-n-bits-assume-a0-n-1-be-an-array-of-size-n/">Generate All Strings of ‘n’ bits. Assume A[0….n-1] be an array of size n.</a> 참조.
 */
public class BackTracking02_binary {

    public static char[] arr = new char[4];

    public static void binary(int n) {
        if (n < 1) {
            for (int i = 0; i < 4; i++) {
                System.out.print(arr[i]);
            }
            System.out.println();
        } else {
            // 최 우측 비트를 0으로 놓고, 그 미만의 비트들에 대한 모든 상태를 출력해봄
            arr[n - 1] = '0';
            binary(n - 1);

            // 최 우측 비트를 1로 놓고, 그 미만의 비트들에 대한 모든 상태를 출력해봄.
            arr[n - 1] = '1';
            binary(n - 1);
        }

    }

    public static void main(String[] args) {
        binary(4);
    }
}
