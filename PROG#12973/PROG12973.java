import java.util.*;
import java.io.*;

class PROG12973 {
    
    public int solution(String s)
    {
    
        Stack<Character> stack = new Stack<>();
        stack.push(s.charAt(0));
        
        for(int i=1; i<s.length(); i++) {
            if(stack.size() == 0) {
                stack.push(s.charAt(i));
            } else if(stack.peek() == s.charAt(i)) {
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
        }
        
        return stack.size() == 0 ? 1 : 0;
    }
    
}