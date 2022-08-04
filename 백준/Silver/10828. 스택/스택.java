import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//명령의 수 입력
		int N = Integer.parseInt(bufferedReader.readLine());
		Stack<Integer> nums = new Stack<>();
		
		for (int tc = 0; tc < N; tc++) {
			//명령 입력
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			String command = stringTokenizer.nextToken();
			int num = 0;
			//push명령을 받으면 정수도 입력받음
			if (command.equals("push"))
				num = Integer.parseInt(stringTokenizer.nextToken());
			
			switch (command) {

			case "push":	//명령과 함께 입력된 숫자 push
				nums.push(num);
				break;
				
			case "pop":		//스택에서 가장 위에 있는 정수를 빼고 출력. 정수가 없는 경우 -1 출력
				bufferedWriter.write((!nums.isEmpty() ? nums.pop() : -1) + "\n");
				break;
				
			case "size":	//스택에 들어있는 정수의 개수 출력
				bufferedWriter.write(nums.size() + "\n");
				break;
				
			case "empty":	//스택이 비어있으면 1, 아니면 0 출력
				bufferedWriter.write((nums.isEmpty() ? 1 : 0) + "\n");
				break;
				
			case "top":		//스택의 가장 위에 있는 정수 출력. 정수가 없는 경우 -1 출력
				bufferedWriter.write((!nums.isEmpty() ? nums.peek() : -1) + "\n");
				break;
			}
		}
		bufferedReader.close();
		bufferedWriter.close();
	}

}
