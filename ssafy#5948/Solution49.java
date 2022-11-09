import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution49 {
	static int[] answer;
	static Set<Integer> set = new HashSet<>();
	static PriorityQueue<Integer> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		answer = new int[T];
		for (int t = 0; t < T; t++) {
			int[] arr = new int[7];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 7; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			dfs(0, arr, 0, 0);
			set.forEach((value) -> pq.add(value));
		}
		Iterator iter = pq.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}

	public static void dfs(int idx, int[] arr, int count, int value) {
		if (count == 3) {
			set.add(value);
			return;
		}

		for (int i = idx; i < 7; i++) {
			dfs(idx + 1, arr, count + 1, value + arr[i]);
			dfs(idx + 1, arr, count, value);
		}
	}
}
