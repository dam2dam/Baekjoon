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
		int[] A = new int[N];
		int[] B = new int[N];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(stringTokenizer.nextToken());
		}
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 0; i < N; i++) {
			B[i] = Integer.parseInt(stringTokenizer.nextToken());
		}
		Arrays.sort(A);
		Arrays.sort(B);
		int answer = 0;
		for (int i = 0; i < N; i++) {
			answer += A[i] * B[N - 1 - i];
		}
		System.out.println(answer);
		bufferedReader.close();
	}
}
