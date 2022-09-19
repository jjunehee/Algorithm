package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main15685 {
	public static final int SIZE = 101;
	public static int[][] map;
	public static LinkedList<Integer> list;
	public static int[] dx = { 1, 0, -1, 0 };
	public static int[] dy = { 0, -1, 0, 1 };
	public static boolean[][] visited;
	public static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		visited = new boolean[SIZE][SIZE];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());

			list = new LinkedList<Integer>();
			list.add(d);
			curveMake(list, g);
			drawMap(x, y);

		}
		checkMap();
		System.out.println(count);
	}

	private static void checkMap() {
		for (int i = 0; i < SIZE-1; i++) {
			for (int j = 0; j < SIZE-1; j++) {
				if (visited[i][j] == true && visited[i + 1][j] == true && visited[i][j + 1] == true
						&& visited[i + 1][j + 1] == true) {
					count++;
				}
			}
		}
	}

	private static void drawMap(int x, int y) {
		int nx = x;
		int ny = y;
		int size = list.size();

		visited[nx][ny] = true;
		for (int i = 0; i < size; i++) {
		
			nx += dx[list.get(i)];
			ny += dy[list.get(i)];
			if (nx < 0 || nx > 100 || ny < 0 || ny > 100) {
				break;
			}
			
			visited[nx][ny] = true;

		}

	}

	private static void curveMake(LinkedList<Integer> list, int g) {
		for (int i = 0; i < g; i++) {

			int size = list.size();

			for (int j = 1; j <= size; j++) {
				list.add((list.get(size - j) + 1) % 4);
			}
		}
	}
}
