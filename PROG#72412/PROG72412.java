import java.util.*;


class PROG72412 {
    
    static Map<String,ArrayList<Integer>> infoMap;
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        infoMap = new HashMap<>();
        
        for(int i=0; i<info.length; i++) {
            String[] infoSplit = info[i].split(" ");
            comb(0,infoSplit,"");
        }
        
        List<String> keyList = new ArrayList<>(infoMap.keySet());
        for(String key : keyList) {
            ArrayList<Integer> scoreList = infoMap.get(key);
            Collections.sort(scoreList);
        }
        
        for(int i=0; i<query.length; i++) {
            String removeAnd = query[i].replace(" and ", "");
            String[] queryAndScore = removeAnd.split(" ");
            int score = Integer.parseInt(queryAndScore[1]);
            if(infoMap.containsKey(queryAndScore[0])) {
                ArrayList<Integer> scoreList = infoMap.get(queryAndScore[0]);    
                int cnt = 0;
                for(int j=0; j<scoreList.size(); j++) {
                    if(scoreList.get(j) >= score) {
                        cnt++;
                    }
                }
                answer[i] = cnt;
                
            }
        }
        
        return answer;
    }
    
    public void comb(int cnt, String[] info, String str) {
        if(cnt == 4) {
            int score = Integer.parseInt(info[4]);
            if(infoMap.containsKey(str)) {
                infoMap.get(str).add(score);
            } else {
                ArrayList<Integer> scoreList = new ArrayList<>();
                scoreList.add(score);
                infoMap.put(str,scoreList);
            }
            return;
        }
        
        comb(cnt+1, info, str+"-");
        comb(cnt+1, info, str+info[cnt]);
    }
}