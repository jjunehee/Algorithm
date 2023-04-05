import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3055 {
	static String[][] map;
	static Point target;
	static Point start;
	static ArrayList<Point> waterList = new ArrayList<>();
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static int R, C;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new String[R][C];

		// map 입력
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = String.valueOf(str.charAt(j));
				if (map[i][j].equals("D")) {
					target = new Point(i, j);
				} else if (map[i][j].equals("S")) {
					start = new Point(i, j);
				} else if (map[i][j].equals("*")) {
					waterList.add(new Point(i, j));
				}
			}
		}
		visited = new boolean[R][C];
		int result = simulation();
		if (result != -1) {
			System.out.println(result);
		} else {
			System.out.println("KAKTUS");
		}
	}

	private static int simulation() {
		Queue<Point> waterQ = new LinkedList<>();
		Queue<Point> animalQ = new LinkedList<>();

		for(Point water : waterList) {
			waterQ.add(water);
		}
		animalQ.add(start);

		int time = 0;
		while (!animalQ.isEmpty()) { // 동물이 더이상 갈 곳이 없을때까지

			// 일단 물부터 퍼지기
			int waterSize = waterQ.size();
			for (int i = 0; i < waterSize; i++) {
				Point flood = waterQ.poll();
				for (int dir = 0; dir < 4; dir++) {
					int nx = flood.x + dx[dir];
					int ny = flood.y + dy[dir];

					if (nx >= 0 && nx < R && ny >= 0 && ny < C && !visited[nx][ny] && !map[nx][ny].equals("X")
							&& !map[nx][ny].equals("D")) {
						map[nx][ny] = "*";
						visited[nx][ny] = true;
						waterQ.add(new Point(nx, ny));
					}
				}
			}

			// 고슴도치 움직이자
			int animalSize = animalQ.size();
			for (int i = 0; i < animalSize; i++) {
				Point now = animalQ.poll();
				if (now.x == target.x && now.y == target.y) {
					return time;
				}
				for (int dir = 0; dir < 4; dir++) {
					int nx = now.x + dx[dir];
					int ny = now.y + dy[dir];

					if (nx >= 0 && nx < R && ny >= 0 && ny < C && !visited[nx][ny] && !map[nx][ny].equals("X")
							&& !visited[nx][ny] && !map[nx][ny].equals("*")) {
						visited[nx][ny] = true;
						map[nx][ny] = "S";
						animalQ.add(new Point(nx, ny));
					}
				}
			}
			time++;
		}
		return -1;
	}

	public static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
