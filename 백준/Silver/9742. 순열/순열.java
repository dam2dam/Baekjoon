import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final String NO_PERMUTATION = "No permutation";
	static final String BLANK = " ";
	static final String EQUAL_SIGN = "=";
	static final String ENTER = "\n";

	static char[] word, permutation_word;
	static String answer;
	static int index, permutationCount;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		StringBuilder stringBuilder = new StringBuilder();

		String line;
		while ((line = bufferedReader.readLine()) != null) {
			stringTokenizer = new StringTokenizer(line);
			if (!stringTokenizer.hasMoreTokens()) {
				break;
			}
			word = stringTokenizer.nextToken().toCharArray();
			index = Integer.parseInt(stringTokenizer.nextToken());

			answer = NO_PERMUTATION;
			permutationCount = 0;
			permutation_word = new char[word.length];
			visited = new boolean[word.length];
			permutation(0);

			for (int i = 0; i < word.length; i++) {
				stringBuilder.append(word[i]);
			}
			stringBuilder
				.append(BLANK).append(index)
				.append(BLANK).append(EQUAL_SIGN)
				.append(BLANK).append(answer)
				.append(ENTER);
		}
		System.out.println(stringBuilder);
		bufferedReader.close();
	}

	private static boolean permutation(int count) {

		if (count == word.length) {
			permutationCount++;
			if (permutationCount == index) {
				answer = String.valueOf(permutation_word);
				return true;
			}
			return false;
		}

		for (int i = 0; i < word.length; i++) {
			if (visited[i]) {
				continue;
			}
			permutation_word[count] = word[i];
			visited[i] = true;
			if (permutation(count + 1)) {
				return true;
			}
			visited[i] = false;
		}
		return false;
	}
}
