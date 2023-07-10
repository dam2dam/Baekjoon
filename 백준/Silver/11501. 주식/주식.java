import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final String ENTER = "\n";

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		StringBuilder stringBuilder = new StringBuilder();

		int T = Integer.parseInt(bufferedReader.readLine());
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(bufferedReader.readLine());
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int[] stocks = new int[N];
			for (int i = 0; i < N; i++) {
				stocks[i] = Integer.parseInt(stringTokenizer.nextToken());
			}
			int max = 0;
			long benefit = 0;
			for (int i = N - 1; i >= 0; i--) {
				max = Math.max(max, stocks[i]);
				benefit += max - stocks[i];
			}
			stringBuilder.append(benefit).append(ENTER);
		}
		System.out.println(stringBuilder);
		bufferedReader.close();
	}
}
