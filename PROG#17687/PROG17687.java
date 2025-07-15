class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();
    
        String totalStr = "";
        // 1 ~ T*M까지 나열해가며 하나의 String 으로 합친다.
        StringBuilder sb = new StringBuilder();
        int nCheck = 0;
        for(int i=0; i<t*m; i++) {

            if(nCheck >= 10) {
                switch(nCheck) {
                    case 10 :
                        totalStr += (sb.toString() + "A");
                        break;
                    case 11 :
                        totalStr += (sb.toString() + "B");
                        break;
                    case 12 :
                        totalStr += (sb.toString() + "C");
                        break;
                    case 13 :
                        totalStr += (sb.toString() + "D");
                        break;
                    case 14 :
                        totalStr += (sb.toString() + "E");
                        break;
                    case 15 :
                        totalStr += (sb.toString() + "F");
                        break;
                }
            } else {
                totalStr += (sb.toString() + String.valueOf(nCheck));
            }
            
            nCheck++;
            if(nCheck % n == 0) { // N의 배수에 도달하면
                sb.append("1");
                nCheck = 0;
            }
        }
        
        int cnt = 0;
        int endCnt = 0;
        for(int i=0; i<totalStr.length(); i++) {
            cnt++;
            if(cnt == p) {
                answer.append(totalStr.charAt(i));
                endCnt++;
                if(endCnt == t) {
                    break;
                }
            }
            if(cnt == m) {
                cnt = 0;
            }
        }

        
        return answer.toString();
    }
}