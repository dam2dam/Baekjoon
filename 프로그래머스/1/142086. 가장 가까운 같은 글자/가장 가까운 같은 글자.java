import java.util.Arrays;

class Solution {
    
    static final int NOT_EXIST = -1;
    static final char a_ASCII = 'a';
        
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        
        // 각 알파벳이 마지막으로 등장했던 인덱스를 저장할 배열 (전부 -1로 초기화)
        int[] alphabets = new int[26];
        Arrays.fill(alphabets, NOT_EXIST);
        
        char[] charString = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            // a ~ z 인덱스: 0 ~ 25 
            int current = charString[i] - a_ASCII;
            // 처음 나왔다면
            if (alphabets[current] == NOT_EXIST) {
                answer[i] = NOT_EXIST;
            // 앞에 있다면
            } else {
                answer[i] = i - alphabets[current];
            }
            // 마지막 위치 갱신
            alphabets[current] = i;
        }
        return answer;
    }
}