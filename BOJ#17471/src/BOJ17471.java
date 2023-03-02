import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17471 {
	static int[] nums;
	static ArrayList<Integer>[] list;
	static boolean[] visited;
	static int N;
	static int[] area;
	static int minValue = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		nums = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		list = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		// 연결리스트 배열을 선언하여, list[i] 배열리스트에는 i에 연결된 정점 정보 저장
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for (int j = 0; j < n; j++) {
				list[i].add(Integer.parseInt(st.nextToken()));
			}
		}

		area = new int[N + 1];
		dfs(1, 0);
		
		if(minValue == Integer.MAX_VALUE) {
			System.out.println("-1");
		} else {
			System.out.println(minValue);
		}
	}

	private static void dfs(int v, int cnt) {
		if (cnt == N) {

			int sumA = 0;
			int sumB = 0;
			for (int i = 1; i <= N; i++) {
				if (area[i] == 1) {
					sumA += nums[i];
				} else {
					sumB += nums[i];
				}
			}

			visited = new boolean[N + 1];
			int link = 0;
			for (int i = 1; i <= N; i++) {
				if (!visited[i]) {
					bfs(i, area[i]);
					link++;
				}
			}
			
			if(link == 2) {
				minValue = Math.min(minValue, Math.abs(sumA - sumB));
			} 
			
			return;

		}

		area[v] = 1;
		dfs(v + 1, cnt + 1);

		area[v] = 2;
		dfs(v + 1, cnt + 1);

	}

	private static void bfs(int v, int areaNum) {
		Queue<Integer> q = new ArrayDeque<>();
		visited[v] = true;
		q.offer(v);

		while (!q.isEmpty()) {
			int cur = q.poll();

			for (int i = 0; i < list[cur].size(); i++) {
				int next = list[cur].get(i);
				if(!visited[next] && areaNum == area[next]) {
					visited[next] = true;
					q.offer(next);
				}
			}
		}
	}
}
