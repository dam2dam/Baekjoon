import java.util.LinkedHashMap;
import java.util.Map;

class Solution {
    
    static final String NOTHING = "-";
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {      
        int n = enroll.length;
        // 출력시 순서를 위해 LinkedHashMap 사용 
        // 이름 : (이름, 이익금, 추천인)
        // 추천인이 있으면 기록하고 없으면 null이 들어간다.
        Map<String, Profile> profiles = new LinkedHashMap<>(n);
        for (int i = 0; i < n; i++) {
            if (hasReferral(referral[i])) {
                profiles.put(enroll[i], new Profile(enroll[i], 0, referral[i]));                
            } else {
                profiles.put(enroll[i], new Profile(enroll[i], 0));
            }
        }
        // 판매 이익에서 90%를 갖고, 10%는 추천인의 이익금을 계산하기 위해 total로 옮겨 추천인이 없을 때까지 반복한다.
        sell:
        for (int i = 0; i < seller.length; i++) {
            Profile current = profiles.get(seller[i]);
            int total = amount[i] * 100;
            String refer = current.referName;
            
            while (refer != null) {
                int fee = (int) Math.floor(total * 0.1);
                current.margin += total - fee;
                
                current = profiles.get(refer);
                total = fee;
                refer = current.referName;
                if (total == 0) {
                    continue sell;
                }
            }
            current.margin += total - (int) Math.floor(total * 0.1);
        }
        // 이익금의 총합 배열
        int[] answer = new int[n];
        int index = 0;
        for (String name : profiles.keySet()) {
            answer[index++] = profiles.get(name).margin;
        }
        return answer;
    }
    
    
    static boolean hasReferral(String name) {
        return !name.equals(NOTHING);
    }
    
    static class Profile {
        String name;
        int margin;
        String referName;
        
        public Profile(String name, int margin) {
            this.name = name;
            this.margin = margin;
            this.referName = null;
        }
        
        public Profile(String name, int margin, String referName) {
            this.name = name;
            this.margin = margin;
            this.referName = referName;
        }
    }
}