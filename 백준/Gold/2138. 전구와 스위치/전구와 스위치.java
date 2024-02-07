import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static final char ASCII_ZERO = '0';
	static final int SWITCH = 1;
	static final int NO_SWITCH = 2;
	static final int IMPOSSIBLE = -1;
	static int N;
	static int[] state1, state2, target;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bufferedReader.readLine());
		state1 = new int[N];
		state2 = new int[N];
		target = new int[N];

		String inputState = bufferedReader.readLine();
		String inputTarget = bufferedReader.readLine();
		for (int i = 0; i < N; i++) {
			state1[i] = inputState.charAt(i) - ASCII_ZERO;
			state2[i] = state1[i];
			target[i] = inputTarget.charAt(i) - ASCII_ZERO;
		}
		// 경우 1) 첫번째 스위치를 누르지 않았을 때
		int count1 = switchOnOff(state1, NO_SWITCH);
		// 경우 2) 첫번째 스위치를 눌렀을 때
		state2[0] ^= SWITCH;
		state2[1] ^= SWITCH;
		int count2 = switchOnOff(state2, SWITCH);

		// 두 경우 모두 가능하다면 최소 횟수 출력
		if (count1 != IMPOSSIBLE && count2 != IMPOSSIBLE) {
			System.out.println(Math.min(count1, count2));
		} else {
			// 둘 중에 하나 이상 불가능하다면 큰 수 출력 (-1과 비교되므로)
			System.out.println(Math.max(count1, count2));
		}
		bufferedReader.close();
	}

	static int switchOnOff(int[] state, int switched) {
		int result = 0;

		int count = 0;
		for (int i = 1; i < N; i++) {
			// 왼쪽 전구가 목표 상태와 다르다면 현재 스위치 누름
			if (state[i - 1] != target[i - 1]) {
				state[i - 1] ^= SWITCH;
				state[i] ^= SWITCH;
				if (i < N - 1) {
					state[i + 1] ^= SWITCH;
				}
				count++;
			}
		}
		// 목표 상태와 같다면
		if (check(state)) {
			// 첫번째 스위치를 눌렀을 경우 횟수 +1 반환
			if (switched == SWITCH) {
				result = count + 1;
			} else if (switched == NO_SWITCH) {
				result = count;
			}
		} else {
			// 목표 상태와 다르다면 -1 반환
			result = IMPOSSIBLE;
		}
		return result;
	}

	/**
	 * 현재 상태 배열과 목표 상태 배열이 일치하는지 확인한다.
	 */
	static boolean check(int[] state) {
		for (int i = 0; i < N; i++) {
			if (state[i] != target[i]) {
				return false;
			}
		}
		return true;
	}
}
