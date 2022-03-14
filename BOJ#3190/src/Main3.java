package src;

import java.io.*;
import java.util.*;

public class Main3 {
	public static boolean[][] appleMap;
	public static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Snake snake = new Snake(new boolean[N][N]);

		appleMap = new boolean[N][N];

		// 사과 개수 입력
		int K = Integer.parseInt(br.readLine());

		// 사과를 map에 추가
		StringTokenizer st;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			appleMap[a][b] = true;
		}

		// 방향 변경 정보
		int L = Integer.parseInt(br.readLine());

		int timeLimit = 100000;
		char[] moveInfo = new char[timeLimit];
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			char b = st.nextToken().charAt(0);
			moveInfo[a] = b;
		}

		for (int time = 1; time <= timeLimit; time++) {
			if (snake.move()) {
				snake.rotate(moveInfo[time]);
			} else {
				System.out.println(time);
				return;
			}
		}

	}

	public static class Snake {
		private boolean[][] bodyMap;
		private Deque<Body> bodyQueue = new ArrayDeque<>();
		private int[] dx = { 0, 1, 0, -1 };
		private int[] dy = { 1, 0, -1, 0 };
		private int dir = 0;

		public Snake(boolean[][] map) {
			this.bodyMap = map;
			this.bodyQueue.add(new Body(0, 0));
			this.bodyMap[0][0] = true;
		}

		boolean move() {
			Body prev = this.bodyQueue.getLast();
			Body head = new Body(prev.x + dx[this.dir], prev.y + dy[this.dir]);

			// 벽에 부딪혔을때
			if (head.x >= N || head.x < 0 || head.y >= N || head.y < 0) {
				return false;
			}
			
			// 자기 몸에 부딪혔을때
			if(bodyMap[head.x][head.y]) {
				return false;
			}
			
			//사과의 유무
			if(!appleMap[head.x][head.y]) {
				Body tail = this.bodyQueue.removeFirst();
				bodyMap[tail.x][tail.y] = false;
			} else {
				appleMap[head.x][head.y] = false;
			}
			
			this.bodyMap[head.x][head.y] = true;
			this.bodyQueue.addLast(head);
			return true;
			
		}

		void rotate(char c) {
			if( c == 'L') {
				this.dir = (this.dir+3) % 4;
			} else if ( c == 'D') {
				this.dir = (this.dir+1) % 4;
			}
		}
		
		private static class Body {
			int x;
			int y;

			public Body(int x, int y) {
				this.x = x;
				this.y = y;
			}
		}
	}
}