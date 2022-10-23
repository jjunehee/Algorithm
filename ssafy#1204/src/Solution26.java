package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class Solution26 {

	static int[] answer;
	static int[] score;

	public static void main(String[] args) throws IOException {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		answer = new int[T];
		for (int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());
			score = new int[1001];
			Map<Integer, Integer> map = new HashMap<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 1000; j++) {
				score[j] = Integer.parseInt(st.nextToken());
				map.compute(score[j], (num, count) -> count == null ? 1 : count + 1);
			}
	
			List<Entry<Integer, Integer>> list_entries = new ArrayList<Entry<Integer, Integer>>(map.entrySet());

			// 비교함수 Comparator를 사용하여 오름차순으로 정렬
			Collections.sort(list_entries, new Comparator<Entry<Integer, Integer>>() {
				// compare로 값을 비교
				public int compare(Entry<Integer, Integer> obj1, Entry<Integer, Integer> obj2) {
					// 오름 차순 정렬
					return obj1.getValue() - obj2.getValue();
				}
			});
			answer[i] = list_entries.get(list_entries.size()-1).getKey();
		}
		
		for (int i = 0; i < T; i++) {
			System.out.println("#" + (i + 1) + " " + answer[i]);
		}
	}
}
