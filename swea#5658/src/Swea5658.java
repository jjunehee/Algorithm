import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Swea5658 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			char[] charArr = br.readLine().toCharArray();

			Queue<Character> q = new ArrayDeque<>();
			for (int i = 0; i < charArr.length; i++) {
				q.add(charArr[i]);
			}

			Set<String> set = new HashSet<>();
			int num = N / 4;
			for (int k = 0; k < num; k++) {
				for (int i = 0; i < 4; i++) {
					String str = "";
					for (int j = 0; j < num; j++) {
						char c = q.poll();
						str += c;
						q.add(c);
					}
					set.add(str);
				}
				q.add(q.poll());
			}

			ArrayList<Integer> list = new ArrayList<>();
			for(String str : set) {
				list.add(Integer.parseInt(str,16));
			}
			
			Collections.sort(list,Collections.reverseOrder());
			
			sb.append(list.get(K-1)).append("\n");
		}
		System.out.println(sb.toString());
		
	}
}
