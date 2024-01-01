import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1043 {

	static int N, M;
	static boolean[] isTrueman;
	static int[] graphInfo;
	static List<Integer>[] partyInfo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graphInfo = new int[N + 1];

		st = new StringTokenizer(br.readLine());

		isTrueman = new boolean[N + 1];
		int num = Integer.parseInt(st.nextToken());

		int id;
		for (int i = 0; i < num; i++) {
			id = Integer.parseInt(st.nextToken());
			isTrueman[id] = true;
		}

		int partyPerson;
		partyInfo = new ArrayList[M + 1];
		for (int i = 1; i <= M; i++) {
			partyInfo[i] = new ArrayList<>();
		}
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			for (int c = 0; c < cnt; c++) {
				partyPerson = Integer.parseInt(st.nextToken());
				partyInfo[i].add(partyPerson);
			}
		}

		makeSet();
		makeGraph();
	}

	public static void makeSet() {
		for (int i = 1; i <= N; i++) {
			graphInfo[i] = i;
		}
	}

	public static int find(int v) {
		if (graphInfo[v] == v) {
			return v;
		}

		return find(graphInfo[v]);
	}

	public static void makeGraph() {

		for (int i = 1; i <= M; i++) {

			for (int id : partyInfo[i]) {
				
			}
		}
	}
}
