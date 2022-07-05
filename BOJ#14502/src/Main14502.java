package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class area{
	int x;
	int y;
	public area(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getXY() {
		// TODO Auto-generated method stub
		return x;
	}
}
public class Main14502 {
	public static int[][] map;
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };
	public static int N;
	public static int M;
	public static int safeZone = 0;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1 = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st1.nextToken());
		M = Integer.parseInt(st1.nextToken());

		map = new int[N][M];
		ArrayList<area> area_inform = new ArrayList<area>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st2.nextToken());
				if(map[i][j]==0) {
					area_inform.add(new area(i,j));
				}
			}
		}
		
		wall();
		spread();
		check();
		System.out.println();
		for (int[] i : map) {
			for (int j : i) {
				System.out.print(j + " ");
			}
			System.out.println();
		}

		System.out.print(safeZone);

	}

	private static void wall() {
		
	}

	private static void spread() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 2) {
					dfs(i, j);
				}
			}
		}
	}

	private static void check() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0)
					safeZone++;
			}
		}
		
	}

	private static void dfs(int i, int j) {
		// TODO DFS±¸Çö
		for (int dir = 0; dir < 4; dir++) {
			int nextX = i + dx[dir];
			int nextY = j + dy[dir];

			if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M)
				continue;
			if (map[nextX][nextY] == 0) {
				map[nextX][nextY] = 2;
				dfs(nextX, nextY);
			}
		}
	}
}
