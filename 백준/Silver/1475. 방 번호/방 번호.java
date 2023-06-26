import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		char[] roomNumber = bufferedReader.readLine().toCharArray();
		int[] numbers = new int[10];

		for (int i = 0; i < roomNumber.length; i++) {
			numbers[roomNumber[i] - '0']++;
		}
		int sum = numbers[6] + numbers[9];
		numbers[6] = numbers[9] = sum / 2 + sum % 2;
		Arrays.sort(numbers);

		System.out.println(numbers[9]);
		bufferedReader.close();
	}
}
