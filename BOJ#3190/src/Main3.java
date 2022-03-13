package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main3{
    public static boolean[][] appleMap;
    public static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        appleMap = new boolean[N][N];
        Snake snake = new Snake(new boolean[N][N]);

        int K = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            appleMap[y][x] = true;
        }

        int changeDirLen = Integer.parseInt(br.readLine());
        final int timeLimit = 100000;
        char[] changeDirArray = new char[timeLimit];
        for (int i = 0; i < changeDirLen; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            changeDirArray[a] = c;
        }

        for (int time = 1; time < timeLimit; ++time) {
            if (snake.move()) {
                snake.rotate(changeDirArray[time]);
            } else {
                System.out.println(time);
                return;
            }
        }
    }


    public static class Snake {
        private final boolean[][] bodyMap;
        private int dir = 0;
        private final Deque<Body> bodyQueue = new ArrayDeque<>();
        private final int[] dx = {1, 0, -1, 0};
        private final int[] dy = {0, 1, 0, -1};
        public Snake(boolean[][] map) {
            this.bodyMap = map;
            bodyQueue.add(new Body(0, 0));
            this.bodyMap[0][0] = true;
        }

        boolean move() {
            Body prev = this.bodyQueue.getLast();
            Body head = new Body(prev.x + dx[this.dir], prev.y + dy[this.dir]);

            // 새로운 head 벽 -> false
            if (head.x >= N || head.x < 0 || head.y >= N || head.y < 0) {
                return false;
            }

            // 몸통 박치기시 false
            if (this.bodyMap[head.y][head.x]) {
                return false;
            }

            // 사과가 없으면 tail remove
            if (!appleMap[head.y][head.x]) {
                Body tail = this.bodyQueue.removeFirst();
                this.bodyMap[tail.y][tail.x] = false;
            } else {
                appleMap[head.y][head.x] = false;
            }

            this.bodyQueue.addLast(head);
            this.bodyMap[head.y][head.x] = true;
            return true;
        }

        void rotate(char c) {
            if ('L' == c) {
                this.dir = (this.dir + 3) % 4;
            } else if ('D' == c) {
                this.dir = (this.dir + 1) % 4;
            }
        }

        private static class Body {
            final int x;
            final int y;

            public Body(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
    }
}