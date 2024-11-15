import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int ANSWER_MAX_VALUE = 1_000_000_000;
	static final int ANSWER_MIN_VALUE = -1_000_000_000;
	static final int PLUS = 0;
	static final int MINUS = 1;
	static final int MUTIPLY = 2;
	static final int DIVIDE = 3;

	static int N, min, max;
	static int[] nums, operators;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		// 수열 입력받기
		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		// 각 연산자의 개수 입력받기
		operators = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			operators[i] = Integer.parseInt(st.nextToken());
		}
		min = ANSWER_MAX_VALUE;
		max = ANSWER_MIN_VALUE;
		makeExpression(nums[0], 1, 0, 0, 0, 0);
		System.out.println(max);
		System.out.println(min);

		br.close();
	}

	/**
	 * 수식을 만드는 메서드
	 * @param value 현재까지 만든 수식의 값
	 * @param operandCount 사용한 숫자(피연산자)의 개수
	 * @param plusCount 사용한 덧셈(+)의 개수
	 * @param minusCount 사용한 뺄셈(-)의 개수
	 * @param multiCount 사용한 곱셈(×)의 개수
	 * @param divCount 사용한 나눗셈(÷)의 개수
	 */
	static void makeExpression(int value, int operandCount,
		int plusCount, int minusCount, int multiCount, int divCount) {

		// 수식이 완성되었다면 최솟값과 최댓값 갱신
		if (operandCount == N) {
			if (value < min) {
				min = value;
			}
			if (value > max) {
				max = value;
			}
			return;
		}

		// 덧셈을 사용할 수 있다면
		if (plusCount < operators[PLUS]) {
			makeExpression(value + nums[operandCount], operandCount + 1,
				plusCount + 1, minusCount, multiCount, divCount);
		}
		// 뺄셈을 사용할 수 있다면
		if (minusCount < operators[MINUS]) {
			makeExpression(value - nums[operandCount], operandCount + 1,
				plusCount, minusCount + 1, multiCount, divCount);
		}
		// 곱셈을 사용할 수 있다면
		if (multiCount < operators[MUTIPLY]) {
			makeExpression(value * nums[operandCount], operandCount + 1,
				plusCount, minusCount, multiCount + 1, divCount);
		}
		// 나눗셈을 사용할 수 있다면
		if (divCount < operators[DIVIDE]) {
			// 나누려는 숫자가 양수이면 나눈 몫을, 음수이면 양수로 바꿔서 나눈 몫을 다시 음수로 바꿔서 사용한다
			makeExpression(value > 0 ? value / nums[operandCount] : -((-value) / nums[operandCount]), operandCount + 1,
				plusCount, minusCount, multiCount, divCount + 1);
		}

	}
}
