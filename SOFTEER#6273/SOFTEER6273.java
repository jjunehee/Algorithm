import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SOFTEER6273 {

	static int N,M,K;
	static int[] weight;
	static int[] pick;
	static boolean[] visited;
	static int minWork = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 레일 길이
		N = Integer.parseInt(st.nextToken());
		// 바구니 총량
		M = Integer.parseInt(st.nextToken());
		// 일 횟수
		K = Integer.parseInt(st.nextToken());
		
		weight = new int[N];
		pick = new int[N+1];
		visited = new boolean[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
		}
		
		// 순열로 N개의 레일 순서를 뽑는다.
		permutation(0);
		
		System.out.print(minWork);
		
		
	}
	
	
	// 순열로 N개의 레일, 경우의 수를 구한다.
	public static void permutation(int cnt) {
		
		if(cnt==N) {
			// 시뮬레이션으로 최소로 일하는 경우를 구한다.
			minWork = Math.min(simulation(pick), minWork);
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(visited[i]) {
				continue;
			}
			pick[cnt] = weight[i];
			visited[i] = true;
			permutation(cnt+1);
			visited[i] = false;
		}
	}
	
	public static int simulation(int[] weight) {
		
		// 총 일의 양
		int totalSum = 0;
		// 이번 회차의 일
		int sum = 0;
		// 일 수
		int workCnt = 0;
		
		outerLoop:
		while(true) {
			for(int i=0; i<N; i++) {
				
				// 이미 최솟값보다 큰 값이라면 더이상 시뮬레이션 할 필요 없음. 백트래킹
				if(totalSum > minWork) { 
					return Integer.MAX_VALUE;
				}
				
				if(sum + weight[i] <= M) { // 이번 레일을 바구니가 감당 O
					sum += weight[i];
				} else { // 이번 레일은 바구니가 감당 X
					totalSum += sum; // 총 일의 양에 추가
					workCnt++; // 일 수 갱신
					if(workCnt == K) { // 총 일 수에 도달했다면 종료
						break outerLoop;
					}
					sum = weight[i]; // 다음 일의 바구니에 이번 레일의 짐을 넣는다.
				}
			}
		}
		
		return totalSum;
	}
}
