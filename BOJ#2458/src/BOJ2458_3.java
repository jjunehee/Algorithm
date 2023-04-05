import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 키 순서3
public class BOJ2458_3 {

	static int N, M;
	static int[][] adj;
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		adj = new int[N + 1][N + 1];

		int a, b;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			adj[a][b] = 1; // a보다 b가 크다
		}

		for (int k = 1; k <= N; k++) { // 경유
			for (int i = 1; i <= N; i++) { // 출발(학생i)
				if (i == k || adj[i][k] == 0) { // 경유효과 없음. (알수 있는 관계 없음)
					continue;
				}
				for (int j = 1; j <= N; j++) { // 도착(학생j)
					// i<k<j : 학생 i보다 학생 k가 키가 크고 학생 k보다 학생 j가 키가 크면 고로 학생 i보다 학생j의 키가 크다.
					if (adj[i][j] == 1) { // 이미 i보다 j가 크다는 사실관계를 알면 패스
						continue;
					}

					// 위에 adj[i][k] == 0 을 걸러서 왔으므로 항상 1이 보장.
					adj[i][j] = adj[i][k] & adj[k][j];

				}
			}
		}

		int ans = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				adj[i][0] += adj[i][j]; // 자신보다 키가 큰 학생수 누적
				adj[0][j] += adj[i][j]; // 자신보다 키가 작은 학생수 누적
			}
		}

		for (int i = 1; i <= N; i++) {
			if(adj[i][0] + adj[0][i] == N-1) ans++;
		}

		System.out.println(ans);
	}

}
