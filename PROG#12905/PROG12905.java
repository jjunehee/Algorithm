class Solution {
    
    static int answer;

    public int solution(int [][]board) {
        
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[0].length; j++) {
                if(board[i][j] == 1) {
                    letsgo(i,j, board);
                }
            }
        }
        
        return answer;
    }
    
    public void letsgo(int x, int y, int[][] board) {
        
        int size = 1;
        while(true) {
            if(y+size >= board[0].length) {
                break;
            }
            
            if(board[x][y+size] == 1) {
                if(checkSquare(x,y,size, board)) {
                    answer = Math.max(answer, (size+1)*(size+1));
                }
            } else {
                break;
            }
            
            size++;
        }
    }
    
    public boolean checkSquare(int x, int y, int size, int[][] board) {
        
        for(int i=x; i<x+size; i++) {
            for(int j=y; j<y+size; j++) {
                
                if(i >= board.length || j >= board[0].length) {
                    return false;
                }
                
                if(board[i][j] == 0) {
                    return false;
                }
            }
        }
        
        return true;
    }
}