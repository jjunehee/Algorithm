package BOJ1351;

import java.util.*;
import java.io.*;

public class BOJ1351 {

    static long N,P,Q;
    static Map<Long,Long> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        P = Long.parseLong(st.nextToken());
        Q = Long.parseLong(st.nextToken());

        // (i, A(i))
        if(N==0) {
            System.out.print(1);
        } else {
            System.out.print(topdown(N));
        }
    
    }

    public static long topdown(long N) {

        if(map.containsKey(N)) {
            return map.get(N);
        }
        if(N == 0) {
            return 1;
        }

        long a = N/P;
        long b = N/Q;

        map.put(N, topdown(a) + topdown(b));

        return map.get(N);
    }
}