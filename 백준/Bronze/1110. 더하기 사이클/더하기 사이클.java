import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bReader.readLine());
		
		int[] number = new int[2];			// 입력값 n
		int[] previousNumber = new int[2];	// 연산할 수
		int[] newNumber = new int[2];		// 연산된(새로운) 수
		int calculation = 0;				// 연산값
		int cycleCount = 0;					// 사이클의 길이

		// 입력값 저장
		number[0] = n/10;
		number[1] = n%10;
		
		// 연산을 위해 n을 연산할 수에 할당
		previousNumber = number;
		
		// 입력값이 0이면 사이클의 길이는 1로 종료
		if(n == 0)
			cycleCount = 1;
		
		else
			while (number[0] != newNumber[0] || number[1] != newNumber[1]) { // 입력값과 새로운수가 같으면 종료
				
				calculation = previousNumber[0] + previousNumber[1]; //각 자리의 숫자를 더한다
				
				newNumber[0] = previousNumber[1]; //주어진 수의 가장 오른쪽 자리 수와
				newNumber[1] = calculation % 10;  //앞에서 구한 합의 가장 오른쪽 자리 수를 이어붙이면 새로운 수
				
				previousNumber = newNumber; //다음 연산을 위한 할당
				
				cycleCount++;
			}
		System.out.println(cycleCount);
	}
}
