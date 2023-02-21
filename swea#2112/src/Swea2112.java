import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Swea2112 {
 
    static int D, W, K;
    static int[][] map;
    static int[][] tempMap;
    static int min;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int T = Integer.parseInt(br.readLine());
 
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            sb.append("#" + t + " ");
            StringTokenizer st = new StringTokenizer(br.readLine());
 
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
 
            map = new int[D][W];
            tempMap = new int[D][W];
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    tempMap[i][j] = map[i][j];
                }
            }
 
            if(K==1 || test()) {
                sb.append("0").append("\n");
                continue;
            }
            // 행을 1~K개를 뽑을텐데, 그걸 알 수가 없다.
            min = D;
            comb(0, 0);
             
            sb.append(min).append("\n");
        }
        System.out.println(sb.toString());
    }
 
    private static void comb(int cnt, int idx) {
 
        if (cnt >= min) {
            return;
        }
 
        if (idx == D) { // 모든 행에대한 처리 (아무것도안하는 or A or B) 가 완료된 상태
 
            if (test()) {
                min = Math.min(min, cnt);
            }
             
            return;
        }
 
        // 모든 행에 대한 작업이 이루어지는 곳
 
        // 약품 처리 X
        comb(cnt, idx + 1);
 
        // A넣기
        for (int j = 0; j < W; j++) {
            tempMap[idx][j] = 0;
        }
        comb(cnt + 1, idx + 1);
 
        // B넣기
        for (int j = 0; j < W; j++) {
            tempMap[idx][j] = 1;
        }
        comb(cnt + 1, idx + 1);
 
        // 원래대로 되돌려 놓기
        for (int j = 0; j < W; j++) {
            tempMap[idx][j] = map[idx][j];
        }
    }
 
    private static boolean test() { // 열을 돌아다니면서 합격기준을 만족하는 지 테스트
 
        int cnt = 0;
        for (int j = 0; j < W; j++) {
            for (int i = 0; i <= D - K; i++) {
                if(check(i, j)) { // 열을 확인하는 과정에서 합격하는 부분이 존재한다면 바로 옆칸으로 이동
                    cnt++;
                    break;
                }
            }
        }
         
        if(cnt==W) { // 모든 열이 합격기준 통과
            return true;
        }
         
        return false;
    }
 
    private static boolean check(int i, int j) {
 
        for (int k = 0; k < K; k++) {
            if (tempMap[i][j] == tempMap[i + k][j]) {
                continue;
            }
            return false;
        }
        return true;
    }
}