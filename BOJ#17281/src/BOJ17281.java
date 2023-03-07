import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		
		visited[1] = true;
		order[4] = 1;

		permu(1);
		System.out.println(maxScore);

	}

	private static void permu(int cnt) {
		if (cnt == 10) {

			int score = gameStart();

			maxScore = Math.max(score, maxScore);

			return;
		}

		
		if (cnt == 4) {
			permu(5);
			return;
		}
		
		
		for (int i = 1; i <= 9; i++) {
			if (visited[i])
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
		while (innings <= N) { // 게임 시작

			int outCount = 0;
			
			int[] base = new int[4];
			while (outCount <= 2) {

				cur = order[curOrder]; // 해당 이닝 선수 입장
				switch (board[innings][cur]) {
				case 1: // 안타
					score += base[3];
					base[3] = base[2];
					base[2] = base[1];
					base[1] = 1;
					break;

				case 2: // 2루타
					score += base[3]+base[2];
					
					base[3] = base[1];
					base[2] = 1;
					base[1] = 0;
					break;

				case 3: // 3루타
					score += base[3]+base[2]+base[1];
					base[3] = 1;
					base[2] = 0;
					base[1] = 0;
					break;

				case 4: // 홈런
					score += base[3]+base[2]+base[1]+1;
					base[3] = 0;
					base[2] = 0;
					base[1] = 0;
					break;

				case 0: // 아웃
					outCount++;
					break;

				}
				curOrder = curOrder % 9 + 1;

			}
			innings++;
		}
		return score;
	}
}
