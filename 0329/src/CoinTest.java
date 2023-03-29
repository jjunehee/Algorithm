import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CoinTest {
	static int N;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		System.out.println(BFS());
	}

	public static int BFS() {
		Queue<Integer> q = new LinkedList<>();
		q.add(N);

		int count = 0;
		boolean flag = false;
		while (!q.isEmpty()) {

			int size = q.size();
			for (int i = 0; i < size; i++) {
				int x = q.poll();

				if (x == 0) {
					min = count;
					flag = true;
					break;
				}
				if (x - 1 >= 0) {
					q.add(x - 1);
				}
				if (x - 4 >= 0) {
					q.add(x - 4);
				}
				if (x - 6 >= 0) {
					q.add(x - 6);
				}
			}
			if (flag) {
				break;
			}
			count++;
		}
		return count;
	}

}
