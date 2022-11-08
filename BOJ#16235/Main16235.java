

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main16235 {
	public static int N, M, K;
	public static int[][] A;
	static LinkedList<Tree> treeList = new LinkedList<>();
	static Queue<Tree> deadList = new LinkedList<>();
	public static int[][] ground;
	public static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
	public static int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };
	public static int count = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		A = new int[N + 1][N + 1];
		ground = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int a = Integer.parseInt(st.nextToken());
				A[i][j] = a;
				ground[i][j] = 5;
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			treeList.add(new Tree(x, y, z));
		}
		Collections.sort(treeList, (t1, t2) -> t1.age - t2.age);
		solution();

	}

	private static void solution() {
		int year = 0;
		while (year < K) {

			spring();
			summer();
			fall();
			winter();

			year++;
		}
		System.out.println(treeList.size());
	}

	private static void spring() {
		Iterator<Tree> iterator = treeList.iterator();

		while (iterator.hasNext()) {
			Tree tree = iterator.next();

			if (ground[tree.x][tree.y] < tree.age) {
				deadList.offer(tree);
				iterator.remove();
				continue;
			}
			ground[tree.x][tree.y] -= tree.age;
			tree.age++;
		}
	}

	private static void summer() {
		while (!deadList.isEmpty()) {
			Tree tree = deadList.poll();
			ground[tree.x][tree.y] += tree.age / 2;
		}
	}

	private static void fall() {
		ArrayList<Tree> newTrees = new ArrayList<>();

		for (Tree tree : treeList) {

			if (tree.age % 5 == 0) {
				for (int dir = 0; dir < 8; dir++) {
					int nx = tree.x + dx[dir];
					int ny = tree.y + dy[dir];
					if (nx >= 1 && nx <= N && ny >= 1 && ny <= N) {
						newTrees.add(new Tree(nx, ny, 1));
					}
				}
			}

		}
		treeList.addAll(0, newTrees);
	}

	private static void winter() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				ground[i][j] += A[i][j];
			}
		}
	}

	public static class Tree {
		int x;
		int y;
		int age;

		public Tree(int x, int y, int age) {
			this.x = x;
			this.y = y;
			this.age = age;
		}
	}
}
