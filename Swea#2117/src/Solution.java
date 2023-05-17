import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");

			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			map = new int[N][N];

			List<Home> homeList = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						homeList.add(new Home(i, j));
					}
				}
			}

			int maxHome = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int[] cnt = new int[41];
					for (int s = 0; s < homeList.size(); s++) {
						cnt[Math.abs(i - homeList.get(s).x) + Math.abs(j - homeList.get(s).y) + 1]++;
					}

					int cntHome = 0;
					for (int k = 1; k <= 21; k++) {
						cntHome += cnt[k];
						if (cntHome * M >= k * k + (k - 1) * (k - 1) && maxHome < cntHome) {
							maxHome = cntHome;
						}
					}
				}
			}

			sb.append(maxHome).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static class Home {
		int x, y;

		public Home(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
