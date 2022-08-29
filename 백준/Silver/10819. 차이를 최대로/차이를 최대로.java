	/*
	
	[10819] 차이를 최대로
	N개의 정수로 이루어진 배열 A가 주어진다. 
	이때, 배열에 들어있는 정수의 순서를 적절히 바꿔서 다음 식의 최댓값을 구하는 프로그램을 작성하시오.
		|A[0] - A[1]| + |A[1] - A[2]| + ... + |A[N-2] - A[N-1]|
	
	첫째 줄에 배열에 들어있는 수의 순서를 적절히 바꿔서 얻을 수 있는 식의 최댓값을 출력한다.
	
	1. 순열을 생성하여 모든 경우의 식을 계산해보고 그 중 최댓값을 출력한다
	
	*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, sum, max;
	static int[] numbers, permutationNumberArray;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		
		// 입력1 : 숫자의 개수
		N = Integer.parseInt(bufferedReader.readLine());
		
		// 입력2 : 식을 만들 숫자 배열
		numbers = new int[N];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 0; i < N; i++)
			numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
		
		visit = new boolean[N];
		permutationNumberArray = new int[N];
		max = Integer.MIN_VALUE;
		
		// 순열생성 시작
		permutation(0);
		
		// 출력
		System.out.println(max);
		
		bufferedReader.close();
	}

	private static void permutation(int count) {
		
		// 순열이 완성되면 식의 최댓값 갱신
		if (count == N) {
			sum = 0;
			for (int i = 0; i <	N-1; i++)
				sum += Math.abs(permutationNumberArray[i] - permutationNumberArray[i+1]);
			max = Math.max(max, sum);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (visit[i] == true)
				continue;
			
			permutationNumberArray[count] = numbers[i];
			visit[i] = true;
			permutation(count+1);
			visit[i] = false;
		}
	}
}
