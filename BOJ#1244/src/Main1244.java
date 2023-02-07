import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1244 {
	static int num;
	static int[] bulb;
	static Queue<Order> q = new LinkedList<>();
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		bulb = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			bulb[i] = Integer.parseInt(st.nextToken());
		}

		num = Integer.parseInt(br.readLine());

		for (int i = 0; i < num; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			q.add(new Order(A, B));
		}

		recur(0);

	}

	private static void recur(int cnt) {

		if (cnt == num) {
			int c = 0;
			for (int i = 1; i <= bulb.length - 1; i++) {
				c++;
				System.out.print(bulb[i]);
				if (c % 20 == 0) {
					System.out.println();
					continue;
				}
				System.out.print(" ");
			}
			System.out.println();
			return;
		}

		Order order = q.poll();

		switch (order.sex) {
		case 1:
			for (int idx = order.num; idx <= bulb.length - 1; idx += order.num) {
				Switch(idx);
			}
			recur(cnt + 1);
			break;
		case 2:
			int mid = order.num;
			int left = mid - 1;
			int right = mid + 1;
			Switch(mid);
			while (left >= 1 && right <= N) {
				if (bulb[left] != bulb[right]) {
					break;
				}
				Switch(left);
				Switch(right);
				left--;
				right++;
			}
			recur(cnt + 1);
			break;
		}
	}

	private static void Switch(int idx) {
		if (bulb[idx] == 0) {
			bulb[idx] = 1;
		} else {
			bulb[idx] = 0;
		}
	}

	public static class Order {
		int sex;
		int num;

		public Order(int sex, int num) {
			this.sex = sex;
			this.num = num;
		}
	}

}
