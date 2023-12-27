import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2660 {

	static int N;
	static List<Integer>[] info;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		info = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			info[i] = new ArrayList<>();
		}

		StringTokenizer st;
		int v1, v2;
		while (true) {
			st = new StringTokenizer(br.readLine());
			v1 = Integer.parseInt(st.nextToken());
			v2 = Integer.parseInt(st.nextToken());

			if (v1 == -1 && v2 == -1) {
				break;
			}

			info[v1].add(v2);
			info[v2].add(v1);
		}

		solution();
	}

	public static void solution() {

		PriorityQueue<Person> pq = new PriorityQueue<>();

		for (int i = 1; i <= N; i++) {

			Queue<Count> q = new LinkedList<>();

			boolean[] visited = new boolean[N + 1];
			visited[i] = true;
			q.add(new Count(i, 0));

			int ret = 0;


			int max = Integer.MIN_VALUE;
			while (!q.isEmpty()) {
				Count now = q.poll();
				max = Math.max(max, now.cnt);
				for (int v2 : info[now.v]) {
					if (visited[v2]) {
						continue;
					}
					visited[v2] = true;
					q.add(new Count(v2, now.cnt + 1));
				}
			}
			pq.add(new Person(i, max));
		}

		int score = pq.peek().score;
		StringBuilder sb = new StringBuilder();
		
		int cnt = 0;
		while (!pq.isEmpty()) {
			Person p = pq.poll();
			if(score == p.score) {
				cnt++;
				sb.append(p.id).append(" ");
			} else {
				break;
			}
		}
		
		System.out.println(score + " " + cnt);
		System.out.println(sb.toString());
	}

	public static class Count {
		int v;
		int cnt;

		public Count(int v, int cnt) {
			this.v = v;
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
