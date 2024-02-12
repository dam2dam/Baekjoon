import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

	static final String ENTER = "\n";

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		StringBuilder answer = new StringBuilder();

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		int N = Integer.parseInt(stringTokenizer.nextToken());
		int M = Integer.parseInt(stringTokenizer.nextToken());

		// 메모장 키워드 저장
		HashSet<String> memo = new HashSet<>();
		for (int i = 0; i < N; i++) {
			memo.add(bufferedReader.readLine());
		}
		String word;
		int count = N;
		for (int i = 0; i < M; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), ",");
			while (stringTokenizer.hasMoreTokens()) {
				// 글에 있는 키워드가 메모장에 있다면
				word = stringTokenizer.nextToken();
				if (memo.contains(word)) {
					// 지우고 개수 감소
					memo.remove(word);
					count--;
				}
			}
			answer.append(count).append(ENTER);
		}
		System.out.println(answer);
		bufferedReader.close();
	}
}
