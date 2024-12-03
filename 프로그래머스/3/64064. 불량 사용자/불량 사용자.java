import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    
    static final char STAR = '*';
    
    static int numberOfUser, numberOfbanned, caseCount;
    static boolean[] picked;
    static String[] userId, bannedId;
    
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;

        numberOfUser = user_id.length;        
        numberOfbanned = banned_id.length;
        userId = user_id;
        bannedId = banned_id;
        picked = new boolean[(int) Math.pow(2, numberOfUser)];
        combination();
        return caseCount;
    }
    
    static void combination() {
        Queue<Pick> queue = new ArrayDeque<>();
        queue.offer(new Pick(0, 0, 0));
        
        while (!queue.isEmpty()) {
            Pick pick = queue.poll();
            
            if (pick.count == numberOfbanned) {
                // 아이디들이 나열된 순서와 관계없이 아이디 목록의 내용이 동일하다면 같은 것으로 처리
                if (!picked[pick.state]) {
                    picked[pick.state] = true;
                    caseCount++;
                }
                continue;
            }
            for (int userIndex = pick.index; userIndex < numberOfbanned; userIndex++) {
				for (int bannedIndex = 0; bannedIndex < numberOfUser; bannedIndex++) {
                    // 제재 아이디 목록에서 이미 매칭한 아이디는 패스
					if ((pick.state & (1 << bannedIndex)) > 0) {
						continue;
					}
					if (canMatch(userId[bannedIndex], bannedId[userIndex])) {
						queue.offer(new Pick(userIndex + 1, pick.count + 1, pick.state | (1 << bannedIndex)));
					}
				}
			}
        }
    }
    
    static boolean canMatch(String user, String banned) {
        if (user.length() != banned.length()) {
            return false;
        }
        for (int i = 0; i < user.length(); i++) {
            if (banned.charAt(i) == STAR) {
                continue;
            }
            if (user.charAt(i) != banned.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    
    static class Pick {
        int index;
        int count;
        int state;
        
        public Pick(int index, int count, int state) {
            this.index = index;
            this.count = count;
            this.state = state;
        }
    }
}