public class OverridingB extends OverridingA {
	
	public static void getData() {
		System.out.println("B getData");
	}
	
	@Override
	public void method() {
		System.out.println("B method");
	}
	
	public static void main(String[] args) {
		OverridingA c1 = new OverridingB();
		c1.getData();
		c1.method();
		
		OverridingB c2 = new OverridingB();
		c2.getData();
		c2.method();
	}
}