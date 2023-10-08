
// 놀이기구 탑승

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class CodeTreeProblem2 {

	static int[][] likeInfo;
	static int[][] map;
	static int N;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		likeInfo = new int[N * N + 1][N * N + 1];
		int[] order = new int[N*N+1];

		// n명의 사람
		for (int i = 1; i <= N * N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int person = Integer.parseInt(st.nextToken());
			order[i] = person;
			for (int j = 0; j < 4; j++) {
				int r = Integer.parseInt(st.nextToken());
				likeInfo[person][r] = 1; // person이 r을 좋아한다.
			}
		}

		for (int i = 1; i <= N*N; i++) {
			selectPos(order[i]);
		}

		System.out.println(calculateScore());

	}

	public static int calculateScore() {

		int result = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				int cnt = 0;

				for (int dir = 0; dir < 4; dir++) {
					int nx = i + dx[dir];
					int ny = j + dy[dir];

					if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
						continue;
					}

					if (likeInfo[map[i][j]][map[nx][ny]] == 1) {
						cnt++;
					}
				}

				if (cnt == 0) {
					continue;
				} else if (cnt == 1) {
					result += 1;
				} else if (cnt == 2) {
					result += 10;
				} else if (cnt == 3) {
					result += 100;
				} else if (cnt == 4) {
					result += 1000;
				}
			}
		}

		return result;
	}

	public static void selectPos(int personNum) {

		List<Pos>[] cntInfoList = new ArrayList[5];

		for (int i = 0; i <= 4; i++) {
			cntInfoList[i] = new ArrayList<>();
		}

		// 좋아하는 사람이 가장 많은 자리 셀렉. 동률이면 어떻게 할 것인가.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				
				if(map[i][j] != 0 ) {
					continue;
				}
				int aroundCnt = 0;
				for (int dir = 0; dir < 4; dir++) {

					int nx = i + dx[dir];
					int ny = j + dy[dir];

					if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
						continue;
					}

					if (map[nx][ny] != 0) { // 누군가 있다.
						if (likeInfo[personNum][map[nx][ny]] == 1) { // 그 사람이 좋아하는 사람
							aroundCnt++;
						}
					}
				}
				cntInfoList[aroundCnt].add(new Pos(i, j));
			}
		}

		Pos fixPos = null;
		loopOut: for (int i = 4; i >= 0; i--) {

			if (cntInfoList[i].size() > 0) {

				if (cntInfoList[i].size() == 1) {
					fixPos = cntInfoList[i].get(0);
					break loopOut;
				} else { // 주변에 빈칸이 많은 칸을 셀렉
					List<Pos>[] aroundBlank = new ArrayList[5];

					for (int k = 0; k <= 4; k++) {
						aroundBlank[k] = new ArrayList<>();
					}

					for (Pos now : cntInfoList[i]) {

						int nx;
						int ny;

						int blankCnt = 0;

						for (int dir = 0; dir < 4; dir++) {
							nx = now.x + dx[dir];
							ny = now.y + dy[dir];

							if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
								continue;
							}

							if (map[nx][ny] == 0) {
								blankCnt++;
							}
						}
						aroundBlank[blankCnt].add(now);
					}

					for (int b = 4; b >= 0; b--) {
						if (aroundBlank[b].size() > 0) {

							if (aroundBlank[b].size() == 1) {
								fixPos = aroundBlank[b].get(0);
								break loopOut;
							} else { // 행, 열 작은 순서대로

								Collections.sort(aroundBlank[b]);
								fixPos = aroundBlank[b].get(0);
								break loopOut;
							}

						}
					}
				}

			}
		}

		map[fixPos.x][fixPos.y] = personNum;

	}

	public static class Pos implements Comparable<Pos> {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pos o) {

			if (this.x == o.x) {
				return this.y - o.y;
			} else {
				return this.x - o.x;
			}
		}

	}

	public static void printMap() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

}
