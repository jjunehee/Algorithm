// 타겟 넘버
public class Solution {
	static int cnt = 0;

	public int solution(int[] numbers, int target) {

		dfs(numbers, 0, 0, target);

		return cnt;
	}

	public void dfs(int[] numbers, int idx, int sum, int target) {

		if (idx == 5 && sum == target) {
			cnt++;
			return;
		}
		
		if (idx >= 5) {
			return;
		}

		dfs(numbers, idx + 1, sum + numbers[idx], target);
		dfs(numbers, idx + 1, sum - numbers[idx], target);
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		int[] numbers = { 1, 1, 1, 1, 1 };
		int target = 3;

		int result = s.solution(numbers, target);
		System.out.println(result);
	}
}