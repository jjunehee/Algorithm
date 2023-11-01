import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Softeer1 {

	static PriorityQueue<Metal> metalList = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		// 가방이 버틸 수 있는 무게
		int W = Integer.parseInt(st.nextToken());

		// 금속 종류
		int N = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			metalList.offer(new Metal(M, P));
		}

		int sum = 0;

		while (!metalList.isEmpty()) {
			if (W == 0) {
				break;
			}
			Metal now = metalList.poll();
			if (W >= now.m) {
				sum += (now.m * now.p);
				W -= now.m;
			} else {
				sum += (W * now.p);
				W -= W;
			}
		}
		System.out.println(sum);
	}

	public static class Metal implements Comparable<Metal> {
		int m;
		int p;

		public Metal(int m, int p) {
			this.m = m;
			this.p = p;
		}

		@Override
		public int compareTo(Metal o) {
			return o.p - this.p;
		}
	}
}
