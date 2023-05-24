
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

static List<Integer> kList;
static List<Integer> tmpList;
static int N;
static int[] pick;
static boolean[] visited;
static int score;
static int ret;

public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int T = Integer.parseInt(br.readLine());

    StringBuilder sb = new StringBuilder();
    for (int t = 1; t <= T; t++) {
        sb.append("#" + t + " ");
        N = Integer.parseInt(br.readLine());

        ret = Integer.MIN_VALUE;
        kList = new ArrayList<>();
        tmpList = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            kList.add(Integer.parseInt(st.nextToken()));
        }

        visited = new boolean[N];
        pick = new int[N];
        permu(0);
        sb.append(ret).append("\n");
    }
    System.out.println(sb.toString());
}

public static void permu(int cnt) {
    if (cnt == N) {
        score = 0;
        gameStart(pick);
        ret = Math.max(ret, score);
        return;
    }

    for (int i = 0; i < N; i++) {
        if (visited[i]) {
            continue;
        }
        visited[i] = true;
        pick[cnt] = kList.get(i);
        permu(cnt + 1);
        visited[i] = false;
    }
}

private static void gameStart(int[] pick2) {
	
    for (int i = 0; i < kList.size(); i++) {
        tmpList.add(kList.get(i));
    }

    // pick에 순서가 저장되어있으므로 이제 앞에서부터 차례대로 뽑으면서 풍선을 터뜨린다.
    for (int i = 0; i < N; i++) {
        int ballon = pick[i];

        for (int k = 0; k < tmpList.size(); k++) {
            if (tmpList.get(k) == ballon) {

                if (tmpList.size() == 1) {
                    score += tmpList.get(k);
                    tmpList.remove(k);
                    break;
                }

                if (k == 0) {
                    score += tmpList.get(k + 1);
                } else if (k == tmpList.size() - 1) {
                    score += tmpList.get(k - 1);
                } else {
                    score += (tmpList.get(k - 1) * tmpList.get(k + 1));
                }
                tmpList.remove(k);
                break;

            }
        }
    }

}
}