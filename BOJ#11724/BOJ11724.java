import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ11724 {

	static List<Integer>[] graphInfo;
	static boolean[] visited;
	static int N, M;
	static int ret;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graphInfo = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graphInfo[i] = new ArrayList<>();
		}

		int v1, v2;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			v1 = Integer.parseInt(st.nextToken());
			v2 = Integer.parseInt(st.nextToken());
			graphInfo[v1].add(v2);
			graphInfo[v2].add(v1);
		}

		visited = new boolean[N + 1];
		System.out.println(countComponent());

	}

	private static int countComponent() {
		for (int i = 1; i <= N; i++) {

			if (visited[i]) {
				continue;
			}
			ret++;
			dfs(i);
		}
		
		return ret;
	}

	private static void dfs(int num) {
		
		visited[num] = true;
		
		int size = graphInfo[num].size();
		for (int i = 0; i < size; i++) {
			int v = graphInfo[num].get(i);
			if(visited[v]) {
				continue;
			}
			
			dfs(v);
		}
		
	}

}
