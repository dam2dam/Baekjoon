import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bufferedReader.readLine());
		long num1 = 0;
		long num2 = 1;
		long sum;
		for (int i = 2; i <= N; i++) {
			sum = num1 + num2;
			num1 = num2;
			num2 = sum;
		}
		System.out.println(num2);
		bufferedReader.close();
	}
}
