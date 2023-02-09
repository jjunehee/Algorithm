import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea2001 {
	static int[][] map;
	static int N, M;
	static int sum;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int max = Integer.MIN_VALUE;
			for (int i = 0; i <= N - M; i++) {
				for (int j = 0; j <= N - M; j++) {
					sum = 0;
					Catch(i, j);
					max = Math.max(max, sum);
				}
			}
			sb.append(max).append("\n");
			
		}
		System.out.println(sb.toString());

	}

	private static void Catch(int x, int y) {

		for (int i = x; i < x + M; i++) {
			for (int j = y; j < y + M; j++) {
				sum += map[i][j];
			}
		}
		
	}

}
