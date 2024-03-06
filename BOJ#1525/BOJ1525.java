
import java.util.*;
import java.io.*;

public class BOJ1525 {

	static int[][] map;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static HashMap<String, Integer> visited = new HashMap<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new int[3][3];
		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		String str = "";
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				str += map[i][j] + "";
			}
		}
		visited.put(str, 0);
		System.out.println(bfs(str));
	}

	public static int bfs(String str) {
		Queue<String> q = new LinkedList<>();
		q.add(str);

		while (!q.isEmpty()) {
			String now = q.poll();
			int zeroOrder = now.indexOf('0');
			
			int move = visited.get(now);
			int nowX = zeroOrder / 3;
			int nowY = zeroOrder % 3;
			if (now.equals("123456780")) {
				return move;
			}
			int nx, ny;
			for (int dir = 0; dir < 4; dir++) {
				nx = nowX + dx[dir];
				ny = nowY + dy[dir];
				if (isBound(nx, ny)) {
					continue;
				}
				int index = nx * 3 + ny;
				char tmp = now.charAt(index);
				String next = now.replace(tmp, 'c');
				next = next.replace('0', tmp);
				next = next.replace('c', '0');

				if (!visited.containsKey(next)) {
					q.add(next);
					visited.put(next, move + 1);
				}
			}
		}
		return -1;
	}

	public static boolean isBound(int nx, int ny) {
		if (nx < 0 || nx >= 3 || ny < 0 || ny >= 3) {
			return true;
		}
		return false;
	}
}
