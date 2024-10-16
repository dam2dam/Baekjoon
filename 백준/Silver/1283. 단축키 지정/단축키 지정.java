import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

class Main {

	final static String BLANK = " ";
	final static String OPENING_BRACKET = "[";
	final static String CLOSING_BRACKET = "]";
	final static String ENTER = "\n";
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Set<Character> shortCuts = new HashSet<>();
		int N = Integer.parseInt(br.readLine());
		for (int op = 0; op < N; op++) {
			String[] option = br.readLine().split(BLANK);

			boolean isFound = false;
			String word;
			char now;
			// 단어의 첫 글자 확인
			for (int i = 0; i < option.length; i++) {
				word = option[i];
				now = word.toLowerCase().charAt(0);
				if (!shortCuts.contains(now)) {
					shortCuts.add(now);
					isFound = true;
					printOption(isFound, i, 0, option);
					break;
				}
			}
			// 단어의 첫 글자로 단축키를 지정하지 못했다면
			if (!isFound) {
				// 왼쪽 알파벳부터 확인
				for (int i = 0; i < option.length; i++) {
					word = option[i];
					for (int j = 1; j < word.length(); j++) {
						now = word.toLowerCase().charAt(j);
						if (!shortCuts.contains(now)) {
							shortCuts.add(now);
							isFound = true;
							printOption(isFound, i, j, option);
							break;
						}
					}
					if (isFound) {
						break;
					}
				}
			}
			// 어떠한 것도 단축키로 지정할 수 없다면 그냥 출력
			if (!isFound) {
				printOption(false, -1, -1, option);
			}
		}
		System.out.println(answer);

	}

	/**
	 * 옵션을 출력하는 함수
	 * @param isFound 단축키 지정 여부
	 * @param wordNumber 단축키가 위치한 단어의 번호
	 * @param alphabetNumber 단축키가 위치한 단어의 알파벳 인덱스
	 * @param option 옵션
	 */
	static void printOption(boolean isFound, int wordNumber, int alphabetNumber, String[] option) {
		char alphabet;

		for (int i = 0; i < option.length; i++) {
			for (int j = 0; j < option[i].length(); j++) {
				alphabet = option[i].charAt(j);
				// 단축키가 지정되었으면 단축키를 [ ]로 감싸서 출력
				if (isFound && wordNumber == i && alphabetNumber == j) {
					answer.append(OPENING_BRACKET)
						.append(alphabet)
						.append(CLOSING_BRACKET);
					continue;
				}
				answer.append(alphabet);
			}
			answer.append(BLANK);
		}
		answer.append(ENTER);
	}
}
