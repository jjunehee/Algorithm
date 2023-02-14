import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main15686 {
	static int[][] map;
	static ArrayList<Chicken> chickenList = new ArrayList<>();
	static Chicken[] chArray;
	static int N, M;
	static int minDistance = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					chickenList.add(new Chicken(i, j));
				}
			}
		}

		chArray = new Chicken[M];
		combChicken(0, 0);
		System.out.println(minDistance);

	}

	private static void combChicken(int cnt, int idx) {
		if (cnt == M) {
			calculate();
			return;
		}

		for (int i = idx; i < chickenList.size(); i++) {
			chArray[cnt] = chickenList.get(i);
			combChicken(cnt + 1, i + 1);
		}

	}

	private static void calculate() { // 모든 집에서 뽑은 치킨집까지의 거리들의 합 계산

		int distance = 0;
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				
				if (map[i][j] == 1) {
					distance = Integer.MAX_VALUE;
					for (int t = 0; t < chArray.length; t++) {
						distance = Math.min(distance, Math.abs(i - chArray[t].x) + Math.abs(j - chArray[t].y));
						
					}
					sum += distance;
				}
				
			}
		}
		minDistance = Math.min(minDistance, sum);

	}

	public static class Chicken {
		int x;
		int y;

		public Chicken(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}