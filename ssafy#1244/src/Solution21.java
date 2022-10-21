package src;
import java.util.Scanner;

public class Solution21 {
    static int chance;
    static int answer;
    static String[] target;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();

        for(int test_case = 1; test_case<= T; test_case++) {
            String input = sc.nextLine();
            String[] inputArray = input.split(" ");

            target = inputArray[0].split("");
            chance = Integer.valueOf(inputArray[1]);

            answer = 0;
            answer = dfs(0, 0);

            System.out.println("#" + test_case + " " + answer);
        }
    }

    static int dfs(int k, int count) {
        String temp;
        String targetnum = "";

        if(chance == count) { //교체 기회를 다 썼다면,
            for (String tmp: target) {
                targetnum += tmp;
            }
            if(Integer.valueOf(targetnum) > answer) {
                answer = Integer.valueOf(targetnum);
            }
            return answer;
        }


        for(int i=k; i<target.length; i++) {
            for(int j=i+1; j<target.length; j++) {

                if(Integer.valueOf(target[i]) <= Integer.valueOf(target[j])) {
                    temp = target[i]; target[i] = target[j]; target[j] = temp; // swap
                    dfs(i, count+1);
                    temp = target[i]; target[i] = target[j]; target[j] = temp; // swap
                }
            }
        }
        return answer;
    }


}