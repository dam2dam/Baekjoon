import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final String BLANK = " ";

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		int groundSize = Integer.parseInt(stringTokenizer.nextToken());
		int theNumberofTrainer = Integer.parseInt(stringTokenizer.nextToken());

		int[] nums = new int[groundSize + 1];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 1; i <= groundSize; i++) {
			nums[i] = Integer.parseInt(stringTokenizer.nextToken());
		}

		int start, end, changeAmount;
		int[] prefixArray = new int[groundSize + 2];
		for (int i = 0; i < theNumberofTrainer; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			start = Integer.parseInt(stringTokenizer.nextToken());
			end = Integer.parseInt(stringTokenizer.nextToken());
			changeAmount = Integer.parseInt(stringTokenizer.nextToken());

			prefixArray[start] += changeAmount;
			prefixArray[end + 1] -= changeAmount;
		}

		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 1; i <= groundSize; i++) {
			prefixArray[i] += prefixArray[i - 1];
			nums[i] += prefixArray[i];
			stringBuilder.append(nums[i]).append(BLANK);
		}
		System.out.println(stringBuilder);
		bufferedReader.close();
	}
}
