import java.util.Arrays;

class Solution {
    public int solution(int[][] triangle) {
        
        for (int row = 1; row < triangle.length; row++) {
            for (int col = 0; col <= row; col++) {
                int left = col - 1;
                int right = col;
                int max = 0;
                if (left < 0) {
                    max = triangle[row - 1][right];
                } else if (right >= row) {
                    max = triangle[row - 1][left];
                } else {
                    max = Math.max(triangle[row - 1][left], triangle[row - 1][right]);
                }
                triangle[row][col] += max;
            }
        }
        return Arrays.stream(triangle[triangle.length - 1]).max().getAsInt();
    }
}