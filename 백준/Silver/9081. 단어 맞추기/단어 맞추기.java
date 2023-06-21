import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static final String ENTER = "\n";

	static char[] input, word;
	static int wordLength;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder stringBuilder = new StringBuilder();

		int T = Integer.parseInt(bufferedReader.readLine());
		for (int tc = 0; tc < T; tc++) {
			input = bufferedReader.readLine().toCharArray();
			wordLength = input.length;
			word = Arrays.copyOf(input, wordLength);

			if (nextPermutation()) {
				stringBuilder.append(String.valueOf(word));
			} else {
				stringBuilder.append(String.valueOf(input));
			}
			stringBuilder.append(ENTER);
		}
		System.out.println(stringBuilder);
		bufferedReader.close();
	}

	private static boolean nextPermutation() {

		// i : (뒤에서부터) 오름차순이 깨지는 직전 값
		int i = wordLength - 1;
		while (i > 0 && word[i - 1] >= word[i]) {
			i--;
		}
		// 맨 끝 값일 경우
		if (i == 0) {
			return false;
		}

		// j : (뒤에서부터) i-1 값보다 큰 값 중 가장 가까운 값
		int j = wordLength - 1;
		while (word[i - 1] >= word[j]) {
			j--;
		}

		// 교환
		char temp = word[i - 1];
		word[i - 1] = word[j];
		word[j] = temp;

		// i-1 뒤의 값 재정렬
		int k = wordLength - 1;
		while (i < k) {
			temp = word[i];
			word[i] = word[k];
			word[k] = temp;
			i++;
			k--;
		}
		return true;
	}
}