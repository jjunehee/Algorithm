import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ5014 {

	static int F, S, G, U, D;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		visited = new boolean[F + 1];

		Queue<Pos> q = new LinkedList<>();
		// 시작지점
		visited[S] = true;
		q.add(new Pos(S, 0));

		boolean flag = false;
		while (!q.isEmpty()) {
			Pos now = q.poll();

			if (now.locate == G) {
				sb.append(now.dist);
				flag = true;
				break;
			}

			if (now.locate + U <= F && !visited[now.locate + U]) {
				visited[now.locate + U] = true;
				q.add(new Pos(now.locate + U, now.dist + 1));
			}

			if (now.locate - D >= 1 && !visited[now.locate - D]) {
				visited[now.locate - D] = true;
				q.add(new Pos(now.locate - D, now.dist + 1));
			}
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		if (flag) {
			bw.write(sb.toString());
		} else {
			bw.write("use the stairs");
		}

		bw.flush();

	}

	public static class Pos {
		int locate;
		int dist;

		public Pos(int locate, int dist) {
			this.locate = locate;
			this.dist = dist;
		}
	}
}
