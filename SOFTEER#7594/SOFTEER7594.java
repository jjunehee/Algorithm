import java.util.*;
import java.io.*;

public class SOFTEER7594 {
	
	static int N;
	static int[][] map;
	static int max = Integer.MIN_VALUE;
	static boolean[][] visited;
	static int[] dx = {1,0};
	static int[] dy = {0,1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// map의 크기
		N = Integer.parseInt(br.readLine());
		
		// input
		map = new int[N][N];
		visited = new boolean[N][N];
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ret = 0;
		if(N==2) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					ret += map[i][j];
				}
			}
		} else {
			pickTreeGroup(0, 0);
			ret = max;
		}
		
		System.out.print(ret);
	}
	
	public static void pickTreeGroup(int idx, int cnt) {
		
		if(cnt == 4) {
			max = Math.max(max, calculateSum());
			return;
		}
		
		if(idx >= N*N) {
			return;
		}
		
		
		for(int i = idx; i< N*N; i++) {
			
			int x = i / N;
			int y = i % N;
				
			if(visited[x][y]) {
				continue;
			}
			
			int nx, ny;
			for(int dir=0; dir<2; dir++) {
				nx = x + dx[dir];
				ny = y + dy[dir];
				
				if(isBound(nx,ny) || visited[nx][ny]) {
					continue;
				}
				
				visited[x][y] = true;
				visited[nx][ny] = true;
				pickTreeGroup(x*N + y + 1, cnt+1);
				visited[x][y] = false;
				visited[nx][ny] = false;
			}
				
		}
		
	}

	public static int calculateSum() {
		
		int sum = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(visited[i][j]) {
					sum += map[i][j];
				}
			}
		}
		
		
		return sum;
	}
	
	public static boolean isBound(int nx, int ny) {
		if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
			return true;
		} else {
			return false;
		}
	}
}




