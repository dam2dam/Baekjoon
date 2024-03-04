import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bufferedReader.readLine());
		// 입력이 1이라면 소수의 합으로 나타낼 수 없으므로 바로 끝낸다
		if (N == 1) {
			System.out.println(0);
			return;
		}

		ArrayList<Integer> prime = findPrime(N);
		int left = 0;
		int right = 0;
		int sum = prime.get(0);
		int count = 0;

		int numberofPrime = prime.size();
		while (right < numberofPrime) {
			// 연속된 소수의 합이 N과 같으면 센다
			if (sum == N) {
				count++;
			}
			// 연속된 소수의 합이 N보다 작거나 같으면
			if (sum <= N) {
				// 오른쪽 수가 끝까지 왔다면 더이상 늘어나지 못하므로 끝낸다
				if (right == numberofPrime - 1) {
					break;
				}
				// 합을 늘린다
				sum += prime.get(++right);
			} else {
				// 연속된 소수의 합이 N보다 크면 합을 줄인다
				sum -= prime.get(left++);
			}
		}
		System.out.println(count);

		bufferedReader.close();
	}

	/**
	 * N 이하의 소수 리스트를 구한다
	 */
	static ArrayList<Integer> findPrime(int N) {
		// 소수가 아니면 true, 소수이면 false인 배열
		boolean[] notPrime = new boolean[N + 1];
		notPrime[0] = true;
		notPrime[1] = true;
		for (int i = 2; i * i <= N; i++) {
			// 소수가 아닌 수는 넘긴다
			if (notPrime[i]) {
				continue;
			}
			// (i * i) 이하의 수는 이전에 이미 체크했기 때문에, (본인을 곱한) 제곱수부터 본다
			for (int j = i * i; j <= N; j += i) {
				notPrime[j] = true;
			}
		}
		// 소수 리스트
		ArrayList<Integer> prime = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			if (notPrime[i]) {
				continue;
			}
			prime.add(i);
		}
		return prime;
	}
}
