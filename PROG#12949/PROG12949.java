

class PROG12949 {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = new int[arr1.length][arr2[0].length];
    
        for(int i=0; i<answer.length; i++) { // 행
            for(int j=0; j<answer[0].length; j++) { // 열
                int value = 0;
                for(int cnt=0; cnt<arr1[0].length; cnt++) {
                    value += arr1[i][cnt]*arr2[cnt][j];
                }
                answer[i][j] = value;
            }
        }
        
        return answer;
        
        
    }
}