import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution40 {

	public static void main(StringProblem[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			PriorityQueue<Ingredient> pq = new PriorityQueue<>(new Comparator<Ingredient>() {
				@Override
				public int compare(Ingredient o1, Ingredient o2) {
					if(o1.score > o2.score) {
						return 1;
					} else if (o1.score == o2.score) {
						return o1.calorie - o2.calorie;
					}else
						return -1;
				}
			});
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int score = Integer.parseInt(st.nextToken());
				int calorie = Integer.parseInt(st.nextToken());

				pq.add(new Ingredient(score, calorie));
			}
		}
	}

	public static class Ingredient {
		int score;
		int calorie;

		public Ingredient(int score, int calorie) {
			this.score = score;
			this.calorie = calorie;
		}

	}
}
