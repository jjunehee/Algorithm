import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1707 {

	static List<Integer>[] info;
	static int V, E;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			info = new ArrayList[V + 1];
			for (int i = 1; i <= V; i++) {
				info[i] = new ArrayList<>();
			}

			int v1, v2;
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				v1 = Integer.parseInt(st.nextToken());
				v2 = Integer.parseInt(st.nextToken());
				info[v1].add(v2);
				info[v2].add(v1);
			}

			solution();
		}
		System.out.println(sb.toString());

	}

	public static void solution() {

		boolean[] visited = new boolean[V + 1];
		boolean[] checkFlag = new boolean[V + 1];
		boolean flag = true;
		
		outerloop: for (int i = 1; i <= V; i++) {
			if (!visited[i]) {
				
				Queue<Check> q = new LinkedList<>();
				
				q.add(new Check(i, true));
				visited[i] = true;
				checkFlag[i] = true;

				while (!q.isEmpty()) {
					Check now = q.poll();

					for (int v : info[now.v]) {
						if (visited[v]) {
							if (checkFlag[v] == now.flag) {
								flag = false;
								break outerloop;
							}
						} else {
							visited[v] = true;
							checkFlag[v] = !now.flag;
							q.add(new Check(v, !now.flag));
						}
					}
				}
			}
		}
		if (flag) {
			sb.append("YES").append("\n");
		} else {
			sb.append("NO").append("\n");
		}
	}

	public static class Check {
		int v;
		boolean flag;

		public Check(int v, boolean flag) {
			this.v = v;
			this.flag = flag;
		}

	}

}
