package src;

import java.io.*;
import java.util.*;

public class Main5 {

	public static int[][] map;
	public static int x, y;
	public static int[] order;
	public static int N, M;
	public static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		

		// map
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		// 주사위 위치
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());

		// 명령 개수
		int K = Integer.parseInt(st.nextToken());
		order = new int[K];

		// map 입력
		for (int i = 0; i < N; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st1.nextToken());
			}
		}

		Marble marble = new Marble(map, x, y);

		// 위치이동 명령 입력
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			order[i] = Integer.parseInt(st2.nextToken());
		}

		// 주사위 이동
		for (int i = 0; i < K; i++) {
			marble.move(order[i]);
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	
	}

	public static class Marble {
		private int x;
		private int y;
		private int[] marble = new int[7];
		private int[] temp = new int[7];
		private int[] dx = { 0, 0, -1, 1 };
		private int[] dy = { 1, -1, 0, 0 };
		

		public Marble(int[][] map, int x, int y) {
			this.x = x;
			this.y = y;
		}

		// 주사위 이동하면서 marble배열 변경해주기
		void move(int dir) {
			
			if (x + dx[dir - 1] < 0 || y + dy[dir - 1] < 0 || x + dx[dir - 1] >= N || y + dy[dir - 1] >= M)
				return;

			x += dx[dir - 1];
			y += dy[dir - 1];
			temp = marble.clone();

			switch (dir) {

			case 1: // 우
				marble[1] = temp[4];
				marble[3] = temp[1];
				marble[4] = temp[6];
				marble[6] = temp[3];
				copy(map[x][y]);
				break;

			case 2: // 좌
				marble[1] = temp[3];
				marble[3] = temp[6];
				marble[4] = temp[1];
				marble[6] = temp[4];
				copy(map[x][y]);
				break;

			case 3: // 상
				marble[1] = temp[2];
				marble[2] = temp[6];
				marble[5] = temp[1];
				marble[6] = temp[5];
				copy(map[x][y]);
				break;

			case 4: // 하
				marble[1] = temp[5];
				marble[2] = temp[1];
				marble[5] = temp[6];
				marble[6] = temp[2];
				copy(map[x][y]);
				break;
			}
			sb.append(marble[1]).append("\n");
		}

		void copy(int mapNum) {
			if (mapNum == 0) {
				map[x][y] = marble[6];
			} else {
				marble[6] = map[x][y];
				map[x][y] = 0;
			}
		}

	}
}
