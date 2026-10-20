import java.util.*;

class Solution {
    
    static PriorityQueue<Book> pq;
    
    public int solution(String[][] book_time) {
        int answer = 0;
        
        pq = new PriorityQueue<>();
        for(String[] book : book_time) {
            String[] startStr = book[0].split(":");
            int startTime = Integer.parseInt(startStr[0])*60 + Integer.parseInt(startStr[1]);
            String[] endStr = book[1].split(":");
            int endTime = Integer.parseInt(endStr[0])*60 + Integer.parseInt(endStr[1]);   
            pq.add(new Book(startTime, endTime));
        }
        
        PriorityQueue<Room> roomPQ = new PriorityQueue<>();
    
        while(!pq.isEmpty()) {
            Book book = pq.poll();
            
            if(roomPQ.isEmpty()) {
                roomPQ.add(new Room(book.endTime+10));
                answer++;
                continue;
            }
            
            Room room = roomPQ.poll();
            
            if(room.endTime <= book.startTime) {
                roomPQ.add(new Room(book.endTime+10));
            } else {
                answer++;
                roomPQ.add(room);
                roomPQ.add(new Room(book.endTime+10));
            }
            
            
        }
        
        return answer;
    }
    
    public static class Book implements Comparable<Book> {
        int startTime;
        int endTime;
        
        public Book(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
        
        @Override
        public int compareTo(Book o) {
            return this.startTime - o.startTime;
        }
    }
    
    public static class Room implements Comparable<Room> {
        int endTime;
        public Room(int endTime) {
            this.endTime = endTime;
        }
        
        @Override
        public int compareTo(Room o) {
            return this.endTime - o.endTime;
        }
    }
}