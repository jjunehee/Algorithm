import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 구간합 구하기5
public class Main11660 {
	static int[][] map;
	static int[][] sums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];
		sums = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (j == 1) {
					sums[i][j] += map[i][j] + sums[i - 1][N];
				} else {
					
					sums[i][j] += map[i][j] + sums[i][j - 1];
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			int sum = 0;
			while(x1 <= x2) {
				if(y1 == 1) {
					sum += (sums[x2][y2] - sums[x2-1][N]);
				}else {
					sum += (sums[x2][y2] - sums[x2][y1-1]);
				}
				x2--;		
			}
			sb.append(sum).append("\n");
			
			
		}
		System.out.println(sb.toString());
	}
}
