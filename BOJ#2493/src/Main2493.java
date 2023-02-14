import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main2493 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		Deque<int[]> deq = new LinkedList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			int top = Integer.parseInt(st.nextToken());
			while (!deq.isEmpty()) {
				if(deq.peekLast()[1] > top) {
					sb.append(deq.peekLast()[0] + " ");
					break;
				}
				deq.pollLast();
			}
			
			if(deq.isEmpty()) {
				sb.append("0 ");
			}

			deq.offerLast(new int[] { i, top });
		}
		System.out.println(sb.toString());

//		Stack<int[]> stack = new Stack<>(); // stack에 탑의 위치, 높이를 저장한다.
//		for (int i = 1; i <= N; i++) {
//			int top = Integer.parseInt(st.nextToken()); // 현재 탑의 위치를 입력 받는다.
//
//			while (!stack.isEmpty()) { // 입력받았던 탑이 있다면
//				if (stack.peek()[1] >= top) { // 현재 탑 기준 가장 가까운 왼쪽 탑과 높이 비교해서 더 높다면
//					System.out.print(stack.peek()[0] + " "); // 바로 수신하는 탑으로 선정
//					break;
//				}
//				stack.pop(); // 가장 가까운 왼쪽 탑의 높이가 더 낮다면 수신하지 못할 뿐더러, 앞으로 나올 오른쪽에 있는 탑들은 절대 접근할 수 없음. 왜냐하면 현재 탑의 높이가 더 높으므로			
//			}
//			if (stack.isEmpty()) { // 애초에 맨 왼쪽이거나, 더 높은 탑이 없어서 전부 pop했다면 0출력하도록
//				System.out.print("0 ");
//			}
//			stack.push(new int[] { i, top }); // 현재 탑을 앞으로 사용할 수 있도록 stack push
//		}

	}

}
