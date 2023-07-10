import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		int N = Integer.parseInt(bufferedReader.readLine());
		int[] times = new int[N];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 0; i < N; i++) {
			times[i] = Integer.parseInt(stringTokenizer.nextToken());
		}
		Arrays.sort(times);
		int sum = 0;
		int minTime = 0;
		for (int i = 0; i < N; i++) {
			sum += times[i];
			minTime += sum;
		}
		System.out.println(minTime);
		bufferedReader.close();
	}
}
