import java.util.*;
import java.io.*;

class Solution {
    static Map<String,Integer> map = new HashMap<>();
    static Set<String> set = new HashSet<>();
    public String[] solution(String[] orders, int[] course) {
        
        for(int i=0; i<orders.length; i++) {
            String str = orders[i];
            String[] sortedStrArray = str.split("");
            Arrays.sort(sortedStrArray);
            String makeStr = "";
            for(String s : sortedStrArray) {
                makeStr += s;
            }
            orders[i] = makeStr;
        }
        
        for(int pickAmount : course) {
            for(String order : orders) {
                comb(order, "", 0, 0, pickAmount);
            }
        }
        
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if(value >= 2) {
                pq.add(new Pos(key.length(), value, key));    
            }
        }
        List<String> answerList = new ArrayList<>();
        
        Pos first = pq.poll();
        answerList.add(first.menu);
        int checkSize = first.size;
        int checkAmount = first.amount;
        while(!pq.isEmpty()){
            Pos now = pq.poll();
            if(checkSize == now.size) {
                if(checkAmount == now.amount) {
                    answerList.add(now.menu);    
                } else {
                    continue;
                }
                
            } else {
                answerList.add(now.menu);
                checkSize = now.size;
                checkAmount = now.amount;
            }
        }
        
        int idx = 0;
        String[] answer = new String[answerList.size()];
        for(String str : answerList) {
            answer[idx++] = str;
        }
        
        Arrays.sort(answer);
        return answer;
    }
    
    public static void comb(String order, String menu, int idx, int cnt, int end) {
        
        if(cnt==end) {
            map.compute(menu, (key,value) -> value == null ? 1 : value+1);
            return;
        }
        
        for(int i=idx; i<order.length(); i++) {
            comb(order, menu+order.charAt(i), i+1, cnt+1, end);
        }
    }
    
    public static class Pos implements Comparable<Pos> {
        int size;
        int amount;
        String menu;
        
        public Pos(int size, int amount, String menu) {
            this.size = size;
            this.amount = amount;
            this.menu = menu;
        }
        
        @Override
        public int compareTo(Pos o) {
            if(this.size == o.size) {
                return o.amount - this.amount;
            } else {
                return o.size - this.size;
            }
        }
    }
}