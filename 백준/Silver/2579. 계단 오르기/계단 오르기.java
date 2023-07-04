import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int N;
	static int[] stairs, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(bufferedReader.readLine());

		stairs = new int[N+1];
		for (int i = 1; i <= N; i++) {
			stairs[i] = Integer.parseInt(bufferedReader.readLine());
		}
//		System.out.println(Arrays.toString(stairs));
		
		dp = new int[N+1];
		dp[1] = stairs[1];
		
		for (int currentStair = 2; currentStair <= N; currentStair++) {
			if (currentStair == 2) {
				dp[currentStair] = stairs[currentStair-1] + stairs[currentStair];
			}
			else if (currentStair == 3) {
				dp[currentStair] = Math.max(stairs[currentStair-1], stairs[currentStair-2]) + stairs[currentStair];
			}
			else {
				dp[currentStair] = Math.max(dp[currentStair-3] + stairs[currentStair-1], dp[currentStair-2]) + stairs[currentStair];
			}
		}
//		System.out.println(Arrays.toString(dp));
		System.out.println(dp[N]);
	}
}
