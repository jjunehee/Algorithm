import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// Z
public class Main1074 {

	static int[][] map;
	static int N, r, c;
	static int ret;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		int n = (int) Math.pow(2, N);

		int ret = divide2(0, 0, n); 

		bw.write(ret + "\n");
		bw.flush();
	}

//	private static void divide(int start, int end, int size, int cnt) {
//
//		// 기저 조건으로 끝까지 들어갔을때 map에 cnt 시작.
//		// 그 조건이 뭘까, 끝까지 나누어졌을때, 특징.
//		// N에 따라서 달라지는 거 같다.
//		// N 이 3일때, 결국에는 3번 divide 된다.
//		if (cnt == N) { // 모두 다 divide된 상황
//			map[start][end] = ret++;
//			return;
//		}
//
//		int half = size / 2;
//		divide(start, end, half, cnt + 1);
//		divide(start, end + half, half, cnt + 1);
//		divide(start + half, end, half, cnt + 1);
//		divide(start + half, end + half, half, cnt + 1);
//
//	}

	private static int divide2(int startX, int startY, int size) {

		// r,c로만 재귀로 들어갈 것이다.
		if (size == 1) {
			return 0;
		}

		int half = size / 2;

		if (r < startX + half && c < startY + half) {
			return divide2(startX, startY, half);
		} else if(r < startX + half && c >= startY + half) {
			return divide2(startX, startY + half, half) + (int) Math.pow(half, 2);
		} else if(r >= startX + half && c < startY + half) {
			return divide2(startX + half, startY, half) + (int) Math.pow(half, 2)*2;
		} else {
			return divide2(startX + half, startY + half, half) + (int) Math.pow(half, 2)*3;
		}

	}
}
