import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class aProblem1 {

	static int N;
	static int[] K;
	static int[] pick;
	static boolean[] visited;
	static boolean[] boom;
	static int maxScore;
	static int score;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			N = Integer.parseInt(br.readLine());
			K = new int[N];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				K[i] = Integer.parseInt(st.nextToken());
			}

			pick = new int[N];
			visited = new boolean[N];
			permu(0);
			sb.append(maxScore).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static void permu(int cnt) {
		if (cnt == N) {
			score = 0;
			gameStart();
			maxScore = Math.max(maxScore, score);
			return;
		}

		for (int i = 0; i < N; i++) {
			if (visited[i]) {
				continue;
			}
			pick[cnt] = i;
			visited[i] = true;
			permu(cnt + 1);
			visited[i] = false;

		}
	}

	public static void gameStart() {

		boom = new boolean[N];
		for (int i = 0; i < N; i++) {
			int order = pick[i];

			boom[order] = true;
			// 왼쪽
			int left = order;
			while (true) {
				left = left - 1;
				if(left < 0) {
					break;
				}
				if (!boom[left]) {
					break;
				}
			}

			// 오른쪽
			int right = order;
			while (true) {
				right = right + 1;
				
				if(right >= N) {
					break;
				}
				if (!boom[right]) {
					break;
				}
			}
			
			if(left < 0 && right >= N) {
				score += K[order];
			} else if( left >= 0 && right >= N) {
				score += K[left];
			} else if( left < 0 && right < N) {
				score += K[right];
			} else if(left >= 0 && right < N) {
				score += (K[left] * K[right]);
			}
		}

	}

}
