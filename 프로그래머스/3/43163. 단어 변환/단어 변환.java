import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int solution(String begin, String target, String[] words) {
        return findShortestSteps(begin, target, words);
    }
    
    static int findShortestSteps(String begin, String target, String[] words) {
        int N = words.length;
        
        Queue<Word> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N];
        
        queue.offer(new Word(begin, 0));
        
        while (!queue.isEmpty()) {
            Word word = queue.poll();
            if (word.value.equals(target)) {
                return word.count;
            }
            for (int i = 0; i < N; i++) {
                if (visited[i]) {
                    continue;
                }
                String nextWord = words[i];
                if (canChange(word.value, nextWord)) {
                    queue.offer(new Word(nextWord, word.count + 1));
                    visited[i] = true;
                }
            }
        }
        return 0;
    }
    
    /**
    * 한 개의 알파벳만 다른 단어인지 확인한다.
    * @return 단어 변경 가능 여부
    */
    static boolean canChange(String word, String nextWord) {
        int diffCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != nextWord.charAt(i)) {
                diffCount++;
            }
            if (diffCount > 1) {
                return false;
            }
        }
        if (diffCount == 1) {
            return true;
        }
        return false;
    }
    
    static class Word {
        String value;
        int count;
        
        public Word(String value, int count) {
            this.value = value;
            this.count = count;
        }
    }
}