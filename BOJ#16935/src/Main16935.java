import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main16935 {
	static int[][] map;
	static int N, M;
	static int[][] map3;
	static int[][] map4;
	static int[][] temp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int Rnum = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Rnum; i++) {
			int R = Integer.parseInt(st.nextToken());
			switch (R) {
			case 1:
				cal1();
				break;
			case 2:
				cal2();
				break;
			case 3:
				cal3();
				break;
			case 4:
				cal4();
				break;
			case 5:
				cal5();
				break;
			case 6:
				cal6();
				break;
			}
		}
		System.out.println();
//		for (int[] i : map) {
//			for (int j : i) {
//				System.out.print(j + " ");
//			}
//			System.out.println();
//		}
	}

	public static void cal1() {
		int R = N - 1;
		for (int i = 0; i < N / 2; i++, R -= 2) {
			for (int j = 0; j < M; j++) {
				int tmp = map[i + R][j];
				map[i + R][j] = map[i][j];
				map[i][j] = tmp;
			}
		}
	}

	public static void cal2() {

		for (int i = 0; i < N; i++) {
			int C = M - 1;
			for (int j = 0; j < M / 2; j++, C -= 2) {
				int tmp = map[i][j + C];
				map[i][j + C] = map[i][j];
				map[i][j] = tmp;
			}
		}
	}

	public static void cal3() {
		M = map[0].length;
		N = map.length;
		map3 = new int[M][N];
		Queue<Integer> q = new LinkedList<>();
		for (int j = 0; j < M; j++) {
			for (int i = N - 1; i >= 0; i--) {
				q.add(map[i][j]);
			}
		}

		for (int i = 0; i < M; i++) {
			for (int j = 0; i < N; i++) {
				map3[i][j] = q.poll();
			}
		}
		for (int[] i : map3) {
			for (int j : i) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
//		while (!q.isEmpty()) {
//			for (int i = 0; i < N; i++) {
//				System.out.print(q.poll() + " ");
//			}
//			System.out.println();
//		}
		map = map3;
	}

	public static void cal4() {
		for (int[] i : map) {
			for (int j : i) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
		
		map4 = new int[M][N];
		Queue<Integer> q = new LinkedList<>();
		for (int j = M - 1; j >= 0; j--) {
			for (int i = 0; i < N; i++) {
				q.add(map[i][j]);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; i < M; i++) {
				map4[i][j] = q.poll();
			}
		}
		for (int[] i : map4) {
			for (int j : i) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
//		while (!q.isEmpty()) {
//			for (int i = 0; i < N; i++) {
//				System.out.print(q.poll() + " ");
//			}
//			System.out.println();
//		}
		map = map4;
	}

	public static void cal5() {
		temp = new int[N][M];

		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M / 2; j++) {
				temp[i][M / 2 + j] = map[i][j];
			}
		}

		for (int i = 0; i < N / 2; i++) {
			for (int j = M / 2; j < M; j++) {
				temp[N / 2 + i][j] = map[i][j];
			}
		}

		for (int i = N / 2; i < N; i++) {
			for (int j = M / 2; j < M; j++) {
				temp[i][j - M / 2] = map[i][j];
			}
		}

		for (int i = N / 2; i < N; i++) {
			for (int j = 0; j < M / 2; j++) {
				temp[i - N / 2][j] = map[i][j];
			}
		}
		map = temp;
	}

	public static void cal6() {
		temp = new int[N][M];

		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M / 2; j++) {
				temp[N / 2 + i][j] = map[i][j];
			}
		}

		for (int i = 0; i < N / 2; i++) {
			for (int j = M / 2; j < M; j++) {
				temp[i][j - M / 2] = map[i][j];
			}
		}

		for (int i = N / 2; i < N; i++) {
			for (int j = M / 2; j < M; j++) {
				temp[i - N / 2][j] = map[i][j];
			}
		}

		for (int i = N / 2; i < N; i++) {
			for (int j = 0; j < M / 2; j++) {
				temp[i][j + M / 2] = map[i][j];
			}
		}

		map = temp;
	}
}
