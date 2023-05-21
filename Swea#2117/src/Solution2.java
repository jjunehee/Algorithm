import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution2 {
	static int N, M;
	static int[][] map;
	static List<Pos> homeList;
	static int[] dist; // index 거리에 있는 집 개수
	static int ret;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new int[N][N];

			homeList = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						homeList.add(new Pos(i, j));
					}
				}
			}

			ret = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dist = new int[50];
					simulation(i, j);
				}
			}
			sb.append(ret).append("\n");

		}
		System.out.println(sb.toString());
	}

	private static void simulation(int x, int y) {
		// 현재 위치에서 집들과의 거리
		for (int i = 0; i < homeList.size(); i++) {
			int distance = Math.abs(x - homeList.get(i).x) + Math.abs(y - homeList.get(i).y);
			dist[distance]++;
		}

		for (int K = 1; K <= 21; K++) {
			int homeCnt = 0;
			for (int h = 0; h < K; h++) {
				homeCnt += dist[h];
			}

			int value = (homeCnt * M) - (K * K + (K - 1) * (K - 1));
			if (value >= 0) {
				ret = Math.max(ret, homeCnt);
			}
		}

	}

	public static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
