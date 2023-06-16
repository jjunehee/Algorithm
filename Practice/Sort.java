import java.util.*;

public class Sort {

	public static void main(String[] args) {

		int[] arr = { 1, 2, 3, 2 };

		Arrays.sort(arr);

		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}

		System.out.println();
		ArrayList<Integer> list = new ArrayList<>();

		list.add(3);
		list.add(4);

		Collections.sort(list, Collections.reverseOrder());

		for (Integer n : list) {
			System.out.print(n + " ");
		}

		System.out.println();
		ArrayList<Person> pList = new ArrayList<>();

		pList.add(new Person("조준희", 28));
		pList.add(new Person("정규성", 26));
		pList.add(new Person("최준서", 30));

		Collections.sort(pList);

		for (Person p : pList) {
			System.out.println(p.name + " " + p.age);
		}

		HashMap<String, Integer> map = new HashMap<>();

		String[] str = { "가나초콜릿","딸기", "나랑드사이다","딸기", "딸기", "딸기", "포도" };

		for (int i = 0; i < 7; i++) {
			map.compute(str[i], (name, count) -> count == null ? 1 : count + 1);
		}
		
		System.out.println(map.get("딸기"));
		
		ArrayList<String> keyList = new ArrayList<>(map.keySet());
		
		Collections.sort(keyList);
		
		for(String s : keyList) {
			System.out.print(s);
		}
		
		System.out.println();
		int maxCount = Integer.MIN_VALUE;
		String maxName = "";
		for(Map.Entry<String, Integer> entry : map.entrySet()) {
			String name = entry.getKey();
			int count = entry.getValue();
			
			if(count > maxCount) {
				maxName = name;
				maxCount = count;
			}
			
		}
		
		System.out.println(maxName + " " + maxCount);
	}

	public static class Person implements Comparable<Person> {
		String name;
		int age;

		public Person(String name, int age) {
			this.name = name;
			this.age = age;
		}

		@Override
		public int compareTo(Person o) {
			return this.age - o.age;
		}

	}
}
