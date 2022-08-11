	/*
	
	[2839] 설탕 배달
	상근이는 지금 사탕가게에 설탕을 정확하게 N킬로그램을 배달해야 한다.
	설탕공장에서 만드는 설탕은 봉지에 담겨져 있다. 봉지는 3킬로그램 봉지와 5킬로그램 봉지가 있다.
	상근이는 귀찮기 때문에, 최대한 적은 봉지를 들고 가려고 한다.
	상근이가 설탕을 정확하게 N킬로그램 배달해야 할 때, 봉지 몇 개를 가져가면 되는지 그 수를 구하는 프로그램을 작성하시오.
	
	상근이가 배달하는 봉지의 최소 개수를 출력한다. 만약, 정확하게 N킬로그램을 만들 수 없다면 -1을 출력한다.
	
	1. 봉지의 최소 개수를 구해야 하기 때문에 큰 수인 5로 먼저 나눈 후 count한다
	2. 나누어지지 않으면 3을 빼고 count한다
	3. 0이 될 때까지 1,2번을 반복하고 count를 출력한다
	4. 0이하로 떨어지면 정확하게 n킬로그램을 만들 수 없으므로 -1을 출력한다
	
	*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		// 입력 : n:상근이가 배달해야하는 설당의 무게
		int n = Integer.parseInt(bufferedReader.readLine());
		int count = 0;
		
		boolean complete = false;
		while (complete == false) {	// 완료 될 때까지 반복
			// 5로 나누어지면 몫만큼 count하고 나머지를 n에 저장 
			if (n % 5 == 0) {
				count += n/5;
				n %= 5;
			}
			else {	// 5로 나누어지지 않으면 3킬로그램 1개를 count
				n = n-3;
				count++;
			}
			
			// 정확하게 n킬로그램을 만들면 완료
			if (n == 0) {
				complete = true;
				break;
			} 
			// 정확하게 n킬로그램을 만들 수 없다면 -1
			else if (n < 0) {
				complete = true;
				count = -1;
				break;
			}
		}
		// 출력
		System.out.println(count);
		bufferedReader.close();
	}
}
