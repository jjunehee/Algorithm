import java.util.*;

class PROG131704 {
    public int solution(int[] order) {
        
        Queue<Point> q = new LinkedList<>();
        Stack<Point> stack = new Stack<>();
        for(int i=0; i<order.length; i++) {
            q.add(new Point(i+1));
        }
        
        int answer = 0;
        int index = 0;
        while(true) {
            Point nowQ = null;
            if(!q.isEmpty()) {
                nowQ = q.peek();
            }
            
            Point nowS = null;
            if(stack.size() != 0) {
                nowS = stack.peek();
            }
            
            if(!q.isEmpty() && nowQ.order == order[index]) {
                answer++;
                index++;
                q.poll();
            } else {
                if(stack.size() != 0 && nowS.order == order[index]) {
                    answer++;
                    index++;
                    stack.pop();
                } else {
                    if(!q.isEmpty()) {
                        stack.push(new Point(q.poll().order));    
                    } else {
                        break;
                    }
                }
            }
        }
        
        return answer;
    }
}

class Point {
    int order;
    public Point(int num) {
        this.order = num;
    }
}