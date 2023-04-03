import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9205 {
	static int startX, startY;
	static int endX, endY;
	static ArrayList<Point> mList;
	static int N;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			mList = new ArrayList<>();
			visited = new boolean[N];
			for (int i = 0; i < N + 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				if (i == 0) {
					startX = Integer.parseInt(st.nextToken());
					startY = Integer.parseInt(st.nextToken());
				} else if (i == N + 1) {
					endX = Integer.parseInt(st.nextToken());
					endY = Integer.parseInt(st.nextToken());
				} else {
					int mX = Integer.parseInt(st.nextToken());
					int mY = Integer.parseInt(st.nextToken());
					mList.add(new Point(mX, mY));
				}
			}

			if(BFS()) {
				sb.append("happy").append("\n");
			} else {
				sb.append("sad").append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	private static boolean BFS() {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(startX, startY));

		while (!q.isEmpty()) {
			Point now = q.poll();
			int dis = Math.abs(now.x - endX) + Math.abs(now.y - endY);
			if (dis <= 1000) {
				return true;
			}

			for (int i = 0; i < N; i++) {
				if (!visited[i]) {
					int mX = mList.get(i).x;
					int mY = mList.get(i).y;
					int D = Math.abs(now.x - mX) + Math.abs(now.y - mY);
					if (D <= 1000) {
						visited[i] = true;
						q.add(new Point(mX, mY));
					}
				}
			}

		}

		return false;
	}

	public static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
