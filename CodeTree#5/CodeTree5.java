import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CodeTree5 {

	static int N, M, K;
	static Pos[][] map;

	static Queue<Person>[][] personList;
	static Pos exit;
	static int goal;
	static int result;

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new Pos[N][N];

		personList = new LinkedList[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = new Pos(i, j, num, false, false);
				personList[i][j] = new LinkedList<>();
			}
		}

		int x, y;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken()) - 1;
			y = Integer.parseInt(st.nextToken()) - 1;
			map[x][y].isPerson = true;
			personList[x][y].add(new Person(x, y));
		}

		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken()) - 1;
		y = Integer.parseInt(st.nextToken()) - 1;
		exit = new Pos(x, y);
		map[x][y].isExit = true;

		simulation();
		System.out.println(result);
		exit.x += 1;
		exit.y += 1;
		System.out.println(exit.x + " " + exit.y);

	}

	public static void simulation() {

		int time = 0;
		while (time < K) {

			System.out.println("출구 위치 : " + exit.x + " " + exit.y);
			move();
			if (goal == M) {
				break;
			}
			printMap();
			rotate();
			printMap();
			printPerson();

			time++;
		}
	}

	public static void move() {

		int[][] sizeInfo = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sizeInfo[i][j] = personList[i][j].size();
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j].isPerson) {

					for (int k = 0; k < sizeInfo[i][j]; k++) {

						Person person = personList[i][j].poll();

						int nx, ny;
						for (int dir = 0; dir < 4; dir++) {
							nx = person.x + dx[dir];
							ny = person.y + dy[dir];

							if (checkBound(nx, ny)) {
								continue;
							}

							if (map[nx][ny].durability == 0 && checkDist(person.x, person.y, nx, ny)) { // 그곳이 빈칸이고, 거리가
																										// 짧아지는 곳이라면
																										// 이동 가능
								System.out.println("여기서 : " + person.x + " " + person.y);
								map[person.x][person.y].isPerson = false;
								person.x = nx;
								person.y = ny;
								System.out.println("여기로이동!!" + person.x + " " + person.y);
								result++;
								if (exit.x == person.x && exit.y == person.y) { // 이동한 그곳이 출구가 있는 곳이라면
									System.out.println("골~!!");
									goal++;
									break;
								}
								map[person.x][person.y].isPerson = true;
								personList[person.x][person.y].add(person); // 이동 했던 안했던 personList에 다시 추가
								break;
							}
						}
					}

				}
			}
		}
	}

	public static void rotate() {

		// 정사각형 정하기
		outerloop: for (int size = 2; size <= N; size++) {

			for (int i = 0; i <= N - size; i++) {
				for (int j = 0; j <= N - size; j++) {
					if (searchRec(size, i, j)) {
						System.out.println("찾았다." + i + " " + j + " " + size);
						// 회전 , 내구도 낮추기
						rotateRec(i, j, size);

						break outerloop;
					}
				}
			}

		}

	}

	public static void rotateRec(int x, int y, int size) {

		Queue<Pos>[][] tmp = new LinkedList[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				tmp[i][j] = new LinkedList<>();
			}
		}

		for (int i = x, a = 0; i < x + size && a < size; i++, a++) {
			for (int j = y, b = 0; j < y + size && b < size; j++, b++) {
				while (!map[i][j].isEmpty()) {
					tmp[a][b].add(map[i][j].poll());
				}

			}

		}

		for (int j = y + size - 1; j >= y; j--) {
			for (int i = x; i < x + size; i++) {
				map[i][j] = tmp.poll();
				if (map[i][j].durability > 0) {
					map[i][j].durability--;
				}

				if (map[i][j].isExit) {
					exit.x = i;
					exit.y = j;
				}
			}
		}

		while (!personList.isEmpty()) {
			personList.poll();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j].isPerson) {
					personList.add(new Person(i, j));
				}
			}
		}

	}

	public static boolean searchRec(int size, int x, int y) {

		boolean isPerson = false;
		boolean isExit = false;

		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (map[i][j].isPerson) {
					System.out.println(i + " " + j);
					isPerson = true;
				}
				if (map[i][j].isExit) {
					System.out.println(i + " " + j);
					isExit = true;
				}
			}
		}

		if (isPerson && isExit) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean checkBound(int nx, int ny) {

		if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
			return true;
		}

		return false;
	}

	public static boolean checkDist(int x, int y, int nx, int ny) {
		int nowDist = Math.abs(exit.x - x) + Math.abs(exit.y - y);

		int nextDist = Math.abs(exit.x - nx) + Math.abs(exit.y - ny);

		if (nowDist > nextDist) {
			return true;
		} else {
			return false;
		}
	}

	public static void printMap() {
		System.out.println("==============");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j].durability + " ");
			}
			System.out.println();
		}
	}

	public static void printPerson() {

		System.out.println("=========");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j].isPerson) {
					System.out.print("T ");
				} else {
					System.out.print("0 ");
				}
			}
			System.out.println();
		}
	}

	public static class Person {
		int x, y;

		public Person(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static class Pos {
		int x, y;
		int durability;
		boolean isPerson;
		boolean isExit;

		public Pos(int x, int y, int durability, boolean isPerson, boolean isExit) {
			this.x = x;
			this.y = y;
			this.durability = durability;
			this.isPerson = isPerson;
			this.isExit = isExit;
		}

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
