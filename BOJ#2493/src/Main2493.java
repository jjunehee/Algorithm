import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;
// 탑
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

//		Stack<int[]> stack = new Stack<>(); // stack�뿉 �깙�쓽 �쐞移�, �넂�씠瑜� ���옣�븳�떎.
//		for (int i = 1; i <= N; i++) {
//			int top = Integer.parseInt(st.nextToken()); // �쁽�옱 �깙�쓽 �쐞移섎�� �엯�젰 諛쏅뒗�떎.
//
//			while (!stack.isEmpty()) { // �엯�젰諛쏆븯�뜕 �깙�씠 �엳�떎硫�
//				if (stack.peek()[1] >= top) { // �쁽�옱 �깙 湲곗� 媛��옣 媛�源뚯슫 �쇊履� �깙怨� �넂�씠 鍮꾧탳�빐�꽌 �뜑 �넂�떎硫�
//					System.out.print(stack.peek()[0] + " "); // 諛붾줈 �닔�떊�븯�뒗 �깙�쑝濡� �꽑�젙
//					break;
//				}
//				stack.pop(); // 媛��옣 媛�源뚯슫 �쇊履� �깙�쓽 �넂�씠媛� �뜑 �궙�떎硫� �닔�떊�븯吏� 紐삵븷 肉먮뜑�윭, �븵�쑝濡� �굹�삱 �삤瑜몄そ�뿉 �엳�뒗 �깙�뱾�� �젅�� �젒洹쇳븷 �닔 �뾾�쓬. �솢�깘�븯硫� �쁽�옱 �깙�쓽 �넂�씠媛� �뜑 �넂�쑝誘�濡�			
//			}
//			if (stack.isEmpty()) { // �븷珥덉뿉 留� �쇊履쎌씠嫄곕굹, �뜑 �넂�� �깙�씠 �뾾�뼱�꽌 �쟾遺� pop�뻽�떎硫� 0異쒕젰�븯�룄濡�
//				System.out.print("0 ");
//			}
//			stack.push(new int[] { i, top }); // �쁽�옱 �깙�쓣 �븵�쑝濡� �궗�슜�븷 �닔 �엳�룄濡� stack push
//		}

	}

}
