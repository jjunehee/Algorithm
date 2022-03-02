package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static char[][] map;
	static boolean[][][][] visited;
	static int holeX, holeY;
	static Marble red, blue;

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M][N][M];

		// 구슬 map 만들기
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);

				if (map[i][j] == 'O') {
					holeX = i;
					holeY = j;
				} else if (map[i][j] == 'R') {
					red = new Marble(i, j, 0, 0, 0);
				} else if (map[i][j] == 'B') {
					blue = new Marble(0, 0, i, j, 0);
				}
			}
		}
		System.out.println(bfs());

		br.close();
	}

	public static int bfs() {
		// 너비우선 탐색
		Queue<Marble> queue = new LinkedList<>();
		queue.add(new Marble(red.rx, red.ry, blue.bx, blue.by, 1));
		visited[red.rx][red.ry][blue.bx][blue.by] = true;

		while (!queue.isEmpty()) {
			Marble marble = queue.poll();
			int curRx = marble.rx;
			int curRy = marble.ry;
			int curBx = marble.bx;
			int curBy = marble.by;
			int curCnt = marble.cnt;

			if (curCnt > 10) {
				return -1;
			}

			for (int i = 0; i < 4; i++) {
				int newRx = curRx;
				int newRy = curRy;
				int newBx = curBx;
				int newBy = curBy;

				boolean isRedHole = false;
				boolean isBlueHole = false;
				// 빨간 구슬 이동
				while (map[newRx + dx[i]][newRy + dy[i]] != '#') {
					newRx += dx[i];
					newRy += dy[i];
					if (newRx == holeX && newRy == holeY) {
						isRedHole = true;
						break;
					}
				}

				// 파란 구슬 이동
				while (map[newBx + dx[i]][newBy + dy[i]] != '#') {
					newBx += dx[i];
					newBy += dy[i];
					if (newBx == holeX && newBy == holeY) {
						isBlueHole = true;
						break;
					}
				}

				if (isBlueHole)
				{
					continue;
				}
				
				if (isRedHole && !isBlueHole) {
					
					return curCnt;
				}
				// 위치가 겹쳐버린 구슬 처리
				if (newRx == newBx && newRy == newBy) {
					if (i == 0) {
						if(curRx>curBx) newRx -= dx[i];
						else newBx -= dx[i];
					} else if (i == 1) {
						if(curRy>curBy) newBy -= dy[i];
						else newRy -= dy[i];
					} else if (i == 2) {
						if(curRx>curBx) newBx -= dx[i];
						else newRx -= dx[i];
					} else if (i == 3) {
						if(curRy>curBy) newRy -= dy[i];
						else newBy -= dy[i];
					}
				}
				
				if(!visited[newRx][newRy][newBx][newBy]) {
					visited[newRx][newRy][newBx][newBy] = true;
					queue.add(new Marble(newRx,newRy,newBx,newBy,curCnt+1));
				}

			}
			
		}

		return -1;
	}
}

class Marble {
	int rx;
	int ry;
	int bx;
	int by;
	int cnt;

	Marble(int rx, int ry, int bx, int by, int cnt) {
		this.rx = rx;
		this.ry = ry;
		this.bx = bx;
		this.by = by;
		this.cnt = cnt;
	}
}