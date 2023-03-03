import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Swea5656_3 {

	static int[][] map;
	static int[][] copyMap;
	static int[] pick;
	static int N, W, H;
	static int min;
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	private static class Point {
		int r, c, cnt;

		public Point(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			min = Integer.MAX_VALUE;
			go(0, map);
			sb.append(min).append("\n");
		}
		System.out.println(sb.toString());
	}

	// 구슬 한번 던지기
	private static boolean go(int count, int[][] map) { // 구슬이 던져진 횟수, 벽돌 맵

		int result = getRemain(map);

		if (result == 0) {
			min = 0;
			return true;
		}

		if (count == N) {
			if (min > result)
				min = result;
			return false;
		}

		int[][] newMap = new int[H][W];

		// 모든 열에 구슬 던져보기
		for (int c = 0; c < W; c++) { // c : 구슬을 던지는 열

			// 구슬에 처음 맞는 벽돌 찾기(위쪽에서)
			int r = 0;
			while (r < H && map[r][c] == 0)
				++r;

			if (r == H)
				continue;

			// 배열 원본의 상태로 초기화
			copyMap(map, newMap);
			// 벽돌 부수기
			boom(newMap, r, c, newMap[r][c]);
			// 벽돌 내리기
			down(newMap);
			// 다음 구슬 던지러 가기
			if (go(count + 1, newMap))
				return true;
		}
		return false;
	}

	private static int getRemain(int[][] map) {

		int count = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] > 0)
					count++;
			}
		}

		return count;
	}

	private static Stack<Integer> stack = new Stack<>();

	private static void down(int[][] map) {
		// 각 열에 대해 윗행부터 아래행까지 벽돌만 스택에 넣어두고 빼서 아래행부터 채우기
		for (int c = 0; c < W; c++) {
			for (int r = 0; r < H; r++) {
				if (map[r][c] > 0) {
					stack.push(map[r][c]);
					map[r][c] = 0;
				}
			}

			int r = H - 1;
			while (!stack.isEmpty()) {
				map[r--][c] = stack.pop();
			}
		}
	}

	// DFS
	private static void boom(int[][] map, int r, int c, int cnt) {

		map[r][c] = 0;
		if (cnt == 1) {
			return;
		}

		// 현벽돌의 cnt-1만큼 4방을 체크
		for (int dir = 0; dir < 4; dir++) {
			int nr = r;
			int nc = c;
			for (int k = 1; k <= cnt - 1; k++) {
				nr += dr[dir];
				nc += dc[dir];
				if (nr >= 0 && nr < H && nc >= 0 && nc < W && map[nr][nc] > 0) {
					boom(map, nr, nc, map[nr][nc]);
				}
			}
		}
	}

	private static void copyMap(int[][] map, int[][] newMap) {

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				newMap[i][j] = map[i][j];
			}
		}

	}
}
