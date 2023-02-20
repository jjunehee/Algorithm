import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Z
public class Main1074 {

	static int[][] map;
	static int N, r, c;
	static int count;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		int n = (int) Math.pow(2, N);
		map = new int[n][n];

		divide(0, 0, n, 0);

		for (int i = 0; i < Math.pow(2, N); i++) {

			for (int j = 0; j < Math.pow(2, N); j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println(map[r][c]);
	}

	private static void divide(int start, int end, int size, int cnt) {

		// 기저 조건으로 끝까지 들어갔을때 map에 cnt 시작.
		// 그 조건이 뭘까, 끝까지 나누어졌을때, 특징.
		// N에 따라서 달라지는 거 같다.
		// N 이 3일때, 결국에는 3번 divide 된다.
		if (cnt == N) { // 모두 다 divide된 상황
			map[start][end] = count++;
			return;
		}

		int half = size / 2;
		divide(start, end, half, cnt + 1);
		divide(start, end + half, half, cnt + 1);
		divide(start + half, end, half, cnt + 1);
		divide(start + half, end + half, half, cnt + 1);

	}
}
