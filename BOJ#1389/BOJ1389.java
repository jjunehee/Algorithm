import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1389 {

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
			info[v2].add(v1);
		}

		solution();
	}

	public static void solution() {

		PriorityQueue<Person> pq = new PriorityQueue<>();
		for (int i = 1; i <= N; i++) {

			boolean[] visited = new boolean[N + 1];
			visited[i] = true;

			Queue<Count> q = new LinkedList<>();
			q.add(new Count(i, 0));

			int ret = 1;
			while (!q.isEmpty()) {
				Count now = q.poll();

				for (int v : info[now.id]) {
					if (visited[v]) {
						continue;
					}

					visited[v] = true;
					ret += now.cnt;
					q.add(new Count(v, now.cnt + 1));
				}
			}
			
			pq.add(new Person(i,ret));

		}
		System.out.println(pq.poll().id);
	}

	public static class Count {
		int id;
		int cnt;

		public Count(int id, int cnt) {
			this.id = id;
			this.cnt = cnt;
		}
	}

	public static class Person implements Comparable<Person> {
		int id;
		int score;

		public Person(int id, int score) {
			this.id = id;
			this.score = score;
		}

		@Override
		public int compareTo(Person o) {

			if (this.score == o.score) {
				return this.id - o.id;
			} else {
				return this.score - o.score;
			}
		}
	}
}
