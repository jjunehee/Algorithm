import java.util.*;
import java.io.*;

class PROG12981 {
	public int[] solution(int n, String[] words) {
		int[] answer = {};

		String[][] info = new String[words.length / n][n];

		int idx = 0;
		for (int i = 0; i < words.length / n; i++) {
			for (int j = 0; j < n; j++) {
				info[i][j] = words[idx++];
			}
		}

		Map<String, Integer> map = new HashMap<>();

		int check;
		char lastChar = info[0][0].charAt(0);
		outerloop: for (int round = 0; round < words.length / n; round++) {
			for (int order = 0; order < n; order++) {
				check = map.getOrDefault(info[round][order], -1);
				if (check == -1) {
					if (lastChar != info[round][order].charAt(0)) {
						answer = new int[] { order + 1, round + 1 };
						break outerloop;
					} else {
						map.put(info[round][order], 1);
						lastChar = info[round][order].charAt(info[round][order].length() - 1);
					}
				} else {
					answer = new int[] { order + 1, round + 1 };
					break outerloop;
				}
			}
		}

		return answer.length == 0 ? new int[] { 0, 0 } : answer;
	}

}