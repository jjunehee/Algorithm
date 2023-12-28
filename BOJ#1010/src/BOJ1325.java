import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1325 {

	static int N, M;
	static List<Integer>[] info;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		info = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			info[i] = new ArrayList<>();
		}

		int v1, v2;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			v1 = Integer.parseInt(st.nextToken());
			v2 = Integer.parseInt(st.nextToken());
			info[v1].add(v2);
		}

		solution();
	}

	public static void solution() {

		int[] score = new int[N + 1];
		for (int i = 1; i <= N; i++) {

			boolean[] visited = new boolean[N + 1];
			visited[i] = true;

			Queue<Integer> q = new LinkedList<>();
			q.add(i);

			while (!q.isEmpty()) {
				int now = q.poll();

				for (int v : info[now]) {
					if (visited[v]) {
						continue;
					}

					score[v]++;
					visited[v] = true;
					q.add(v);
				}
			}

		}

		int max = Integer.MIN_VALUE;
		for (int num : score) {
			max = Math.max(max, num);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			if(score[i] == max) {
				sb.append(i + " ");
			}
		}
		
		System.out.println(sb.toString());

	}

}
