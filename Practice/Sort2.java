
import java.util.*;
import java.io.*;

public class Sort2 {

	public static void main(String[] args) {

		int[] arr = { 3, 2, 1 };

		Arrays.sort(arr);

		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();

		List<Integer> list = new ArrayList<>();

		list.add(2);
		list.add(1);
		list.add(4);
		list.add(5);
		list.add(1);

		Collections.sort(list);
		for (Integer num : list) {
			System.out.print(num + " ");
		}
		System.out.println();

		List<Person> personList = new ArrayList<>();
		personList.add(new Person("A", 30));
		personList.add(new Person("B", 24));
		personList.add(new Person("C", 26));

		Collections.sort(personList);
		for (Person p : personList) {
			System.out.println(p.name + " " + p.age);
		}
		System.out.println();

		Map<String, Integer> map = new HashMap<>();
		map.put("조준희", 28);
		map.put("최준서", 24);
		map.put("정규성", 26);

		List<String> keyList = new ArrayList<>(map.keySet());
		for (String name : keyList) {
			System.out.print(name + " ");
		}
		System.out.println();

		
		Map<String,Integer> countryMap = new HashMap<>();
		String[] countries = { "대한민국", "일본", "프랑스", "대한민국", "프랑스", "대한민국" };

		for (int i = 0; i < countries.length; i++) {
			countryMap.compute(countries[i], (name, count) -> count == null ? 1 : count + 1);
		}
		
		System.out.println(countryMap.get("프랑스"));
		
		
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
