import java.util.Arrays;

public class PROG42885 {

	public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        
        int minIndex = 0;
        for(int max = people.length-1; minIndex <= max; max--) {
            if(people[minIndex] + people[max] <= limit) {
                minIndex++;
            }
            answer++;
        }
        
        return answer;
    }
}
