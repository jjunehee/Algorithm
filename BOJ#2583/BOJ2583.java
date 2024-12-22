import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2583 {

	static int M, N, K;
	static boolean[][] map;
	static PriorityQueue<Group> groupList;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// M,N,K Input
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new boolean[N][M];

		// K Info Input
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());

			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());

			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			paintKToMap(x1, y1, x2 - 1, y2 - 1);
		}

		countGroup();

		StringBuilder sb = new StringBuilder();
		sb.append(groupList.size()).append("\n");
		
		while(!groupList.isEmpty()) {
			sb.append(groupList.poll().size + " ");
		}

		System.out.print(sb.toString());

	}

	public static void paintKToMap(int x1, int y1, int x2, int y2) {
		for (int i = x1; i <= x2; i++) {
			for (int j = y1; j <= y2; j++) {
				map[i][j] = true;
			}
		}
	}

	public static void countGroup() {

		groupList = new PriorityQueue<>();

		int groupNum = 1;
		int groupSize = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!map[i][j]) {
					groupSize = groupingByBfs(i, j);
					groupList.add(new Group(groupNum++, groupSize));
				}
			}
		}
	}

	public static int groupingByBfs(int x, int y) {

		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(x, y));
		map[x][y] = true;

		int cnt = 1;

		while (!q.isEmpty()) {

			Pos now = q.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nx = now.x + dx[dir];
				int ny = now.y + dy[dir];

				if (isBound(nx, ny) || map[nx][ny]) {
					continue;
				}

				q.add(new Pos(nx, ny));
				map[nx][ny] = true;
				cnt++;
			}
		}

		return cnt;
	}

	public static boolean isBound(int nx, int ny) {
		if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
			return true;
		} else {
			return false;
		}
	}

	public static class Group implements Comparable<Group> {
		int idx;
		int size;

		public Group(int idx, int size) {
			this.idx = idx;
			this.size = size;
		}

		@Override
		public int compareTo(Group o) {
			return this.size - o.size;
		}
		
	}
	
	public static class Pos {
		int x;
		int y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
