import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ13335 {

	static int N, W, L;
	static Queue<Integer> truckQ;
	static Queue<Integer> bridgeQ;
	static int bridgeWeight;
	static int time;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		// N,W,L
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		truckQ = new LinkedList<>();
		bridgeQ = new LinkedList<>();

		// initialize

		// 다리에 진입 예정인 트럭
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			truckQ.add(Integer.parseInt(st.nextToken()));
		}

		// 다리에 위치한 물체(공백,트럭)
		for (int i = 0; i < W; i++) {
			bridgeQ.add(0);
		}

		int ret = simulation();

		System.out.print(ret);
	}

	public static int simulation() {

		int time = 0;

		while (true) {
			// 다리에 있는 것들을 이동시킨다.
			int outWeight = bridgeQ.poll();

			// 다리 현재 하중 업데이트
			bridgeWeight -= outWeight;

			if (L - bridgeWeight >= truckQ.peek()) { // 현재 상황대비, 다음 트럭을 수용할 수 있다면
				// 다음 트럭 투입
				int inWeight = truckQ.poll();
				bridgeQ.add(inWeight);
				bridgeWeight += inWeight;
			} else { // 현재 상황대비, 다음 트럭이 들어오면 최대하중을 넘어서 들어올 수 없다면
				bridgeQ.add(0); // 공백 투입
			}

			time++;

			if (truckQ.isEmpty()) { // 만약 마지막 트럭이 정상적으로 다리에 진입했다면,
				while (!bridgeQ.isEmpty()) { // 다리 이동 빨리감기
					bridgeQ.poll();
					time++;
				}
				break;
			}
		}

		return time;

	}
}
