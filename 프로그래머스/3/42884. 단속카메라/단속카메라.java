import java.util.Arrays;

class Solution {
    public int solution(int[][] routes) {
        // 고속도로에서 나간 지점 오름차순 정렬
        Arrays.sort(routes, (route1, route2) -> Integer.compare(route1[1], route2[1]));

        int n = routes.length;
        // 차량의 대수만큼 cctv를 놓고 공유하는 cctv만큼 줄여나가면서 확인한다.
        int cctvCount = n;
        boolean[] checked = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            // 다른 cctv를 사용하는(확인해보지 않은) 차량부터 확인한다.
            if (checked[i]) {
                continue;
            }
            checked[i] = true;
            int aCarOut = routes[i][1];
            for (int j = i + 1; j < n; j++) {
                int bCarIn = routes[j][0];
                int bCarOut = routes[j][1];
                // 임의의 차량(a)이 나간 지점을 동시에 이용하고 있는 모든 차(b)들은 같은 cctv를 사용한다.
                if (bCarIn <= aCarOut && aCarOut <= bCarOut) {
                    cctvCount--;
                    checked[j] = true;
                } else {
                    // 겹치는 구간 없음
                    break;
                }
            }
        }
        return cctvCount;
    }
}