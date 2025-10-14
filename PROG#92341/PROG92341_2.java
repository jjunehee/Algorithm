import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        
        // 기본 시간(분)
        int basicTime = fees[0];
        // 기본 요금(원)
        int basicFee = fees[1];
        // 단위 시간(분)
        int perTime = fees[2];
        // 단위 요금(원)
        int perFee = fees[3];
        
        
        // <차량번호, 시간> 정보를 담을 Map 변수
        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> finalMap = new HashMap<>();
        
        for(String info : records) {
            String[] splitStr = info.split(" ");
            
            // 입,출차 시간
            String[] timeSplitStr = splitStr[0].split(":");
            int time = 60 * Integer.parseInt(timeSplitStr[0]) + Integer.parseInt(timeSplitStr[1]);
            
            // 차량번호
            String carNumber = splitStr[1];
            
            if(!map.containsKey(carNumber)) { // 입차
                map.put(carNumber,time);
            } else { // 출차
                // 입차 시간
                int inTime = map.get(carNumber);
                map.remove(carNumber);
                 // 머무른 시간 갱신
                int stayedTime = time - inTime;
                finalMap.put(carNumber, finalMap.getOrDefault(carNumber,0) + stayedTime);
            }
        }
        
        for(String carNumber : map.keySet()) {
            int outTime = 60*23 + 59;
            int inTime = map.get(carNumber);
            int stayedTime = outTime - inTime;
            finalMap.put(carNumber, finalMap.getOrDefault(carNumber,0) + stayedTime);
        }
        
        
        // 결과를 담을 변수
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        
        for(String carNumber : finalMap.keySet()) {
            int stayedTime = finalMap.get(carNumber);
            if(stayedTime < basicTime) { // 기본 시간 이하
                pq.add(new Pos(carNumber, basicFee));
            } else { // 기본 시간 초과
                int fee = basicFee + (int)(Math.ceil((stayedTime - basicTime) / (double)perTime)) * perFee;
                pq.add(new Pos(carNumber, fee));
            }
        }
        
        int[] answer = new int[pq.size()];
                       
        int idx = 0;
        while(!pq.isEmpty()) {
            Pos now = pq.poll();
            answer[idx++] = now.fee;
        }
        return answer;
    }
    
    public static class Pos implements Comparable<Pos> {
        String carNumber;
        int fee;
        public Pos(String carNumber, int fee) {
            this.carNumber = carNumber;
            this.fee = fee;
        }
        @Override
        public int compareTo(Pos o) {
            return Integer.parseInt(this.carNumber) - Integer.parseInt(o.carNumber);
        }
    }
}