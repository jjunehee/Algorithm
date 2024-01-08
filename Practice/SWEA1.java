import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1 {

	static boolean[] isStart = new boolean[12000];
	static boolean[] isEnd = new boolean[12000];
	static List<Integer>[] graphInfo;
	static int ret;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		StringTokenizer st;
		int s,e;
		makeStartEnd();
		makeGraph();

//		for (int t = 1; t <= T; t++) {
//			sb.append("#" + t + " ");
//			st = new StringTokenizer(br.readLine());
//			
//			s = Integer.parseInt(st.nextToken());
//			e = Integer.parseInt(st.nextToken());
//			
//			BFS(s, e);
//			
//			sb.append(ret).append("\n");
//
//		}
//		System.out.println(sb.toString());

	}

	public static void makeStartEnd() {
		int n = 1;
		int start = 0;
		int end = 0;
		while (true) {
			start = (int) ((Math.pow(n, 2) - n + 2) / 2);
			end = (int) ((Math.pow(n, 2) + n) / 2);
			if (start > 10000) {
				break;
			}
			isStart[start] = true;
			isEnd[end] = true;
			n++;
		}
		System.out.println(start);
	}

	public static void makeGraph() {
		graphInfo = new ArrayList[12001];
		for (int i = 1; i <= 12000; i++) {
			graphInfo[i] = new ArrayList<>();
		}

		graphInfo[1].add(2);
		graphInfo[2].add(1);
		graphInfo[1].add(3);
		graphInfo[3].add(1);

		int v = 2;
		int level = 2;
		while (v <= 10000) {

			if (isStart[v]) {
				graphInfo[v].add(v + 1);
				graphInfo[v].add(v + level);
				graphInfo[v].add(v + level + 1);
				graphInfo[v + level].add(v);
				graphInfo[v + level + 1].add(v);
			} else if (isEnd[v]) {
				graphInfo[v].add(v - 1);
				graphInfo[v].add(v + level);
				graphInfo[v].add(v + level + 1);
				graphInfo[v + level].add(v);
				graphInfo[v + level + 1].add(v);
				level++;
			} else {
				graphInfo[v].add(v - 1);
				graphInfo[v].add(v + 1);
				graphInfo[v].add(v + level);
				graphInfo[v].add(v + level + 1);
				graphInfo[v + level].add(v);
				graphInfo[v + level + 1].add(v);
			}

			v++;
		}
	}

	public static void BFS(int start, int end) {

		boolean[] visited = new boolean[12000];
		Queue<Check> q = new LinkedList<>();
		q.add(new Check(start, 0));
		while (!q.isEmpty()) {
			Check now = q.poll();
			if (now.v == end) {
				ret = now.dist;
				break;
			}
			for (int v : graphInfo[now.v]) {
				if (visited[v]) {
					continue;
				}

				visited[v] = true;
				q.add(new Check(v, now.dist + 1));
			}
		}
	}

	public static class Check {
		int v;
		int dist;

		public Check(int v, int dist) {
			this.v = v;
			this.dist = dist;
		}
	}
}