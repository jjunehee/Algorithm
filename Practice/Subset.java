
public class Subset {
	static int[] arr = { 1, 2, 3, 4, 5 };
	static boolean[] visited;
	static int count;
	public static void main(String[] args) {

		visited = new boolean[arr.length];

		comb(0, 0);
		System.out.println(count);
	}

	private static void comb(int cnt, int idx) {

		if (idx == arr.length) {
			count++;
			for (int i = 0; i < arr.length; i++) {
				if(visited[i]) System.out.print(arr[i] + " ");
			}
			System.out.println();
			return;
		}
		

		visited[idx] = true;
		comb(cnt + 1, idx + 1);
		visited[idx] = false;
		comb(cnt, idx + 1);
	}
}
