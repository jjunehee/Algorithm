import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17281 {
	static int[][] board;
	static int[] order;
	static boolean[] visited;
	static int N;
	static int maxScore = Integer.MIN_VALUE;
	static int cc;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		board = new int[N + 1][10];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 9; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visited = new boolean[10];
		order = new int[10];

		permu(1);
		System.out.println(maxScore);

	}

	private static void permu(int cnt) {
		if (cnt == 10) {

//			for (int i = 1; i < order.length; i++) {
//				System.out.print(order[i] + " ");
//			}
//			System.out.println();
//			System.out.println("============");
//			System.out.println("=================");
			if(order[1] == 5 && order[2] == 6 && order[3] == 7) {
				int score = gameStart();
				cc++;
				System.out.println("===========");
			}
			
			return;
		}

		for (int i = 1; i <= 9; i++) {
			if (cnt == 4) {
				order[cnt] = 1;
				permu(cnt + 1);
				continue;
			}

			if (i == 1 || visited[i])
				continue;

			order[cnt] = i;
			visited[i] = true;
			permu(cnt + 1);
			visited[i] = false;
		}

	}

	private static int gameStart() {

		int innings = 1;
		int curOrder = 1;
		int cur;
		int score = 0;
		for (int i = 1; i < order.length; i++) {
			System.out.print(order[i] + " ");
		}
		System.out.println();
		System.out.println("============start");
		while (innings <= N) { // 게임 시작

			int outCount = 0;
			System.out.println("현재 이닝" + innings);

			while (outCount <= 2) {
				Queue<Integer> q = new ArrayDeque<>();
				q.add(0);
				q.add(0);
				q.add(0);
				cur = order[curOrder]; // 해당 이닝 선수 입장
				System.out.println("선수" + cur + " 이닝정보 " + board[innings][cur]);
				switch (board[innings][cur]) {
				case 1: // 안타
					System.out.println("t");
					for (int i = 0; i < 1; i++) {
						if (q.poll() != 0) {
							score++;
						}
					}
					System.out.println(cur + "들어감");
					q.add(cur);
					System.out.println("추가 후 사이즈 " + q.size());
					break;

				case 2: // 2루타
					for (int i = 0; i < 2; i++) {
						if (q.poll() != 0) {
							score++;
						}
					}
					q.add(cur);
					q.add(0);
					break;

				case 3: // 3루타
					for (int i = 0; i < 3; i++) {
						if (q.poll() != 0) {
							score++;
						}
					}
					q.add(cur);
					q.add(0);
					q.add(0);
					break;

				case 4: // 홈런
					System.out.println("홈런!");
					System.out.println(q.size() + " 사이즈");
					for(Integer num : q) {
						System.out.print(num + " ");
					}
					System.out.println();
					while (!q.isEmpty()) {
						if (q.poll() != 0) {
							score++;
							System.out.println("득점");
						}
					}
					q.add(0);
					q.add(0);
					q.add(cur);
					score++;
					break;

				case 0: // 아웃
					outCount++;
					break;

				}
				System.out.println("next");
				System.out.println(q.size() + " 사이즈");
				curOrder = (curOrder + 1) % 9;
//				System.out.println(curOrder);

			}

			innings++;
		}
//		System.out.println(score);
		return score;

	}

}
