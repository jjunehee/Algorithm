import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main4358 {
	static int allcnt;
	public static void main(StringProblem[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Map<StringProblem,Integer> map = new HashMap<>();
		StringProblem st = br.readLine();
		while(!st.equals("")) {
			map.compute(st, (tree, count) -> count == null ? 1 : count + 1);
			allcnt++;
			
			st = br.readLine();
		}
		
		Object[] keys = map.keySet().toArray();
		Arrays.sort(keys);
		
		StringBuilder sb = new StringBuilder();
		for(Object key : keys) {
			StringProblem keyStr = (StringProblem)key;
			int count = map.get(keyStr);
			double per = (count * 100.0) / allcnt;
			
			sb.append(keyStr + " " + StringProblem.format("%.4f", per) + "\n");
		}
		System.out.println(sb.toString());
	}
}