import java.util.*;

class Solution {
    static int[] retInfo = new int[2];
    public int[] solution(int[][] arr) {
        
        int size = arr.length;
        
        divideAndConquer(0,0, size, arr);
        
        return retInfo;
    }
    
    public int divideAndConquer(int x, int y, int size, int[][] arr) {
        if(size==2) {
            return calculate(x,y,size,arr);
        }
        
        int ret1 = divideAndConquer(x, y, size/2, arr);
        int ret2 = divideAndConquer(x+size/2, y, size/2, arr);
        int ret3 = divideAndConquer(x, y+size/2, size/2, arr);
        int ret4 = divideAndConquer(x+size/2, y+size/2, size/2, arr);
        if(ret1 != -1 && (ret1 == ret2) && (ret2 == ret3) && (ret3 == ret4)) {
            retInfo[ret1] -= 3;
            return ret1;
        }
        
        return -1;
        
    }
    
    public int calculate(int x, int y, int size, int[][] arr) {
        int[] numInfo = new int[2];
        
        for(int i=x; i<x+size; i++) {
            for(int j=y; j<y+size; j++) {
                numInfo[arr[i][j]]++;
            }
        }
        
        if(numInfo[0] == 0 || numInfo[1] == 0) {
            int bingo = numInfo[0] != 0 ? 0 : 1;
            retInfo[bingo] += 1;
            return bingo;
        } else {
            retInfo[0] += numInfo[0];
            retInfo[1] += numInfo[1];
            return -1;
        }
    }
}