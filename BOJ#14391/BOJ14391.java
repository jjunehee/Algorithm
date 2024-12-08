
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14391 {
    private static int N, M;
    private static int[][] map;
    private static boolean[][] isHorizontal;
    private static int maxSum = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        isHorizontal = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        explore(0, 0);
        System.out.println(maxSum);
    }

    private static void explore(int row, int col) {
        // 자르기 완료
        if (row == N) {
            calculateSum();
            return;
        }

        // 열이 범위를 벗어나면 다음 행으로 이동
        if (col == M) {
            explore(row + 1, 0);
            return;
        }

        // 가로로 자르기
        isHorizontal[row][col] = true;
        explore(row, col + 1);

        // 세로로 자르기
        isHorizontal[row][col] = false;
        explore(row, col + 1);
    }

    private static void calculateSum() {
        int currentSum = 0;

        // 가로로 이어진 숫자 합 계산
        for (int i = 0; i < N; i++) {
            int rowSum = 0;
            for (int j = 0; j < M; j++) {
                if (isHorizontal[i][j]) {
                    rowSum = rowSum * 10 + map[i][j];
                } else {
                    currentSum += rowSum;
                    rowSum = 0;
                }
            }
            currentSum += rowSum; // 마지막 가로 숫자 추가
        }

        // 세로로 이어진 숫자 합 계산
        for (int j = 0; j < M; j++) {
            int colSum = 0;
            for (int i = 0; i < N; i++) {
                if (!isHorizontal[i][j]) {
                    colSum = colSum * 10 + map[i][j];
                } else {
                    currentSum += colSum;
                    colSum = 0;
                }
            }
            currentSum += colSum; // 마지막 세로 숫자 추가
        }

        // 최대값 갱신
        maxSum = Math.max(maxSum, currentSum);
    }
}
