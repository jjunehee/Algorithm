import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ5214 {

	static int N, K, M;
	static List<Integer>[] htInfo;
	static List<Integer>[] vInfo;
	static boolean[][] graphInfo;
	static int[] pick = new int[2];
	static int ret = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		htInfo = new ArrayList[M + 1];
		vInfo = new ArrayList[N + 1];
		for (int i = 1; i <= M; i++) {
			htInfo[i] = new ArrayList<>();
		}
		for (int i = 1; i <= N; i++) {
			vInfo[i] = new ArrayList<>();
		}
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < K; j++) {
				int num = Integer.parseInt(st.nextToken());
				htInfo[i].add(num);
				vInfo[num].add(i);
			}
		}

		System.out.println(solution());

	}

	public static int solution() {
		
		if(N==1) {
			return 1;
		}

		Queue<checkPoint> q = new LinkedList<>();
		q.add(new checkPoint(1, 1));
		
		
		boolean[] visitTube = new boolean[M + 1];
		boolean[] visitStation = new boolean[N+1];
		
		visitStation[1] = true;

		while (!q.isEmpty()) {
			checkPoint now = q.poll();
			if (now.v == N) {
				ret = now.dist;
				break;
			}

			for (int hyperTube : vInfo[now.v]) {

				if(visitTube[hyperTube]) {
					continue;
				}
				visitTube[hyperTube] = true;
				for (int v : htInfo[hyperTube]) {
					if (visitStation[v]) {
						continue;
					}

					visitStation[v] = true;
					q.add(new checkPoint(v, now.dist + 1));
				}
			}
		}

		if (ret == Integer.MAX_VALUE) {
			return -1;
		} else {
			return ret;
		}

	}

	public static class checkPoint {
		int v;
		int dist;

		public checkPoint(int v, int dist) {
			this.v = v;
			this.dist = dist;
		}
	}
}
