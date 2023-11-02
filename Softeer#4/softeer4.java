import java.io.*;
import java.util.*;

public class softeer4 {

  static int n,q;
  static int[] valueArray;
  public static void main(String[] args) throws IOException{

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    n = Integer.parseInt(st.nextToken());
    q = Integer.parseInt(st.nextToken());

    valueArray = new int[n];
    st = new StringTokenizer(br.readLine());
    for(int i=0; i<n; i++) {
      valueArray[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(valueArray);
    Map<Integer,Integer> map = new HashMap<>();
    for(int i=0; i<n; i++) {
      map.put(valueArray[i],i);
    }
    StringBuilder sb = new StringBuilder();
    for(int i=0; i<q; i++) {
      int num = Integer.parseInt(br.readLine());

      int index = map.getOrDefault(num,-1);
      
      if(index == 0 || index == n-1 || index == -1) {
        sb.append(0).append("\n");
        continue;
      }
      sb.append(index*(n-index-1)).append("\n");
    }

    System.out.println(sb.toString());
  }
}