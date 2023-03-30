import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//외판원순회2
public class BOJ10971 {

	static int[][] map;
	static int N;
	static boolean[] visited;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			DFS(i, i, 0, 1);
		}
		System.out.println(min);
	}

	public static void DFS(int start, int cur, int price, int cnt) {
		
		if(price > min) {
			return;
		}
		
		
		
		if (cnt == N) {
			if(map[cur][start] == 0) {
				return;
			}
			price = price + map[cur][start];
			min = Math.min(price, min);
			return;
		}
		
		///////////////////////////////////
		
		visited[cur] = true;

		for (int i = 0; i < N; i++) {
			if (visited[i] || map[cur][i] == 0) {
				continue;
			}
			
			DFS(start, i, price + map[cur][i], cnt + 1);
		}
		
		visited[cur] = false;
		

	}
}
