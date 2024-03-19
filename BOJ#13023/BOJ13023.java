import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ13023 {

	static int N, M;

	static List<Integer>[] graphInfo;
	static boolean[] visited;
	static int ret;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graphInfo = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			graphInfo[i] = new ArrayList<>();
		}

		int a = 0;
		int b = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			graphInfo[a].add(b);
			graphInfo[b].add(a);
		}

		boolean flag = false;
		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			if (dfs(i, 1)) {
				flag = true;
				break;
			}
		}
		
		if (!flag) {
			bw.write(0 + "\n");
		} else {
			bw.write(1 + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

	public static boolean dfs(int vertex, int cnt) {

		if (cnt == 5) {
			return true;
		}
		visited[vertex] = true;

		for (int neighbor : graphInfo[vertex]) {
			if (visited[neighbor]) {
				continue;
			}
			if (dfs(neighbor, cnt + 1)) {
				return true;
			}
		}

		visited[vertex] = false;
		return false;
	}

}
