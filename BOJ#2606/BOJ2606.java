import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2606 {

	static int N, M;
	static List<Integer>[] info;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		info = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			info[i] = new ArrayList<>();
		}

		int v1, v2;
		StringTokenizer st;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			v1 = Integer.parseInt(st.nextToken());
			v2 = Integer.parseInt(st.nextToken());
			info[v1].add(v2);
			info[v2].add(v1);
		}
		
		System.out.println(solution());
		
	}
	
	public static int solution() {
		boolean[] visited = new boolean[N+1];
		
		Queue<Integer> q = new LinkedList<>();
		visited[1] = true;
		q.add(1);
		
		int ret = 0;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for(int num : info[now]) {
				if(visited[num]) {
					continue;
				}
				
				visited[num] = true;
				ret++;
				q.add(num);
			}
		}
		
		return ret;
	}
}
