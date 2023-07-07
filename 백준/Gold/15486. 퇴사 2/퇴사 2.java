import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		int N = Integer.parseInt(bufferedReader.readLine());
		Plan[] plans = new Plan[N + 1];
		int time, pay;
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			time = Integer.parseInt(stringTokenizer.nextToken());
			pay = Integer.parseInt(stringTokenizer.nextToken());
			plans[i] = new Plan(time, pay);
		}
		int[] dp = new int[N + 1];
		for (int today = 0; today < N; today++) {
			int requiredDays = plans[today].time;
			if (today + requiredDays <= N) {
				dp[today + requiredDays] = Math.max(dp[today + requiredDays], dp[today] + plans[today].pay);
			}
			dp[today + 1] = Math.max(dp[today], dp[today + 1]);
		}
		System.out.println(dp[N]);
		bufferedReader.close();
	}

	static class Plan {
		int time, pay;

		public Plan(int time, int pay) {
			this.time = time;
			this.pay = pay;
		}
	}
}
