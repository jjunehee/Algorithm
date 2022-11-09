import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution49 {
	static int[] answer;
	static Set<Integer> set = new HashSet<>();
	static ArrayList<Integer> list = new ArrayList<>();
	static boolean[] visited = new boolean[7];

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
			set.forEach((value) -> list.add(value));
			Collections.reverse(list);
			answer[t] = list.get(4);
		}

		for (int i = 0; i < T; i++) {
			System.out.println("#" + (i+1) + " " + answer[i]);
		}

	}

	public static void dfs(int idx, int[] arr, int count, int value) {
		if (count == 3) {
			set.add(value);
			return;
		}

		for (int i = idx; i < 7; i++) {
			if (!visited[i]) {
				visited[i] = true;
				dfs(idx + 1, arr, count + 1, value + arr[i]);
				visited[i] = false;
			}
		}
	}
}
