import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static final int YES = 1;
	static final int NO = 0;
	static final String ENTER = "\n";

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		StringBuilder answer = new StringBuilder();

		int N = Integer.parseInt(bufferedReader.readLine());
		int[] numbers = new int[N + 1];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 1; i <= N; i++) {
			numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
		}

		// 팰린드롬 배열
		// ex) palindrome[i][j] => i번째~j번째로 이루어진 부분 문자열은 팰린드롬이다
		boolean[][] palindrome = new boolean[N + 1][N + 1];
		searchPalindrome(N, numbers, palindrome);

		int M = Integer.parseInt(bufferedReader.readLine());
		for (int i = 0; i < M; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int start = Integer.parseInt(stringTokenizer.nextToken());
			int end = Integer.parseInt(stringTokenizer.nextToken());
			if (palindrome[start][end]) {
				answer.append(YES).append(ENTER);
			} else {
				answer.append(NO).append(ENTER);
			}
		}
		System.out.println(answer);
		bufferedReader.close();
	}

	static void searchPalindrome(int N, int[] numbers, boolean[][] palindrome) {

		for (int i = 1; i <= N; i++) {
			// 1. 길이가 1인 부분 수열(자기자신) => 모두 팰린드롬이다
			palindrome[i][i] = true;
			// 2. 길이가 2인 부분 수열 => 바로 앞의 수와 같으면 팰린드롬이다
			if (numbers[i - 1] == numbers[i]) {
				palindrome[i - 1][i] = true;
			}
		}
		// 3. 길이가 3 이상인 부분 수열 => 시작과 끝이 같고, 그 가운데 수열이 팰린드롬이면 팰린드롬이다
		for (int gap = 2; gap < N; gap++) {
			for (int startIndex = 1; startIndex <= N - gap; startIndex++) {
				int endIndex = startIndex + gap;
				if (numbers[startIndex] == numbers[endIndex] && palindrome[startIndex + 1][endIndex - 1]) {
					palindrome[startIndex][endIndex] = true;
				}
			}
		}
	}
}
