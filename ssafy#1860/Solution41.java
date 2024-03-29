import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Solution41 {
	static boolean result;
	static String[] answer;
	static int[] guest;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		answer = new String[T];
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			guest = new int[N];
			ArrayList<Integer> arrivalTime = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int time = Integer.parseInt(st.nextToken());
//				guest[i] = time;
				arrivalTime.add(time);
			}

//			Arrays.sort(guest);
			Collections.sort(arrivalTime);
			result = true;

			Iterator iter = arrivalTime.iterator();
			int count = 0;
			while (iter.hasNext()) {
				int time = (int) iter.next();
				count++;
				if (time / M * K - count < 0) {
					result = false;
				}
			}

			if (result) {
				answer[t] = "Possible";
			} else {
				answer[t] = "Impossible";
			}
		}

		for (int i = 0; i < T; i++) {
			System.out.println("#" + (i + 1) + " " + answer[i]);
		}
	}
}
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.StringTokenizer;
//
//public class Solution41 {
//	static boolean result;
//	static String[] answer;
//	static int[] guest;
//
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int T = Integer.parseInt(br.readLine());
//		answer = new String[T];
//		for (int t = 0; t < T; t++) {
//			StringTokenizer st = new StringTokenizer(br.readLine());
//			int N = Integer.parseInt(st.nextToken());
//			int M = Integer.parseInt(st.nextToken());
//			int K = Integer.parseInt(st.nextToken());
//			guest = new int[N];
//			st = new StringTokenizer(br.readLine());
//			for (int i = 0; i < N; i++) {
//				int time = Integer.parseInt(st.nextToken());
//				guest[i] = time;
//			}
//
//			Arrays.sort(guest);
//
//			result = true;
//			for (int i = 0; i < N; i++) {
//				if (guest[i] / M * K - i < 1) {
//					result = false;
//					break;
//				}
//			}
//
//			if (result) {
//				answer[t] = "Possible";
//			} else {
//				answer[t] = "Impossible";
//			}
//		}
//
//		for (int i = 0; i < T; i++) {
//			System.out.println("#" + (i + 1) + " " + answer[i]);
//		}
//	}
//}
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.StringTokenizer;
//
//public class Solution41 {
//	static boolean result;
//	static String[] answer;
//
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int T = Integer.parseInt(br.readLine());
//		answer = new String[T];
//		for (int t = 0; t < T; t++) {
//			StringTokenizer st = new StringTokenizer(br.readLine());
//			int N = Integer.parseInt(st.nextToken());
//			int M = Integer.parseInt(st.nextToken());
//			int K = Integer.parseInt(st.nextToken());
//
//			Map<Integer, Integer> map = new HashMap<>();
//			st = new StringTokenizer(br.readLine());
//			for (int i = 0; i < N; i++) {
//				int time = Integer.parseInt(st.nextToken());
//				map.compute(time, (tt, count) -> count == null ? 1 : count + 1);
//			}
//
//			Object[] keys = map.keySet().toArray();
//			Arrays.sort(keys);
//
//			int time = 0;
//			int remain = 0;
//			int count = 0;
//			while (true) {
//				time++;
//
//				if (time % M == 0) {
//					remain += K;
//				}
//				
//				if (map.containsKey(time) && map.get(time) != 0) {
//					if (remain < map.get(time)) {
//						result = false;
//						break;
//					}
//					remain -= map.get(time);
//					count++;
//				}
//
//				if (count == N) {
//					result = true;
//					break;
//				}
//
//			}
//
//			if (result) {
//				answer[t] = "Possible";
//			} else {
//				answer[t] = "Impossible";
//			}
//		}
//
//		for (int i = 0; i < T; i++) {
//			System.out.println("#" + (i+1) + " " + answer[i]);
//		}
//	}
//}
