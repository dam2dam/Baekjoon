import java.util.Arrays;

class Solution {
    public int solution(String[] words) {
        int answer = 0;
        // 정렬 (사전순)
        Arrays.sort(words);
        
        int N = words.length;
        int[] counts = new int[N];
        for (int i = 0; i < N - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            int shorterLength = Math.min(word1.length(), word2.length());
            int sameLength = getSameLength(word1, word2, shorterLength);
            
            // 긴 문자열이 짧은 문자열을 포함하고 있으면 짧은 문자열 길이만큼,
            // 아니면 공통 문자열 길이 + 1까지 검색해야 한다.
            if (sameLength == shorterLength) {
                counts[i] = Math.max(counts[i], sameLength);
            } else {
                counts[i] = Math.max(counts[i], sameLength + 1);
            }
            // 이전 문자열과 다음 문자열 중에 더 공통 문자열이 긴만큼 영향을 받는다.
            counts[i + 1] = Math.max(counts[i + 1], sameLength + 1);
        }
        for (int i = 0; i < N; i++) {
            answer += counts[i];
        }
        return answer;
    }
    
    static int getSameLength(String word1, String word2, int shorterLength) {
        int sameCount = 0;
        for (int i = 0; i < shorterLength; i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                return sameCount;
            }
            sameCount++;
        }
        return sameCount;
    }
}