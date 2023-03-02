import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17135 {
	static int[][] map;
	static int[][] copyMap;
	static int[] pick;
	static int N, M, D;
	static boolean[] select;
	static int[] dx = { 0, -1, 0 };
	static int[] dy = { -1, 0, 1 };
	static boolean[][] visited;
	static int score;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		pick = new int[3];
		comb(0, 0);

	}

	private static void comb(int cnt, int idx) {
		if (cnt == 3) {
//			System.out.println(Arrays.toString(pick));

			score = 0;
			attack();
			return;
		}

		for (int i = idx; i < M; i++) {
			pick[cnt] = i;
			comb(cnt + 1, i + 1);
		}
	}

	private static void attack() {

		copyMap = new int[N][M];
		for (int i = 0; i < map.length; i++) {
			copyMap[i] = map[i].clone();
		}

		// 궁수 3명이 차례로 타깃을 선정해야함

		Queue<Pos> achor = new ArrayDeque<>();
		for (int j : pick) {
//			System.out.println(j);
			achor.add(new Pos(N, j));
		}

		Queue<Pos> target = new ArrayDeque<>();
		int distance = 1;
		while (!achor.isEmpty() && distance <= D) {
			Pos cur = achor.poll(); // 궁수 poll

//			System.out.println("achor" + cur.x + " " + cur.y);
			visited = new boolean[N][M];
			for (int dir = 0; dir < 3; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) {
					continue;
				}
				visited[nx][ny] = true;
				if (copyMap[nx][ny] == 1) {
					target.add(new Pos(nx, ny));
					break;
				}
				achor.add(new Pos(nx, ny));
				distance++;
			}
		}
		for (Pos t : target) {
			System.out.println(t.x + " " + t.y);
			copyMap[t.x][t.y] = 0;
			score++;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(copyMap[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("score" + score);
		System.out.println("==");

	}

	private static class Pos {
		int x;
		int y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
