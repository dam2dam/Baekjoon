class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        // (가로 길이 >= 세로 길이) 이기 때문에 노란색의 제곱근까지만 확인
        int yellowSqrt = (int) Math.sqrt(yellow);
        for (int num = 1; num <= yellowSqrt; num++) {
            // 노란색을 사각형으로 배열할 수 있다면 (yellow의 약수)
            if (yellow % num == 0) {
                // 카펫의 세로 길이 == 노란색의 세로 길이 + 2
                int r = num + 2;
                // 카펫의 가로 길이 == 노란색의 가로 길이 + 2
                int c = yellow / num + 2;
                // 전체 카펫의 세로 길이와 가로 길이의 곱이 전체 격자의 수와 일치한다면
                if (r * c == brown + yellow) {
                    answer[0] = c;
                    answer[1] = r;
                    break;
                }
            }
        }
        return answer;
    }
}