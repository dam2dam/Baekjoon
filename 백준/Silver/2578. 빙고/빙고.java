import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int BOARD_SIZE = 5;
	static final int WIN_COUNT = 3;
	public static final int MINIMUM_CALL = 11;

	static int[] number;
	static boolean[][] board;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		number = new int[BOARD_SIZE * BOARD_SIZE + 1];
		int input;
		for (int i = 0; i < BOARD_SIZE; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < BOARD_SIZE; j++) {
				input = Integer.parseInt(stringTokenizer.nextToken());
				// number의 index => 빙고 보드의 숫자 값
				// number의 value => 빙고 보드의 인덱스를 1차원 배열 인덱스로 표현
				number[input] = ((i * BOARD_SIZE) + j) + 1;
			}
		}

		int call;
		int point;
		int r, c;
		int bingoCount;
		board = new boolean[BOARD_SIZE][BOARD_SIZE];
		for (int i = 0; i < BOARD_SIZE; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < BOARD_SIZE; j++) {
				// 사회자가 부른 숫자 빙고 보드에 표시
				call = Integer.parseInt(stringTokenizer.nextToken());
				point = number[call] - 1;
				r = point / BOARD_SIZE;
				c = point % BOARD_SIZE;
				board[r][c] = true;

				// 빙고가 가능한 만큼 부르면 빙고 개수 세기
				if (i * BOARD_SIZE + j + 1 >= MINIMUM_CALL) {
					bingoCount = checkBingo();
					if (bingoCount >= WIN_COUNT) {
						System.out.println(i * BOARD_SIZE + j + 1);
						System.exit(0);
					}
				}
			}
		}
		bufferedReader.close();
	}

	private static int checkBingo() {
		int bingoCount = 0;

		int rowCount;
		int colCount;
		int ascCount = 0;
		int descCount = 0;

		int index = 0;
		for (int i = 0; i < BOARD_SIZE; i++) {
			rowCount = 0;
			colCount = 0;
			for (int j = 0; j < BOARD_SIZE; j++) {
				// 가로
				if (board[i][j]) {
					rowCount++;
				}
				// 세로
				if (board[j][i]) {
					colCount++;
				}
				// 왼쪽 위 -> 오른쪽 아래 대각선
				if (i == j && board[i][j]) {
					descCount++;
				}
				// 오른쪽 위 -> 왼쪽 아래 대각선
				if (i == index && j == ((BOARD_SIZE - 1) - index) && board[i][j]) {
					ascCount++;
					index++;
				}
			}
			if (rowCount == BOARD_SIZE) {
				bingoCount++;
			}
			if (colCount == BOARD_SIZE) {
				bingoCount++;
			}
		}
		if (ascCount == BOARD_SIZE) {
			bingoCount++;
		}
		if (descCount == BOARD_SIZE) {
			bingoCount++;
		}
		return bingoCount;
	}
}
