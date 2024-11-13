import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final String ENTER = "\n";

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder answer = new StringBuilder();

		// 테스트 케이스의 수
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 0; testcase < T; testcase++) {
			int numberofCoins = Integer.parseInt(br.readLine());
			// 동전의 종류
			int[] coins = new int[numberofCoins + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= numberofCoins; i++) {
				coins[i] = Integer.parseInt(st.nextToken());
			}
			// 만들어야 할 금액
			int amount = Integer.parseInt(br.readLine());

			/*
			 * dp 배열
			 * index : 금액
			 * value : 이 금액을 만들 수 있는 경우의 수
			 */
			int[] dp = new int[amount + 1];

			for (int coinIndex = 1; coinIndex <= numberofCoins; coinIndex++) {
				// 아무 동전도 선택하지 않으면 0원이므로 1가지 경우가 있다
				dp[0] = 1;
				// 현재 고려할 동전 (이 동전으로 금액을 만들어 보자)
				int currentCoin = coins[coinIndex];
				for (int currentAmount = currentCoin; currentAmount <= amount; currentAmount++) {
					// dp[currentAmount] : 직전 동전까지 고려했을 때의 경우의 수 == 금액에 현재 동전을 포함하지 않을 때의 경우의 수
					// dp[currentAmount - currentCoin] : 현재 금액에서 현재 동전만큼의 금액이 없을 때의 경우의 수 == 금액에 현재 동전을 포함할 때의 경우의 수
					dp[currentAmount] += dp[currentAmount - currentCoin];
				}
			}
			answer.append(dp[amount]).append(ENTER);
		}
		System.out.println(answer);
		br.close();
	}
}
