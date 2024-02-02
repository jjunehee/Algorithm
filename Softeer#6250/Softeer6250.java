
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

// 성적 평가
public class Softeer6250 {

	static List<Integer>[] info;
	static int[][] score;
	static int[] totalScore;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		info = new ArrayList[4];
		for (int i = 0; i <= 3; i++) {
			info[i] = new ArrayList<>();
		}

		score = new int[4][N];
		totalScore = new int[N];
		for (int i = 1; i <= 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				info[i].add(num);
				score[i][j] = num;
				totalScore[j] += num;
			}
		}

		for (int j = 0; j < N; j++) {
			info[0].add(totalScore[j]);
		}

		for (int i = 0; i <= 3; i++) {
			Collections.sort(info[i], Collections.reverseOrder());
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= 3; i++) {
			Map<Integer, Integer> map = new HashMap<>();
			for (int idx = 0; idx < N; idx++) {
				int check = map.getOrDefault(info[i].get(idx), -1);
				if (check == -1) {
					map.put(info[i].get(idx), idx + 1);
				}
			}
			for (int j = 0; j < N; j++) {
				if(j==N-1) {
					sb.append(map.get(score[i][j]));
				} else {
					sb.append(map.get(score[i][j]) + " ");
				}
			}
			sb.append("\n");
		}
		
		Map<Integer, Integer> map = new HashMap<>();
		for (int idx = 0; idx < N; idx++) {
			int check = map.getOrDefault(info[0].get(idx), -1);
			if (check == -1) {
				map.put(info[0].get(idx), idx + 1);
			}
		}
		for (int j = 0; j < N; j++) {
			if(j==N-1) {
				sb.append(map.get(totalScore[j]));
			} else {
				sb.append(map.get(totalScore[j]) + " ");
			}
		}
		
		System.out.println(sb.toString());

	}

}
