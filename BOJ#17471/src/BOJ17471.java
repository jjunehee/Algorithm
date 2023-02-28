import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17471 {
	static int[] nums;
	static ArrayList<Integer>[] list;
	static int total;
	static int[] pick;
	static boolean[] visited;
	static boolean[] tmpvisited;
	static int N;
	static int countBlue;
	static boolean flag;
	static int minValue = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		nums = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			total += nums[i];
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

		// 조합을 통해서 빨간색(A) 구역 뽑기
		for (int cnt = 1; cnt < N; cnt++) { // 1개 뽑는 경우부터 N-1개 뽑는 경우까지

			for (int startVertex = 1; startVertex <= N; startVertex++) { // 시작 구역(정점) 지정.
				
				pick = new int[cnt]; // cnt 만큼 뽑아서 넣어줄 배열 선언
				visited = new boolean[N + 1]; // 방문처리 배열 선언
				
				selectA(startVertex, 0, cnt);
				
			}
		}
		
		System.out.println(minValue == Integer.MAX_VALUE ? -1 : minValue); // 구역이 한번도 나뉜적이 없었다면, 초기화 값 그대로이므로 -1 출력 아니면 최소값 출력

	}

	private static void selectA(int v, int cnt, int endCount) {
		if (cnt == endCount) {
			
			if (test(endCount)) { // 구역이 둘로 잘 나눠지는 지 확인하는 test 메소드
				calculate(); // 구역이 잘 나눠졌으므로 빨간구역, 파란구역 계산 및 차이값 계산
			}
			return;
			
		}

		for (int i = 0; i < list[v].size(); i++) { // 해당 정점에 연결된 정점들 탐색
			
			if (visited[list[v].get(i)]) // 연결된 정점 중 방문한 적이 있는 정점이라면 continue;
				continue;
			
			pick[cnt] = list[v].get(i); // 방문한 적이 없다면 pick에 넣기
			visited[list[v].get(i)] = true; // 방문 처리
			selectA(list[v].get(i), cnt + 1, endCount);
		}

	}

	private static boolean test(int Count) {

		int check = N - Count; // 빨간 구역 뽑고 나고 남은 개수, 즉 파란 구역 개수
		countBlue = 0;
		flag = false; // 파란구역이 모두 연결되었는지 여부 파악 flag
		
		tmpvisited = new boolean[N+1];
		tmpvisited = visited.clone(); // 원본 visit 배열 유지해주기 위해 tmpvisit 선언
		
		for (int v = 1; v <= N; v++) {
			if (!visited[v]) { // 빨간 구역이 방문한 적이 없다면~ (즉, 파란 구역)
				tmpvisited[v] = true;
				dfs(v); // 연결 여부를 파악하기 위해 dfs로 들어간다.
				break;
			}
		}
		if (check == countBlue) { // dfs를 통해 count한 값(countBlue)와 N-빨간구역을 비교한다. 같다는 것은 파란 구역이 모두 잘 연결되어있다는 의미
			return true;
		} else {
			return false;
		}

	}

	private static void dfs(int v) {

		countBlue++;

		for (int i = 0; i < list[v].size(); i++) {
			if (tmpvisited[list[v].get(i)]) {
				continue;
			}
			tmpvisited[list[v].get(i)] = true;
			dfs(list[v].get(i));
		}

	}
	
	private static void calculate() {
		int sumA = 0;
		for (int i = 0; i < pick.length; i++) {
			sumA += nums[pick[i]];
		}
		int sumB = total - sumA;

		minValue = Math.min(minValue, Math.abs(sumA - sumB));
	}
}
