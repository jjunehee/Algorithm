import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ17143 {

	static ArrayList<Point> sharkList = new ArrayList<>();
	static Point[][] map;
	static int R, C, M;
	static int ret;
	static int[] dx = { 0, -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new Point[R + 1][C + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			Point shark = new Point(r, c, s, d, z);
			map[r][c] = shark;
			sharkList.add(shark);
		}

		simulation();
		System.out.println(ret);
	}

	private static void simulation() {

		int personPos = 0;
		while (personPos <= C) {
			
			personPos++;
			if (personPos > C || sharkList.isEmpty()) { // 상어가 아예 없으면 갈 필요가 없지
				break;
			}

			hunt(personPos); // 사람이 지금 있는 위치에서 낚시 진행
			sharkMove(); // 상어 움직이기
		}

	}

	private static void hunt(int huntColumn) {

		for (int i = 1; i <= R; i++) {
			if (map[i][huntColumn] != null) {
				ret += map[i][huntColumn].z; // 잡은 상어의 크기 ++
				sharkList.remove(map[i][huntColumn]); // 그 상어를 리스트에서 지워줌
				map[i][huntColumn] = null; // 맵에서 지워줌
				break;
			}
		}

	}

	private static void sharkMove() {

		ArrayList<Point> removeList = new ArrayList<>();
		Point[][] moveMap = new Point[R + 1][C + 1];

		for (int i = 0; i < sharkList.size(); i++) { // 상어들이 이제 움직인다.
			Point shark = sharkList.get(i);

			int X = shark.d <= 2 ? R : C;
			int speed = shark.s % ((X - 1) * 2);
			for (int k = 0; k < speed; k++) { // speed 만큼만 움직이면 아무리 많이 움직여도 같은 방향 같은 위치이다.

				int nx = shark.r + dx[shark.d];
				int ny = shark.c + dy[shark.d];

				if (nx < 1) { // 위로 넘어가면
					shark.d = 2;
					nx = shark.r + dx[shark.d];
					ny = shark.c + dy[shark.d];
				} else if (nx > R) { // 아래로 넘어가면
					shark.d = 1;
					nx = shark.r + dx[shark.d];
					ny = shark.c + dy[shark.d];
				} else if (ny < 1) { // 왼쪽 넘어가면
					shark.d = 3;
					nx = shark.r + dx[shark.d];
					ny = shark.c + dy[shark.d];
				} else if (ny > C) { // 오른쪽 넘어가면
					shark.d = 4;
					nx = shark.r + dx[shark.d];
					ny = shark.c + dy[shark.d];
				}

				shark.r = nx;
				shark.c = ny;
			}


			if (moveMap[shark.r][shark.c] != null) { // 도착한 자리에 다른 상어가 있는 경우
				if (moveMap[shark.r][shark.c].z < shark.z) {
					removeList.add(moveMap[shark.r][shark.c]); // 기존에 있던 상어 먹어치워버리기~
					moveMap[shark.r][shark.c] = shark; // 새로운 상어 입력
				} else { // 기존에 있던 상어가 사이즈가 더 컸다면
					removeList.add(shark); // 상어 먹히기~
				}
			} else { // 도착한 자리에 상어가 없는 경우
				moveMap[shark.r][shark.c] = shark; // 그냥 상어 입력~
			}

		}
		
		map = moveMap;

		for (Point shark : removeList) {
			sharkList.remove(shark);
		}
		
	}

	public static class Point {
		int r, c, s, d, z;

		public Point(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
}