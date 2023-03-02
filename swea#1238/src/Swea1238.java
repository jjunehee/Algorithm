import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swea1238 {

	static int N;
	static ArrayList<Integer>[] list;
	static boolean[] visited;
	static int maxTime;
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= 10; t++) {
			sb.append("#" + t + " ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			list = new ArrayList[101];
			for (int i = 1; i <= 100; i++) {
				list[i] = new ArrayList<>();
			}
			visited = new boolean[101];
			int startV = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N / 2; i++) {
				int v1 = Integer.parseInt(st.nextToken());
				int v2 = Integer.parseInt(st.nextToken());
				list[v1].add(v2);
			}

			max = Integer.MIN_VALUE;
			maxTime = 0;
			call(startV);
			sb.append(max).append("\n");
		}
		System.out.println(sb.toString());

	}

	private static void call(int v) {

//		visited[v] = true;
//		System.out.println("cur : " + v +  " time : " + time);
//		for (int vertex : list[v]) {
//
//			if (!visited[vertex]) {
//				call(vertex, time + 1);
//			}
//		}
//		if (time == maxTime) {
//			max = Math.max(max, v);
//			maxTime = time;
//		} else if (time > maxTime) {
//			max = v;
//			maxTime = time;
//		}

		Queue<Vertex> q = new ArrayDeque<>();
		q.offer(new Vertex(v, 0));
		visited[v] = true;

		while (!q.isEmpty()) {
			Vertex cur = q.poll();
			int curV = cur.v;
			int curTime = cur.time;

			if (curTime > maxTime) {
				max = curV;
				maxTime = curTime;
			} else if (curTime == maxTime) {
				max = Math.max(max, curV);
			}

			for (int vertex : list[curV]) {
				if (!visited[vertex]) {
					visited[vertex] = true;
					q.offer(new Vertex(vertex, curTime + 1));
				}
			}
		}
	}

	public static class Vertex {
		int v;
		int time;

		public Vertex(int v, int time) {
			this.v = v;
			this.time = time;
		}
	}
}
