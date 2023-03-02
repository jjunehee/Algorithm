import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17472 {

	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static int N, M;
	static ArrayList<Node>[] list;
	static PriorityQueue<pq_Edge> pq;
	static int[] parents;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int num = 0;
		visited = new boolean[N][M];
		list = new ArrayList[7];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					num++;
					list[num] = new ArrayList<>();
					bfs(i, j, num);
				}
			}
		}

		pq = new PriorityQueue<>();
		for (int i = 1; i <= num; i++) {
			for (int j = 0; j < list[i].size(); j++) {
				Node v = list[i].get(j);
				for (int dir = 0; dir < 4; dir++) {
					findBridge(v.x, v.y, dir, -1, i);
				}
			}
		}

		Kruskal(num);

		System.out.println(result);

	}

	private static void Kruskal(int num) {

		parents = new int[num + 1];

		for (int i = 1; i <= num; i++) {
			parents[i] = i;
		}

		int bridge = 0;
		for (pq_Edge edge : pq) {
		}
		while (!pq.isEmpty()) {
			pq_Edge Edge = pq.poll();
			if (union(Edge.from, Edge.to)) {
				result += Edge.len;
				bridge++;
			}
		}

		if (bridge != num - 1)
			result = -1;

	}

	private static boolean union(int v1, int v2) {
		int pV1 = findSet(v1);
		int pV2 = findSet(v2);

		if (pV1 == pV2) {
			return false;
		} else {
			parents[pV1] = pV2;
			return true;
		}
	}

	private static int findSet(int v) {

		if (v == parents[v]) {
			return v;
		} else {
			return parents[v] = findSet(parents[v]);
		}
	}

	private static void findBridge(int x, int y, int dir, int len, int preIsland) {
		if (map[x][y] != preIsland && map[x][y] != 0) {
			if (len >= 2) {
				pq.add(new pq_Edge(preIsland, map[x][y], len));
			}
			return;
		}

		int nx = x + dx[dir];
		int ny = y + dy[dir];

		if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
			return;
		}

		if (map[nx][ny] == preIsland) {
			return;
		}

		findBridge(nx, ny, dir, len + 1, preIsland);

	}

	private static void bfs(int x, int y, int num) {
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(x, y));

		visited[x][y] = true;

		while (!q.isEmpty()) {

			Node cur = q.poll();
			map[cur.x][cur.y] = num; // 섬 영역 칠하기
			list[num].add(new Node(cur.x, cur.y)); // 해당 섬의 각 칸을 추가!

			for (int dir = 0; dir < 4; dir++) { // 4방향을 돌면서 섬 영역 찾기
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M) { // 칠하는 과정에서 맵의 범위를 벗어나면 continue!
					continue;
				}

				if (visited[nx][ny] || map[nx][ny] != 1) { // 그 섬에서 이미 칠한 곳이나, 섬 밖이면 continue;
					continue;
				}

				visited[nx][ny] = true;
				q.offer(new Node(nx, ny));
			}

		}

	}

	public static class pq_Edge implements Comparable<pq_Edge> {
		int from;
		int to;
		int len;

		public pq_Edge(int from, int to, int len) {
			this.from = from;
			this.to = to;
			this.len = len;
		}

		@Override
		public int compareTo(pq_Edge o) {
			return this.len - o.len;
		}
	}

	public static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}
}
