	/*
	
	[3040] 백설 공주와 일곱 난쟁이
	난쟁이가 일을 하는 동안 백설공주는 그들을 위해 저녁 식사를 준비한다. 백설공주는 의자 일곱개, 접시 일곱개, 나이프 일곱개를 준비한다.
	어느 날 광산에서 아홉 난쟁이가 돌아왔다. (왜 그리고 어떻게 아홉 난쟁이가 돌아왔는지는 아무도 모른다) 
	아홉 난쟁이는 각각 자신이 백설공주의 일곱 난쟁이라고 우기고 있다.
	백설공주는 이런 일이 생길 것을 대비해서, 난쟁이가 쓰고 다니는 모자에 100보다 작은 양의 정수를 적어 놓았다. 
	일곱 난쟁이의 모자에 쓰여 있는 숫자의 합이 100이 되도록 적어 놓았다.
	아홉 난쟁이의 모자에 쓰여 있는 수가 주어졌을 때, 일곱 난쟁이를 찾는 프로그램을 작성하시오. (아홉 개의 수 중 합이 100이 되는 일곱 개의 수를 찾으시오)
	
	일곱 난쟁이가 쓴 모자에 쓰여 있는 수를 한 줄에 하나씩 출력한다.
	
	1. 아홉개의 수를 입력받아 배열에 저장한다
	2. 비교를 위해 아홉개의 숫자를 모두 더한다 : sum
	3. 9개 숫자에서 2개를 고르는 조합을 만든다 : combinationSearch()
	4. 2개 숫자 조합의 합이 총합에서 100을 뺀 수와 같은지 비교하여 두 난쟁이를 걸러내고 배열에 저장한다 
	5. 아홉 난쟁이 중, 걸러낸 두 난쟁이를 제외하고 출력한다
	
	*/ 

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[] dwarfs;
	static int[] combinationNumbers;
	static int[] answer;
	static int sum;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		// 입력 : 1~99 자연수 9개
		dwarfs = new int[9];
		for (int i = 0; i < dwarfs.length; i++)
			dwarfs[i] = Integer.parseInt(bufferedReader.readLine());
		
		// 모자에 적힌 모든 수의 합을 구한다
		sum = Arrays.stream(dwarfs).sum();
		
		// 모든 조합을 임시 저장할 배열과 정답인 조합을 저장할 배열
		combinationNumbers = new int[2];
		answer = new int[2];
		
		// 아홉 난쟁이 중, 일곱 난쟁이 외의 두 난쟁이 찾기
		combinationSearch(0, 0);
		
		// 출력 : 아홉 난쟁이 중 (일곱 난쟁이가 아닌) 두 난쟁이를 제외하고 출력
		for (int i = 0; i < dwarfs.length; i++) {
			if (dwarfs[i] == answer[0] || dwarfs[i] == answer[1])
				continue;
			System.out.println(dwarfs[i]);
		}
		bufferedReader.close();
	}
	
	static void combinationSearch(int start, int index) {
		if (index == 2) {	// 조합이 완성되면 숫자의 총합에서 100을 뺀 수와 같은지 비교하여 두 난쟁이 찾기
			if (combinationNumbers[0] + combinationNumbers[1] == sum-100) {
				answer[0] = combinationNumbers[0];
				answer[1] = combinationNumbers[1];
			}
			return;
		}
		for (int i = start; i < 9; i++) {
			combinationNumbers[index] = dwarfs[i];
			combinationSearch(i+1, index+1);
		}
	}
}
