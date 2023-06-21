import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	static final String BRACKET_OPEN = "(";
	static final String BRACKET_CLOSE = ")";

	static String[] stringArray;
	static int[] pair;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		String input = bufferedReader.readLine();
		char[] charArray = input.toCharArray();
		
		int stringLength = input.length();
		stringArray = new String[stringLength];
		for (int i = 0; i < stringLength; i++) {
			stringArray[i] = String.valueOf(charArray[i]);
		}

		pair = new int[50];
		Stack<Integer> stack = new Stack();
		for (int i = 0; i < stringLength; i++) {
			if (stringArray[i].equals(BRACKET_OPEN)) {
				stack.push(i);
			}
			if (stringArray[i].equals(BRACKET_CLOSE)) {
				pair[stack.pop()] = i;
			}
		}
		int originalStringLength = searchString(0, stringLength);
		System.out.println(originalStringLength);
		bufferedReader.close();
	}

	private static int searchString(int start, int end) {
		int length = 0;
		for (int i = start; i < end; i++) {
			if (stringArray[i].equals(BRACKET_OPEN)) {
				length += Integer.parseInt(stringArray[i - 1]) * searchString(i + 1, pair[i]) - 1;
				i = pair[i];
			} else {
				length++;
			}
		}
		return length;
	}
}
