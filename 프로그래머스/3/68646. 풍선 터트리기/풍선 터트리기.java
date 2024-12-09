class Solution {
    
    public int solution(int[] a) {
        int N = a.length;
        
        int[] minOnLeft = new int[N];
        findMinOnLeft(N, a, minOnLeft);
        
        int[] minOnRight = new int[N];
        findMinOnRight(N, a, minOnRight);
        
        // 자신의 왼쪽과 오른쪽 모두 번호가 작은 풍선만 있다면 마지막까지 남을 수 없다.
        // 가장 왼쪽 풍선과 가장 오른쪽 풍선은 항상 마지막까지 남을 수 있으므로 2로 초기화 한다.
        int answer = 2;
        for (int i = 1; i < N - 1; i++) {
            if (a[i] > minOnLeft[i] && a[i] > minOnRight[i]) {
                continue;
            }
            answer++;
        }    
        return answer;
    }
    
    // 현재 위치보다 왼쪽에 있는 값 중, 최솟값을 기록한다.
    static void findMinOnLeft(int N, int[] a, int[] minOnLeft) {
        int min = a[0];
        for (int i = 1; i < N; i++) {
            minOnLeft[i] = min;
            if (min > a[i]) {
                min = a[i];
            }
        }
    }
    
    // 현재 위치보다 오른쪽에 있는 값 중, 최솟값을 기록한다.
    static void findMinOnRight(int N, int[] a, int[] minOnRight) {
        int min = a[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            minOnRight[i] = min;
            if (min > a[i]) {
                min = a[i];
            }
        }
    }
}