import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static final String BLANK = " ";
	public static final char A_CODE = 'a';

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		char[] word = bufferedReader.readLine().toCharArray();
		int[] alphabet = new int[26];

		for (int i = 0; i < word.length; i++) {
			alphabet[word[i] - A_CODE]++;
		}

		for (int i = 0; i < 26; i++) {
			System.out.print(alphabet[i] + BLANK);
		}
		bufferedReader.close();
	}
}
