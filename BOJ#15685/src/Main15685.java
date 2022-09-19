package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main15685 {
	public static int[][] map;
	public static LinkedList<Integer> list;
	public static int[] dx = { 1, 0, -1, 0 };
	public static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		map = new int[100][100];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());

			list.add(d);
			curveMake(list, g);
			drawMap(list);

		}
	}

	private static void drawMap(LinkedList<Integer> list) {
		
	}

	private static void curveMake(LinkedList<Integer> list, int g) {
		for (int i = 0; i < g; i++) {

			int size = list.size();

			for (int j = 0; j < size; j++) {
				list.add((list.get(size - j) + 1) % 4);
			}
		}
	}
}
