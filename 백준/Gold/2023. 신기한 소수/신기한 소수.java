	/*

	[2023] 신기한 소수
	수빈이는 왼쪽부터 1자리, 2자리, 3자리, 4자리 수 모두 소수인 숫자를 신기한 소수라고 이름 붙였다.
	N이 주어졌을 때, 수빈이를 위해 N자리 신기한 소수를 모두 찾아보자.
	
	N자리 수 중에서 신기한 소수를 오름차순으로 정렬해서 한 줄에 하나씩 출력한다.
	
	1. 왼쪽 첫 번째 숫자부터 소수를 판별
	2. 자릿수를 늘려가며 소수를 걸러내기
	
	*/

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static Queue<Integer> primeNumber;	//소수를 저장할 큐

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		//출력할 소수의 자릿수 입력: n자리
		int n = scanner.nextInt();
		
		// 한 자리 수 중에서 소수를 걸러내어 큐에 넣음
        primeNumber = new LinkedList<Integer>();
        for (int i = 2; i < 10; i++)
			addPrimeNumber(i);
        
        // 큐에 있는 숫자가 10^(n-1)이하이면 실행 (실행 후 n자리가 됨)
        while (primeNumber.peek() < Math.pow(10, n-1)) {
        	//큐에서 소수 하나를 빼고
        	int primeNumberElement = primeNumber.poll();
        	//그 소수의 자릿수를 올리고 (소수*10)대의 숫자 9개를 판별하여 소수 큐에 넣음
			for (int j = 0; j < 10; j++)
				addPrimeNumber(primeNumberElement*10 + j);
		}
       
        //출력 (처음부터 숫자 오름차순으로 계산했기 때문에 정렬은 필요없음)
        for (Integer integer : primeNumber)
            System.out.println(integer);
    }
	
	//소수를 큐에 넣는 함수
	static void addPrimeNumber(int num) {
		//소수가 맞으면 큐에 넣음
        if (isprimeNumber(num))
            primeNumber.offer(num);
	}
	
	//소수 판별 함수
    static boolean isprimeNumber(int num) {
    	for (int j = 2; j*j <= num; j++)
            if (num%j == 0)
                return false;
    	return true;
    }
}