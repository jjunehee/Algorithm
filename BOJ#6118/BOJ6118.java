import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ6118 {

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

		Queue<Check> q = new LinkedList<>();
		q.add(new Check(1, 0));

		boolean[] visited = new boolean[N + 1];
		visited[1] = true;

		PriorityQueue<Check> pq = new PriorityQueue<>();
		while (!q.isEmpty()) {

			Check now = q.poll();

			for (int v : info[now.v]) {
				if (visited[v]) {
					continue;
				}

				visited[v] = true;
				pq.add(new Check(v, now.dist + 1));
				q.add(new Check(v, now.dist + 1));
			}

		}
		
		Check ret = pq.peek();
		int retNum = ret.v;
		int retDist = ret.dist;
		
		int cnt = 0;
		while(!pq.isEmpty()) {
			Check now = pq.poll();
			if(now.dist == retDist) {
				cnt++;
			}
		}
		
		System.out.println(retNum + " " + retDist + " " + cnt);

	}

	public static class Check implements Comparable<Check>{
		int v;
		int dist;

		public Check(int v, int dist) {
			this.v = v;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Check o) {
			if(this.dist == o.dist) {
				return this.v - o.v;
			} else {
				return o.dist - this.dist;
			}
		}
	}
}
