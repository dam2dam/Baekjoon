	/*
	
	[12865] 평범한 배낭
	이 문제는 아주 평범한 배낭에 관한 문제이다.
	준서가 여행에 필요하다고 생각하는 N개의 물건이 있다. 
	각 물건은 무게 W와 가치 V를 가지는데, 해당 물건을 배낭에 넣어서 가면 준서가 V만큼 즐길 수 있다. 
	아직 행군을 해본 적이 없는 준서는 최대 K만큼의 무게만을 넣을 수 있는 배낭만 들고 다닐 수 있다. 
	준서가 최대한 즐거운 여행을 하기 위해 배낭에 넣을 수 있는 물건들의 가치의 최댓값을 알려주자.
	
	한 줄에 배낭에 넣을 수 있는 물건들의 가치합의 최댓값을 출력한다.
	
	1. 현재 채우려는 무게만큼의 가치를 채워나간다
	2. 먼저 보았던 물건이 담을 수 있는 만큼의 가치를 합쳐서 남은 무게를 채운다
	3. 그 중 최댓값으로 저장하고 배열을 다 채우면 최댓값을 찾는다
	
	*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, K, max;
	static Item[] items;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		
		// 입력1 : N:물품의 수, K:준서가 버틸 수 있는 무게
		N = Integer.parseInt(stringTokenizer.nextToken());
		K = Integer.parseInt(stringTokenizer.nextToken());
		
		// 입력2 : 각 물건의 무게와 해당 물건의 가치를 입력받아 item객체를 배열에 저장
		items = new Item[N+1];
		for (int i = 1; i <= N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int weight = Integer.parseInt(stringTokenizer.nextToken());
			int value = Integer.parseInt(stringTokenizer.nextToken());
			
			items[i] = new Item(weight, value);
		}
		
		max = Integer.MIN_VALUE;
		
		dp = new int[N+1][K+1];
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= K; j++) {
				// 현재 채우려고 하는 무게보다 담으려고 하는 물건이 더 무거우면 이전 물건을 담았을 때의 누적값을 가져옴
				if (items[i].weight > j)
					dp[i][j] = dp[i-1][j];
				 
				// 이전 물건을 담았을 때의 가치와, 
				// 현재 물건의 가치 + 채우려고 하는 무게에서 현재 물건의 무게를 빼고 남은 만큼 채울 수 있는 가치(이전 값에서 가져옴)를 비교하여
				// 최댓값을 저장
				else
					dp[i][j] = Math.max(dp[i-1][j], items[i].value + dp[i-1][j-items[i].weight]);
			}
		
		// 출력 : 배낭에 넣을 수 있는 물건들의 가치합의 최댓값
		System.out.println(dp[N][K]);
		
		bufferedReader.close();
	}


	static class Item {
		int weight, value;

		public Item(int weight, int value) {
			super();
			this.weight = weight;
			this.value = value;
		}
	}
	
}
