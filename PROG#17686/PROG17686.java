
import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        
        PriorityQueue<FileDto> pq = new PriorityQueue<>();
        int order = 1;
        for(String fileStr : files) {
            FileDto fileDto = formatting(fileStr, order++);     
            pq.add(fileDto);
        }
        
        String[] answer = new String[pq.size()];
        int idx = 0;
        while(!pq.isEmpty()) {
            FileDto file = pq.poll();
            answer[idx++] = file.HEAD + file.NUMBER + file.TAIL;
        }        
        return answer;
    }
    
    public FileDto formatting(String fileStr, int order) {
        
        char[] fileToCharArray = fileStr.toCharArray();
        
        int startIdx = 0;
        for(int i=0; i<fileToCharArray.length; i++) {
            if(Character.isDigit(fileToCharArray[i])) {
                startIdx = i;
                break;
            }
        }
        
        int cnt = 0;
        int endIdx = -1;
        for(int i=startIdx; i<fileToCharArray.length; i++) {
            if(!Character.isDigit(fileToCharArray[i]) || cnt == 5) {
                endIdx = i;
                break;
            }
            cnt++;
        }
        
        if(endIdx == -1) {
            endIdx = fileStr.length();
        }
        String HEAD = fileStr.substring(0,startIdx);
        String NUMBER = fileStr.substring(startIdx,endIdx);
        String TAIL = fileStr.substring(endIdx);
        
        return new FileDto(HEAD, NUMBER, TAIL, order);
    }
    
    public class FileDto implements Comparable<FileDto> {
        String HEAD;
        String NUMBER;
        String TAIL;
        int order;
        public FileDto(String HEAD, String NUMBER, String TAIL, int order) {
            this.HEAD = HEAD;
            this.NUMBER = NUMBER;
            this.TAIL = TAIL;
            this.order = order;
        }
        
        @Override
        public int compareTo(FileDto o) {
            
            String head1 = this.HEAD.toUpperCase();
            String head2 = o.HEAD.toUpperCase();
            
            int headCompare = head1.compareTo(head2);
            if(headCompare != 0) {
                return headCompare;
            }
            
            int numCompare = Integer.compare(Integer.parseInt(this.NUMBER), Integer.parseInt(o.NUMBER));
            if(numCompare != 0) {
                return numCompare;
            }
            
            return Integer.compare(this.order, o.order);
        }
    }
}