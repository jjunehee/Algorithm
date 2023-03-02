import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ17135 {
	static int[][] map;
	static int[][] copyMap;
	static int[] pick; // 조합을 통해 궁수 위치를 지정해줄 배열
	static int N, M, D; // 맵 크기 및 궁수 사정거리
	static int[] dx = { 0, -1, 0 }; // 왼쪽 위쪽 오른쪽
	static int[] dy = { -1, 0, 1 };
	static boolean[][] visited; // bfs 방문 처리를 위한 방문배열
	static int score;
	static int maxScore;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		// map 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 궁수 위치를 뽑아줄 pick배열
		pick = new int[3];
		comb(0, 0);

		System.out.println(maxScore);

	}

	private static void comb(int cnt, int idx) {
		if (cnt == 3) { // 궁수 위치 3개가 뽑아진다면 start

			
			//원본 map 유지하기 위해 copyMap에 복사
			copyMap = new int[N][M];
			for (int i = 0; i < map.length; i++) {
				copyMap[i] = map[i].clone();
			}
			
			score = 0;
			for (int i = 0; i < N; i++) { // 적들이 모두 내려가려면 N번 만큼 내려가면 적 다 사라지므로 N번
				attack(); // 궁수 공격
				move(); // 적들 아래로 이동
			}
			maxScore = Math.max(score, maxScore); // 매 조합마다 score 최대값 갱신
			return;
		}

		for (int i = idx; i < M; i++) {
			pick[cnt] = i;
			comb(cnt + 1, i + 1);
		}
	}

	private static void move() {
		for (int i = N - 1; i > 0; i--) {
			for (int j = 0; j < M; j++) {
				copyMap[i][j] = copyMap[i - 1][j];
			}
		}
		Arrays.fill(copyMap[0], 0);
	}

	private static void attack() {


		Set<Pos> target = new HashSet<>(); // 궁수의 타깃이 될 target 집합 ( 타깃이 같을 수 있기때문에 Set으로 설정)

		for (int i = 0; i < 3; i++) { // pick된 궁수 3명에 대해서 공격 3번 진행
			
			Queue<Pos> acher = new ArrayDeque<>();
			acher.add(new Pos(N, pick[i])); // 궁수위치 추가

			visited = new boolean[N][M];
			int distance = 1;
			while (!acher.isEmpty() && distance <= D) { // 해당 궁수 위치에서 bfs로 가능한 범위 탐색
				
				Pos cur = acher.poll(); // 궁수 출동 

				for (int dir = 0; dir < 3; dir++) {
					int nx = cur.x + dx[dir];
					int ny = cur.y + dy[dir];

					if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) { // 범위 초과하거나 방문한 곳이라면 다른방향으로 continue
						continue;
					}

					visited[nx][ny] = true; // 방문 처리

					if (copyMap[nx][ny] == 1) { // 그곳에 적이 있다면
						target.add(new Pos(nx, ny)); // target에 추가.
						break; // 방향이 왼쪽부터 진행되기 때문에, 적이 발견된다면 더이상 볼 필요없이 바로 break
					}
					acher.add(new Pos(nx, ny)); // 적을 찾지 못해서, 거리를 늘려서 적 찾기 위해 이어나갈 위치 add
				}
				distance++;

			}
		}

		for (Pos t : target) { // target에 공격후 map 수정, score++
			copyMap[t.x][t.y] = 0;
			score++;
		}

	}

	private static class Pos {
		int x;
		int y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
