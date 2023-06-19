import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static final String BLANK = " ";

	static int N, M;
	static int[] nums, permutationNums;
	static boolean[] visited;
	static Set<String> set;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());

		nums = new int[N];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(stringTokenizer.nextToken());
		}
		Arrays.sort(nums);

		permutationNums = new int[M];
		visited = new boolean[N];
		set = new LinkedHashSet<>();
		permutation(0);

		for (String num : set) {
			System.out.println(num);
		}
		bufferedReader.close();
	}

	private static void permutation(int count) {
		if (count == M) {
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < M; i++) {
				stringBuilder.append(permutationNums[i]).append(BLANK);
			}
			set.add(stringBuilder.toString());
			return;
		}
		for (int i = 0; i < N; i++) {
			if (visited[i]) {
				continue;
			}
			permutationNums[count] = nums[i];
			visited[i] = true;
			permutation(count + 1);
			visited[i] = false;
		}
	}
}
