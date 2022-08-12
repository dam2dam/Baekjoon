import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int m;
	static int k;
	
	static int[][] inputArray;
	static int[] operationIndexArray;
	static int[] permutationArray; 
	static boolean[] visit;
	
	static int[][] operation;
	
	static List<Integer> minimums;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		// 입력1 : n:행의 수, m:열의 수, k:회전 연산의 개수
		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());
		k = Integer.parseInt(stringTokenizer.nextToken());
		
		// 입력2 : 배열 입력받기
		inputArray = new int[n+1][m+1];
		for (int i = 1; i <= n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 1; j <= m; j++) {
				inputArray[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}
		
		operation = new	int[k][3];
		for (int i = 0; i < k; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < 3; j++) {
				operation[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}
		
		visit = new boolean[k];
		operationIndexArray = new int[k];
		for (int i = 0; i < operationIndexArray.length; i++) {
			operationIndexArray[i] = i;
		}
		permutationArray = new int[k];
		
		minimums = new ArrayList<Integer>();
		
		permutation(inputArray, 0);
		
		Collections.sort(minimums);
		
		System.out.println(minimums.get(0));
	}
	
	
	static void permutation(int[][] inputArray, int count) {

		if (count == k) {
			int[][] newArray = new int[n+1][m+1];
			for (int i = 0; i < inputArray.length; i++) {
				for (int j = 0; j < inputArray[i].length; j++) {
					newArray[i][j] = inputArray[i][j];
				}
			}
			for (int i = 0; i < k; i++) {
				int index = permutationArray[i];
				int r = operation[index][0];
				int c = operation[index][1];
				int s = operation[index][2];
				newArray = rotation(newArray, r, c, s);	
			}
			rowSum(newArray);
			return;
		}
		
		for (int i = 0; i < k; i++) {
			if (visit[i] == false) {
				visit[i] = true;
				permutationArray[count] = operationIndexArray[i];
				permutation(inputArray, count+1);
				visit[i] = false;
			}
		}
	}
	
	
	static int[][] rotation(int[][] newArray, int r, int c, int s) {
		int[][] temp = new int[n+1][m+1];
		for (int i = 0; i < newArray.length; i++) {
			for (int j = 0; j < newArray[i].length; j++) {
				temp[i][j] = newArray[i][j];
			}
		}
		for (int depth = 0; depth < s; depth++) {
			for (int col = c-s +depth; col < c+s -depth; col++) {
				newArray[r-s +depth][col +1] = temp[r-s +depth][col];
			}
			for (int row = r-s +depth; row < r+s -depth; row++) {
				newArray[row +1][c+s -depth] = temp[row][c+s -depth];
			}
			for (int col = c+s -depth; col > c-s +depth; col--) {
				newArray[r+s -depth][col -1] = temp[r+s -depth][col];
			}
			for (int row = r+s -depth; row > r-s +depth; row--) {
				newArray[row -1][c-s +depth] = temp[row][c-s +depth];
			}
		}
		return newArray;
	}
	
	static void rowSum(int[][] newArray) {
		int min = Integer.MAX_VALUE;
		for (int i = 1; i < newArray.length; i++) {
			int sum = 0;
			for (int j = 1; j < newArray[i].length; j++) {
				sum += newArray[i][j];
			}
			min = Math.min(min, sum);
		}
		minimums.add(min);
		
	}
}
