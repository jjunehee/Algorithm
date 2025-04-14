import java.util.*;
import java.io.*;


public class SOFTEER7594 {

	static int N;
	static List<Tree> treeList = new ArrayList<>();
	static Tree[] pickTree = new Tree[8];
	
	static int max = Integer.MIN_VALUE;
	static int test = 0;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,-1,0,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			 st = new StringTokenizer(br.readLine());
			 for(int j=0; j<N; j++) {
				 int value = Integer.parseInt(st.nextToken());
				 treeList.add(new Tree(i,j,value)); // 나무 정보 리스트에 입력
			 }
		}
		
		if(N==2) {
			max = 0;
			for(int i=0; i<treeList.size(); i++) {
				max += treeList.get(i).value;
			}
		} else {
			comb(0,0);
		}
		
		System.out.print(max);
		
		
	}
	
	public static void comb(int idx, int cnt) {
		if(cnt == 8) {
			max = Math.max(max, calculate(pickTree));
			return;
		}
		
		
		for(int i=idx; i<treeList.size(); i++) {
			pickTree[cnt] = treeList.get(i);
			comb(i+1, cnt+1);
		}
	}
	
	public static int calculate(Tree[] pickTree) {
		
		if(!checkGroup(pickTree)) {
			return -1;
		}
		
		int sum = 0;
		for(int i=0; i<8; i++) {
			sum += pickTree[i].value;
		}
		return sum;
	}
	
	public static boolean checkGroup(Tree[] pickTree) {
		
		boolean[][] pickTreeCheck = new boolean[N][N];
		for(Tree tree : pickTree) {
			pickTreeCheck[tree.x][tree.y] = true;
		}
		
		boolean[][] visited = new boolean[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j] && pickTreeCheck[i][j]) {
					
					Queue<Pos> q = new LinkedList<>();
					visited[i][j] = true;
					q.add(new Pos(i,j));
					int cnt = 1;
					
					while(!q.isEmpty()) {
						Pos now = q.poll();
						int nx, ny;
						for(int dir=0; dir<4; dir++) {
							nx = now.x + dx[dir];
							ny = now.y + dy[dir];
							if(isBound(nx,ny) || visited[nx][ny] ) {
								continue;
							}
							
							if(pickTreeCheck[nx][ny]) {
								visited[nx][ny] = true;
								q.add(new Pos(nx,ny));
								cnt++;
							}
						}
					}
					
					if(cnt % 2 != 0) {
						return false;
					}
				}
			}
		}
		
		return true;
		
	}
	
	public static boolean isBound(int nx, int ny) {
		if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
			return true;
		} else {
			return false;
		}
	}
	
	public static class Pos {
		int x;
		int y;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static class Tree {
		int x;
		int y;
		int value;
		public Tree(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}
	}
}


