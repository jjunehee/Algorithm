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
	private static void pickN(int count, int start) { // DFS�� ����ؼ� N���� ���̷��� ����(Combination) ���ϱ�
		if (count == N) {
>>>>>>> Stashed changes
=======
	private static void pickN(int count, int start) { // DFS�� ����ؼ� N���� ���̷��� ����(Combination) ���ϱ�
		if (count == N) {
>>>>>>> Stashed changes
			spreadSimulation();
			return;
		}

		for (int i = start; i < list.size(); i++) {
			active[count] = list.get(i); // TODO �̷��� ���������� spreadSimulation���� ����ϵ��� �ϴ� ���� ���� �´� ����ϱ�.
			pickN(count + 1, i + 1);
		}

	}

<<<<<<< Updated upstream
<<<<<<< Updated upstream
	private static void spreadSimulation() {
		// M개의 바이러스 모두 동시에 퍼져야함
		boolean endFlag = false;
		while(!endFlag) {
			
		}
=======
=======
>>>>>>> Stashed changes
	private static void spreadSimulation() { // BFS�� Spread simulation
		// active�迭�� Virus 3�� �ִ� ����.

		
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
