import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Swea7465 {
	static int[] disjoint;
	static int N, M;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			makeSet();

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				union(A, B);
			}

			Set<Integer> set = new HashSet<>();
			for (int i = 1; i <= N; i++) {
				set.add(find(i));
			}
			sb.append(set.size()).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void makeSet() {
		disjoint = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			disjoint[i] = i;
		}
	}

	private static int find(int i) {
		if (i == disjoint[i]) {
			return i;
		} else {
			return disjoint[i] = find(disjoint[i]);
		}
	}

	private static boolean union(int n1, int n2) {

		int p1 = find(n1);
		int p2 = find(n2);

		if (p1 == p2) {
			return false;
		} else {
			disjoint[p1] = p2;
			return true;
		}
	}

}
