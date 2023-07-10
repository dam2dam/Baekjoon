import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bufferedReader.readLine());
		int[] ropes = new int[N];
		for (int i = 0; i < N; i++) {
			ropes[i] = Integer.parseInt(bufferedReader.readLine());
		}
		Arrays.sort(ropes);
		int max = 0;
		for (int i = 0; i < N; i++) {
			max = Math.max(max, ropes[i] * (N - i));
		}
		System.out.println(max);
		bufferedReader.close();
	}
}
