package ch02_recursion_backtracking;

/**
 * n...k-1 사이에서 고른 길이가 n인 모든 문자열 생성.
 * <a href="https://shiv4289.wordpress.com/tag/backtracking/">Backtracking-I (Primer)</a> 참조.
 *
 * "abc"라는 문자열이 있을때 n=2 라면 ab, ac, bc, ba, ... 형태의 모든 문자열을 출력하는 것.
 */
public class BackTracking03_kstring {

    private static char[] str = { 'a', 'b', 'c', 'd' };
    private static char[] result = new char[2];

    static void kstring(int n, int k) {
        if (n < 1) {
            for (int i = 0; i < 2; i++) {
                System.out.print(result[i]);
            }
            System.out.println();
        } else {
            for (int j = 0; j < k; j++) {
                result[n - 1] =str[j];
                kstring(n - 1, k);
            }
        }
    }

    public static void main(String[] args) {
        kstring(2, 4);
    }
}
