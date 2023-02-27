import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ1987 {
    static char[][] map;
    static boolean[][] visited;

    static int[] dx = { 1, 0, -1, 0 };
    static int[] dy = { 0, -1, 0, 1 };

    static int R, C;
    static Map<Character, Boolean> check = new HashMap<>();
    static int maxDepth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R + 1][C + 1];
        visited = new boolean[R + 1][C + 1];
        for (int i = 1; i <= R; i++) {
            String str = br.readLine();
            for (int j = 1; j <= C; j++) {
                map[i][j] = str.charAt(j - 1);
            }
        }

        dfs3(1, 1, 1);
        System.out.println(maxDepth);
    }

    private static void dfs3(int x, int y, int D) {

        maxDepth = Math.max(D, maxDepth);
        check.put(map[x][y], true);

        for (int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (nx < 1 || nx > R || ny < 1 || ny > C || check.getOrDefault(map[nx][ny], false)) {
                continue;
            }
        
            dfs3(nx, ny, D + 1);
        }

        check.put(map[x][y], false);

    }

}
