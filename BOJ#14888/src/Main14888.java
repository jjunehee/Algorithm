package src;

import java.io.*;
import java.util.*;

public class Main14888 {

	static int[] numbers;
	static int[] operators;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		operators = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			operators[i] = Integer.parseInt(st.nextToken());
		}

		dfs(0,null);
	}

	private static void dfs(int operatorNum, Queue<Integer> q) {
		Queue<Integer> q1 = new LinkedList<>();
		
		if (operatorNum == N - 1) {
			System.out.print(q.poll());
			return;
		}
		

		for (int i = 0; i < 4; i++) {
			if (operators[i] >= 1) {
				q1.add(i);
				operators[i] -= 1;
				dfs(operatorNum + 1,q1);
				operators[i] += 1;
			}
		}
		return;
	}

	private static void calculate(Queue<Integer> q) {
		
	}

}
