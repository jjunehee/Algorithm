import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea1247 {

	static Customer[] customers;
	static int N;
	static Customer[] pick;
	static boolean[] visited;
	static int count;
	static int cX, cY;
	static int hX, hY;
	static int minDistance;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuffer sb = new StringBuffer();
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			customers = new Customer[N];
			
			sb.append("#" + t + " ");
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			cX = Integer.parseInt(st.nextToken());
			cY = Integer.parseInt(st.nextToken());
			hX = Integer.parseInt(st.nextToken());
			hY = Integer.parseInt(st.nextToken());
			for (int i = 0; i < N; i++) {
				int customerX = Integer.parseInt(st.nextToken());
				int customerY = Integer.parseInt(st.nextToken());
				customers[i] = new Customer(customerX, customerY);
			}
			
			minDistance = Integer.MAX_VALUE;
			visited = new boolean[N];
			pick = new Customer[N];
			permu(0);
			sb.append(minDistance).append("\n");
		}
		
		System.out.println(sb.toString());
		
	}

	private static void permu(int cnt) {
		if (cnt == N) {
			calculate();
			return;
		}

		for (int i = 0; i < N; i++) {
			if (visited[i])
				continue;

			pick[cnt] = customers[i];
			visited[i] = true;
			permu(cnt + 1);
			visited[i] = false;
		}
	}

	private static void calculate() {
		int distance = 0;

		// 회사에서 출발
		int curX = cX;
		int curY = cY;
		
		for (int i = 0; i < N; i++) {
			distance += (Math.abs(curX - pick[i].x) + Math.abs(curY - pick[i].y));
			curX = pick[i].x;
			curY = pick[i].y;
		}
		
		// 집으로 돌아오기
		distance += (Math.abs(curX - hX) + Math.abs(curY - hY));

		minDistance = Math.min(distance, minDistance);
	}

	public static class Customer {
		int x;
		int y;

		public Customer(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
