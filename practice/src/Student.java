package src;

import java.util.*;

public class Student implements Comparator<Student> {

	int age;
	int classNumber;
	
	Student(int age, int classNumber) {
		this.age = age;
		this.classNumber = classNumber;
	}
	
	@Override
	public int compare(Student arg0, Student arg1) {
		// TODO Auto-generated method stub
		return arg0.age-arg1.age;
	}
	public int getAge() {
		return this.age;
	}

	public static void main(String[] args) {
		ArrayList<Student> studentList = new ArrayList<Student>();
		Student a = new Student(12, 1);
		Student b = new Student(10,2);
		
		studentList.add(a);
		studentList.add(b);
		
		Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return -(s1.age-s2.age);
            }
        });
		
		for (Student s : studentList) {
            System.out.println(s.getAge());
        }



		
		
	}


}

