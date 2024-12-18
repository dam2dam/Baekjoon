import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {
    
    final int PATH = 1;
    final int IN = 2;
    final int[] dx = {0, 1, 0, -1};
    final int[] dy = {1, 0, -1, 0};
    
    int[][] map;
    int itemX, itemY;
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        this.itemX = itemX * 2;
        this.itemY = itemY * 2;
        makeMap(rectangle);
        return move(characterX * 2, characterY * 2);
    }
    
    /**
     * 다각형의 테두리를 따라 bfs를 수행하고 아이템까지의 최단 경로를 기록한다.
     * (좌표 * 2)인 상태이기 때문에 (최단 경로 / 2)를 반환한다.
     */
    int move(int x, int y) {
        int minDistance = 2500;
        Queue<Point> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[101][101];
        
        queue.offer(new Point(x, y, 0));
        visited[x][y] = true;
        
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            
            if (now.x == itemX && now.y == itemY) {
                if (minDistance > now.count) {
                    minDistance = now.count;
                }
                continue;
            }
            for (int d = 0; d < 4; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];
                if (!inRange(nx, ny) || map[nx][ny] != PATH || visited[nx][ny]) {
                    continue;
                }
                visited[nx][ny] = true;
                queue.offer(new Point(nx, ny, now.count + 1));
            }
        }
        return minDistance / 2;
    }
    
    boolean inRange(int x, int y) {
        return x > 0 && x <= 100 && y > 0 && y <= 100;
    }
    
    /**
     * 직사각형을 좌표에 그려서 다각형을 표시한다.
     * 좌표를 칸으로 표시할 때 겹치는 부분에 의해 다각형의 형태가 달라지기 때문에 (좌표 * 2)로 표시한다.
     * 다각형의 테두리는 1, 안쪽은 2로 표시한다.
     */
    void makeMap(int[][] rectangle) {
        map = new int[101][101];
        for (int[] now : rectangle) {
            int bottomLeftX = now[0] * 2;
            int bottomLeftY = now[1] * 2;
            int upperRightX = now[2] * 2;
            int upperRightY = now[3] * 2;
            
            for (int x = bottomLeftX; x <= upperRightX; x++) {
                for (int y = bottomLeftY; y <= upperRightY; y++) {
                    if (map[x][y] == IN) {
                        continue;
                    }
                    if (x == bottomLeftX || x == upperRightX || y == bottomLeftY || y == upperRightY) {
                        map[x][y] = PATH;
                        continue;
                    }
                    map[x][y] = IN;
                }
            }
        }
    }
    
    class Point {
        int x;
        int y;
        int count;
        
        public Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}