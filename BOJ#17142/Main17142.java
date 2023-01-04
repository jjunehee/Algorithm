import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main17142 {
	static int[][] map;
	static int N, M;
	static ArrayList<Virus> list = new ArrayList<>();
	static Virus[] active;
	static int count;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

<<<<<<< Updated upstream
<<<<<<< Updated upstream
		map = new int[N][N];
		active = new Virus[M];
		for (int i = 0; i < N; i++) {
=======
=======
>>>>>>> Stashed changes
		map = new int[M][M];
		active = new Virus[N];
		for (int i = 0; i < M; i++) {
>>>>>>> Stashed changes
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					list.add(new Virus(i, j));
				}
			}
		}
		pickN(0, 0);
	}

<<<<<<< Updated upstream
<<<<<<< Updated upstream
	private static void pickN(int num, int start) { // DFS
		if (num == M) {
=======
	private static void pickN(int count, int start) { // DFS를 사용해서 N개의 바이러스 조합(Combination) 구하기
		if (count == N) {
>>>>>>> Stashed changes
=======
	private static void pickN(int count, int start) { // DFS를 사용해서 N개의 바이러스 조합(Combination) 구하기
		if (count == N) {
>>>>>>> Stashed changes
			spreadSimulation();
			return;
		}

		for (int i = start; i < list.size(); i++) {
			active[count] = list.get(i); // TODO 이렇게 전역변수로 spreadSimulation에서 사용하도록 하는 것이 과연 맞는 방법일까.
			pickN(count + 1, i + 1);
		}

	}

<<<<<<< Updated upstream
<<<<<<< Updated upstream
	private static void spreadSimulation() {
		// M媛쒖쓽 諛붿씠�윭�뒪 紐⑤몢 �룞�떆�뿉 �띁�졇�빞�븿
		boolean endFlag = false;
		while(!endFlag) {
			
		}
=======
=======
>>>>>>> Stashed changes
	private static void spreadSimulation() { // BFS로 Spread simulation
		// active배열에 Virus 3개 있는 상태.

		
			for (int i = 0; i < active.length; i++) {
				Virus virus = active[i];
				System.out.println(virus.x + " " + virus.y);
			}
		
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
	}

	public static class Virus {
		int x;
		int y;

		public Virus(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
