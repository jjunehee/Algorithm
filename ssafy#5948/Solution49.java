import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
 
public class Solution49 {
    static int[] answer;
    boolean[] visited = new boolean[7];
    static Set<Integer> set = new HashSet<>();
 
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        answer = new int[T];
        StringBuffer sb = new StringBuffer();
        for (int t = 0; t < T; t++) {
            sb.append("#").append(t + 1).append(" ");
            int[] arr = new int[7];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 7; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            set.clear();
            dfs(0, arr, 0, 0);
            List lists = new ArrayList<>(set);
            Collections.sort(lists, Collections.reverseOrder());
            sb.append(lists.get(4)).append("\n");
        }
        System.out.println(sb);
 
    }
 
    public static void dfs(int idx, int[] arr, int count, int value) {
        if (count == 3) {
            set.add(value);
            return;
        }
 
        for (int i = idx; i < 7; i++) {
            dfs(i + 1, arr, count + 1, value + arr[i]);
        }
    }
 
}