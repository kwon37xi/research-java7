package ch02_recursion_backtracking;

/**
 * 하노이탑.
 *
 * 세개의 막대와 여기에 쌓여질 서로 다른 크기의 원반들.
 * 원반들이 한 막대에 크기가 작은 것부터 큰 순서로, 제일 위에 제일 작은 원반이 있는 원뿔 형태로 시작.
 * 전체 원반을 다른 막대로 다음 규칙을 지켜가며 옮긴다.
 * - 한번의 하나의 원반만 옮길 수 있다.
 * - 옮길 때마다 한 막대의 맨 앞의 원반을 다른 막대에 이미 놓여있는 원반 위로 옮기게 된다.
 * - 자기보다 작은 원반 위로는 옮길 수 없다.
 */
public class TowerOfHanoi {

	/**
	 * 어떤 원반 x를 기준으로 할 때,  x - 1 을 가운데로 옮기고, x를 목표로 옮기고, x - 1을 목표에 있는 x위로 옮긴다.
     *
     * @param n 원반 갯수
     * @param frompeg 시작 막대
     * @param topeg 목표 막대
     * @param auxpeg 중간 막대
     */
    public static void towerOfHanoi(int n, char frompeg, char topeg, char auxpeg) {
        if (n == 1) {
            System.out.println("Move disk 1 from peg - " + frompeg + " - " + "to peg " + topeg);
            return;
        }

        towerOfHanoi(n - 1, frompeg, auxpeg, topeg);

        System.out.println("Move disk " + n + " from peg " + frompeg + " to peg " + topeg);

        towerOfHanoi(n - 1, auxpeg, topeg, frompeg);
    }

    public static void main(String[] args) {
        towerOfHanoi(3, 'A', 'Z', 'M');
    }
}
