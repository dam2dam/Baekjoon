class Solution {
    
    static int n;
    static int[] count01;
    static int[][] board;
    static Point[][] compressed;
    
    public int[] solution(int[][] arr) {
        n = arr.length;
        board = arr;
        // 압축된 칸의 정보
        compressed = new Point[n][n];
        // 0의 개수와 1의 개수를 담은 배열
        count01 = new int[2];
        // 압축
        compress(n, 0, 0);
        // 0과 1 세기
        count();
        
        return count01;
    }
    
	/**
	 * 압축하는 메서드
	 * @param size 현재 영역 크기
	 * @param row 현재 영역 기준 행 (왼쪽 위)
	 * @param col 현재 영역 기준 열 (왼쪽 위)
	 * @return 압축 가능 여부와 압축된 영역의 값이 담긴 Result 객체
	 */
    static Result compress(int size, int row, int col) {
        int value = board[row][col];
		// 가장 작은 2 × 2 칸이라면
		if (size == 2) {
			// 압축이 가능하다면
			if (isAllSame(row, col)) {
				// 압축 정보 기록 후 true 반환
				compressed[row][col] = new Point(size, value);
				return new Result(true, value);
			}
            // 압축 불가 false 반환
			return new Result(false, -1);
		}
        
        // 현재 영역을 4등분
		int half = size / 2;
		Result result1 = compress(half, row, col);
		Result result2 = compress(half, row, col + half);
		Result result3 = compress(half, row + half, col);
		Result result4 = compress(half, row + half, col + half);

        // 4등분 한 영역 모두 압축에 성공했고, 값이 모두 같다면
		if (result1.isPossible && result2.isPossible && result3.isPossible && result4.isPossible
            && result1.value == result2.value && result2.value == result3.value && result3.value == result4.value) {
            // 현재 영역 압축
			compressed[row][col].size = size;
			compressed[row][col + half] = null;
			compressed[row + half][col] = null;
			compressed[row + half][col + half] = null;
			return new Result(true, value);
		}
		return new Result(false, -1);
	}

	/**
	 * 영역의 값이 모두 같은지 판단하는 메서드
	 * @param row 현재 영역 기준 행 (왼쪽 위)
	 * @param col 현재 영역 기준 열 (왼쪽 위)
	 * @return 모든 영역 값 동일 여부
	 */
	static boolean isAllSame(int row, int col) {
		int value1 = board[row][col];
		int value2 = board[row][col + 1];
		int value3 = board[row + 1][col];
		int value4 = board[row + 1][col + 1];
        // 0과 1의 개수 기록
		count01[value1]++;
		count01[value2]++;
		count01[value3]++;
		count01[value4]++;
		return value1 == value2 && value2 == value3 && value3 == value4;
	}
    
    /**
	 * 압축된 영역을 반영하여 0과 1의 개수를 세는 메서드
	 */
	static void count() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				Point point = compressed[i][j];
                // 압축되지 않은 영역은 넘어감
				if (point == null) {
					continue;
				}
                // 압축된 영역은 사이즈만큼 개수를 줄임
				count01[point.value] -= point.size * point.size - 1;
			}
		}
	}
    
    static class Point {
        int size;
        int value;
        
        /**
		 * @param size 압축된 영역 크기
		 * @param value 압축된 영역의 값
		 */
        public Point(int size, int value) {
            this.size = size;
            this.value = value;
        }
    }
    
    static class Result {
        boolean isPossible;
        int value;
        
		/**
		 * @param isPossible 압축 가능 여부
		 * @param value 압축된 영역의 값
		 */
        public Result(boolean isPossible, int value) {
            this.isPossible = isPossible;
            this.value = value;
        }
    }
}