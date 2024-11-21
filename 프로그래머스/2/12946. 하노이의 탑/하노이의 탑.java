class Solution {
    
    static final int FROM = 0;
    static final int TO = 1;
    static int index;
    static int[][] answer;
    
    public int[][] solution(int n) {
        // 하노이의 탑 최소 횟수는 (2^n - 1)회
        answer = new int[(int)Math.pow(2, n) - 1][2];
        
        index = 0;
        hanoi(n, 1, 2, 3);
        
        return answer;
    }
    
    /**
	 * 하노이의 탑 원판을 이동하는 메서드
	 * @param number 현재 원판의 번호
	 * @param from 출발할 기둥
	 * @param mid 경유할 기둥
	 * @param to 도착할 기둥
	 */
    static void hanoi(int number, int from, int mid, int to) {
        if (number == 1) {
            answer[index][FROM] = from;
            answer[index++][TO] = to;
            return;
        }
        hanoi(number - 1, from, to, mid);
        answer[index][FROM] = from;
        answer[index++][TO] = to;
        hanoi(number - 1, mid, from, to);
    }
}