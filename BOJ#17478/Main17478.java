import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main17478 {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static String[] str = { "\"����Լ��� ������?\"", "\"�� ����. �������� �� �� ����⿡ �̼��� ��� ������ ����� ������ �־���.",
			"���� ������� ��� �� ���ο��� ������ ������ �߰�, ��� �����Ӱ� ����� �־���.", "���� ���� ��κ� �ǾҴٰ� �ϳ�. �׷��� ��� ��, �� ���ο��� �� ���� ã�ƿͼ� ������.\"",
			"��� �亯�Ͽ���.", "\"����Լ��� �ڱ� �ڽ��� ȣ���ϴ� �Լ����\"", "��� �� ��ǻ�Ͱ��а� �л��� ������ �������� ã�ư� ������." };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		sb.append(str[6]).append("\n");
		recur(0);

		System.out.println(sb.toString());
	}

	private static void recur(int cnt) {

		if (cnt == N) {
			printDepth(cnt);
			sb.append(str[0]).append("\n");
			printDepth(cnt);
			sb.append(str[5]).append("\n");
			printDepth(cnt);
			sb.append(str[4]).append("\n");
			return;
		}

		for (int i = 0; i < 4; i++) {
			printDepth(cnt);
			sb.append(str[i]).append("\n");
		}

		recur(cnt + 1);

		printDepth(cnt);
		sb.append(str[4]).append("\n");

	}

	private static void printDepth(int depth) {
		for (int i = 0; i < depth; i++) {
			sb.append("____");
		}
	}
}
