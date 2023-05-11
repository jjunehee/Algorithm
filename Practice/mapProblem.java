import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class mapProblem {

	public static void main(String[] args) throws IOException {
		// �������� �� �ִ�,�ּ� �� ���ϱ�
		// �Է� : ����̸� ��
		// ��� : �̸� �ּ� �ִ�

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int peopleAmount = 5;
		Map<String,Set<Integer>> map = new HashMap<>();
		for (int i = 0; i < peopleAmount; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int score = Integer.parseInt(st.nextToken());
			map.computeIfAbsent(name, k ->  new HashSet<>()).add(score);
		}
		
		Object[] keySet = map.keySet().toArray();
		for(Object key : keySet) {
			String keyStr = String.valueOf(key);
			if(map.get(keyStr).size() > 1) {
				System.out.println(keyStr + "  " + map.get(keyStr));
			}
		}
	}
	public static class Pairs {
		
	}
}
