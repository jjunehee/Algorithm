import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution56 {
	static int[] W, R;
	static boolean[] check;
	static int total;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuffer sb = new StringBuffer();
		for (int t = 1; t <= T; t++) {
			total = 0;
			sb.append("#" + t + " ");

			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			R = new int[n + 1];
			W = new int[m + 1];
			check = new boolean[n + 1];
			for (int i = 1; i <= n; i++) {
				R[i] = Integer.parseInt(br.readLine());
			}
			for (int i = 1; i <= m; i++) {
				W[i] = Integer.parseInt(br.readLine());
			}

			Map<Integer, Integer> map = new HashMap<>();
			Queue<Integer> waitList = new LinkedList<>();
			
			for (int count = 0; count < 2 * m; count++) {
				int now = Integer.parseInt(br.readLine());
				boolean possible = false;
				if (now > 0) { // in
					for (int i = 1; i <= n; i++) {
						if (!check[i]) { // 비어있는 자리 있으면 입장
							
							check[i] = true;
							total += R[i] * W[now];
							map.put(now, i);
							possible = true;
							break;
						}
					}

					if (!possible) {
						waitList.add(now);
					}

				} else { // out
					int parkingNum = map.get(-now);
					check[parkingNum] = false;
					if (!waitList.isEmpty()) {
						now = waitList.poll();
						total += R[parkingNum] * W[now];
						map.put(now, parkingNum);
						check[parkingNum] = true;
					}
				}
			}
			sb.append(total).append("\n");
		}
		System.out.println(sb);
	}
}
