import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class aProblemApple {

	static int N;

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int curDir;
	static int rotateSum;
	static Pos now;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			N = Integer.parseInt(br.readLine());

			Pos[] apples = new Pos[11];
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					if (num > 0) { // 사과를 의미
						apples[num] = new Pos(i,j);
						cnt++;
					}
				}
			}

			now = new Pos(0, 0);
			curDir = 0;
			rotateSum = 0;
			for (int i = 1; i <= cnt; i++) {
				int rotate = getMinRotateCnt(apples[i]);
				rotateSum += rotate;
				now.x = apples[i].x;
				now.y = apples[i].y;
				curDir = (curDir + rotate) % 4;
			}
			sb.append(rotateSum).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static int getMinRotateCnt(Pos apple) {

		int deltax = dx[curDir];
		int deltay = dy[curDir];
		int diffX = apple.x - now.x;
		int diffY = apple.y - now.y;

		if (deltax * diffY - deltay * diffX > 0) {
			return 3;
		}

		if (deltax != 0) {
			return deltax * diffX > 0 ? 1 : 2;
		} else {
			return deltay * diffY > 0 ? 1 : 2;
		}

	}

	public static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
