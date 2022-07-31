import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		// n 입력
		String n = scanner.next(); 
		
		StringBuilder sb;
		String nReverse;
		
		// nInt: int n, nStr: String n, nReverse
		int nInt = Integer.parseInt(n);
		String nStr = n;
		boolean answer = true;
		
		while (answer) {
			//소수 체크를 위한 String n -> int n
			nInt = Integer.parseInt(nStr);
			
			//팰린드롬 체크를 위한 reverse n
			sb = new StringBuilder(nStr);
			nReverse = sb.reverse().toString();
			
			//소수이면서 팰린드롬인 경우 종료
			if(nStr.equals(nReverse) && primeNumberCheck(nInt)) {
				answer = false;
				break;
			}
			//n증가
			nInt++;
			
			//증가한 n -> String n
			nStr = Integer.toString(nInt);
		}
		System.out.println(nInt);
		
	}
	
	//소수 체크
	static boolean primeNumberCheck(int num) {
		if(num < 2)
			return false;
		else
			for(int i = 2; i*i <= num; i++)
				if(num % i == 0)
					return false;
		return true;
	}
}
