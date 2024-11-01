
import java.io.*;
import java.util.*;

public class Softeer317409 {

	static int n, m;
	static int[][] map;
	static int[][] tmpMap;
	static int[][] copyMap;
	static boolean[][] visited;
	static int max;
	static int answer = Integer.MIN_VALUE;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static boolean[] permuCheck;
	static int[] pick;
	static List<Pos> people;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][n];
		visited = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		people = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			people.add(new Pos(x, y));
		}

		pick = new int[m];
		permuCheck = new boolean[m];
		permu(0, 0);

		System.out.println(answer);
	}

	public static int[][] copy(int[][] map) {
		int[][] newMap = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				newMap[i][j] = map[i][j];
			}
		}
		return newMap;
	}

	public static void permu(int idx, int cnt) {
		if (cnt == m) {

			tmpMap = copy(map);
			int ret = 0;

			for (int p = 0; p < pick.length; p++) {

				Pos person = people.get(pick[p]);
				max = 0;

				copyMap = new int[n][n];

				tmpMap[person.x][person.y] = 0;

				// 친구 1호 출동
				ret += map[person.x][person.y];
				dfs(person.x, person.y, 0, 0);
				ret += max;

				tmpMap = copy(copyMap);

			}
			answer = Math.max(answer, ret);

			return;
		}

		for (int i = 0; i < m; i++) {
			if (permuCheck[i]) {
				continue;
			}

			permuCheck[i] = true;
			pick[cnt] = i;
			permu(idx + 1, cnt + 1);
			permuCheck[i] = false;

		}
	}

	public static void dfs(int x, int y, int cnt, int sum) {
		if (cnt == 3) {
			if (max < sum) {
				copyMap = copy(tmpMap);
				max = sum;
			}
			return;
		}

		for (int dir = 0; dir < 4; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];

			if (isBound(nx, ny) || visited[nx][ny]) {
				continue;
			}

			int value = tmpMap[nx][ny];
			visited[nx][ny] = true;
			tmpMap[nx][ny] = 0;
			dfs(nx, ny, cnt + 1, sum + value);
			tmpMap[nx][ny] = value;
			visited[nx][ny] = false;

		}

	}

	public static boolean isBound(int nx, int ny) {
		if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
			return true;
		}
		return false;
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
