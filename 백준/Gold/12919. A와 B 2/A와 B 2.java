import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	static final char A = 'A';
	static final char B = 'B';
	static final boolean FORWARD = true;
	static final boolean REVERSE = false;
	static char[] S, T;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		S = bufferedReader.readLine().toCharArray();
		T = bufferedReader.readLine().toCharArray();

		// S를 T로 바꿀 수 있으면 1을 없으면 0을 출력
		if (search()) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
		bufferedReader.close();
	}

	/**
	 * 문자열 T에서 한 글자씩 줄여가며 S와 같은 문자열이 나올 수 있는지 확인한다.
	 */
	static boolean search() {
		Queue<StringAB> queue = new ArrayDeque<>();
		// 시작 문자열은 T와 같음
		queue.offer(new StringAB(0, T.length - 1, T.length, FORWARD));

		while (!queue.isEmpty()) {
			StringAB now = queue.poll();
			// 현재 문자열과 S의 길이가 같으면
			if (now.length == S.length) {
				// 같은 문자열인지 확인
				if (check(now)) {
					return true;
				} else {
					continue;
				}
			}

			// 경우 1) 첫 글자가 B이면, 뒤집고 해당 B를 제외함
			if (T[now.front] == B) {
				// 역방향 표기였다면
				if (now.direction == REVERSE) {
					// 현재 문자열 범위에서 front와 rear의 위치를 바꾸고(B가 있던 원래 front 위치는 제외), 정방향 표기
					queue.offer(new StringAB(now.rear, now.front - 1, now.length - 1, FORWARD));
				} else { // 정방향 표기였다면
					// 현재 문자열 범위에서 front와 rear의 위치를 바꾸고(B가 있던 원래 front 위치는 제외), 역방향 표기
					queue.offer(new StringAB(now.rear, now.front + 1, now.length - 1, REVERSE));
				}
			}

			// 경우 2) 마지막 글자가 A이면, 해당 A를 제외함
			if (T[now.rear] == A) {
				// 역방향 표기였다면
				if (now.direction == REVERSE) {
					// A가 있던 원래 rear위치 제외하고, 방향 그대로
					queue.offer(new StringAB(now.front, now.rear + 1, now.length - 1, REVERSE));
				} else { // 정방향 표기였다면
					// A가 있던 원래 rear위치 제외하고, 방향 그대로
					queue.offer(new StringAB(now.front, now.rear - 1, now.length - 1, FORWARD));
				}
			}
		}
		return false;
	}

	/**
	 * 해당 문자열과 S가 일치하는지 확인한다.
	 */
	static boolean check(StringAB string) {
		int indexS = 0;

		if (string.direction == REVERSE) {
			for (int i = string.front; i >= string.rear; i--) {
				if (S[indexS] != T[i]) {
					return false;
				}
				indexS++;
			}
		} else {
			for (int i = string.front; i <= string.rear; i++) {
				if (S[indexS] != T[i]) {
					return false;
				}
				indexS++;
			}
		}
		return true;
	}

	static class StringAB {
		int front;
		int rear;
		int length;
		boolean direction;

		/**
		 * @param front 문자열 첫 글자의 인덱스
		 * @param rear 문자열 마지막 글자의 인덱스
		 * @param length 문자열의 길이
		 * @param direction 문자열의 방향 (정방향 : true, 역방향 : false)
		 */
		public StringAB(int front, int rear, int length, boolean direction) {
			this.front = front;
			this.rear = rear;
			this.length = length;
			this.direction = direction;
		}
	}
}
