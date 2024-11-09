import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static char A = 'A';
	static char B = 'B';
	static int POSSIBLE = 1;
	static int IMPOSSIBLE = 0;

	static char[] S, T;
	static boolean isBackward;
	static int N, left, right;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine().toCharArray();
		T = br.readLine().toCharArray();

		N = T.length;
		isBackward = false;
		left = 0;
		right = N - 1;

		removeTail(T);
		System.out.println(compareCharArrays(S, T));

		br.close();
	}

	/**
	 *  A 문자열의 길이와 같을 때까지 T 문자열 끝에서 A나 B를 하나씩 제거한다
	 *  (인덱스가 줄어드는 만큼 제거한다는 의미이다)
	 * @param targetString
	 */
	static void removeTail(char[] targetString) {
		// 제거할 알파벳의 인덱스
		int index = N - 1;
		for (int i = 0; i < N - S.length; i++) {
			if (targetString[index] == A) {    // 제거할 알파벳이 A라면
				if (isBackward) {    // 역방향이라면
					index = ++left;    // 왼쪽에서 없앤다
				} else {    // 정방향이라면
					index = --right;    // 오른쪽에서 없앤다
				}
			} else if (targetString[index] == B) {    // 제거할 알파벳이 B라면
				if (isBackward) {   // 역방향이라면
					left++;    // 왼쪽에서 없애고
					index = right;    // 방향을 바꾼다
				} else {    // 정방향이라면
					right--;    // 오른쪽에서 없애고
					index = left;    // 방향을 바꾼다
				}
				isBackward = !isBackward;    // 방향을 바꾼다
			}
		}
	}

	/**
	 * S 문자열의 길이만큼 줄어든 T 문자열이 S와 같은 문자열인지 비교한다
	 * @param startString
	 * @param targetString
	 * @return S 문자열과 T 문자열의 일치 여부
	 */
	static int compareCharArrays(char[] startString, char[] targetString) {
		int index = 0;

		// 역방향이라면
		if (isBackward) {
			// T 문자열의 오른쪽 -> 왼쪽 순서로 비교
			for (int i = right; i >= left; i--) {
				// 하나라도 다르면 불일치 반환
				if (startString[index++] != targetString[i]) {
					return IMPOSSIBLE;
				}
			}
		} else {    // 정방향이라면
			// T 문자열의 왼쪽 -> 오른쪽 순서로 비교
			for (int i = left; i <= right; i++) {
				// 하나라도 다르면 불일치 반환
				if (startString[index++] != targetString[i]) {
					return IMPOSSIBLE;
				}
			}
		}
		return POSSIBLE;
	}
}
