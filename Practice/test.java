import java.util.Arrays;

public class test {

	public static void main(String[] args) {

		int[] arr = new int[10];
		for (int i = 0; i < 10; i++) {
			arr[i] = (int) (Math.random() * 10);
		}

		System.out.println(Arrays.toString(arr));

		// Bubble Sort
		for (int i = arr.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}

		// Selection Sort
		for (int i = 0; i < arr.length - 1; i++) {
			int minIdx = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[minIdx] > arr[j]) {
					minIdx = j;
				}
			}
			int temp = arr[minIdx];
			arr[minIdx] = arr[i];
			arr[i] = temp;
		}

		// Insertion Sort
		for (int i = 1; i < arr.length; i++) {
			int temp = arr[i];
			int prev = i - 1;
			while (prev >= 0 && arr[prev] > temp) {
				arr[prev + 1] = arr[prev];
				prev--;
			}
			arr[prev + 1] = temp;
		}

		System.out.println(Arrays.toString(arr));
	}

}