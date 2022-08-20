import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	
	static int S, P;
	static char[] chars;
	static Dna DNA;
	static int aCount = 0;
	static int cCount = 0;
	static int gCount = 0;
	static int tCount = 0;


	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringtokenizer = new StringTokenizer(bufferedReader.readLine());
		
		S = Integer.parseInt(stringtokenizer.nextToken());
		P = Integer.parseInt(stringtokenizer.nextToken());
		
		chars = new char[S];
		String input = bufferedReader.readLine();
		for(int i = 0; i < S; i++) {
			chars[i] = input.charAt(i);
		}
		
		stringtokenizer = new StringTokenizer(bufferedReader.readLine());
		DNA = new Dna();
		DNA.A = Integer.parseInt(stringtokenizer.nextToken());
		DNA.C = Integer.parseInt(stringtokenizer.nextToken());
		DNA.G = Integer.parseInt(stringtokenizer.nextToken());
		DNA.T = Integer.parseInt(stringtokenizer.nextToken());

		int startIndex = 0;
		int endIndex = P-1;

		for(int i = 0; i < P; i++) {
			switch(chars[i]) {
			case 'A':
				aCount++;
				break;
			case 'C':
				cCount++;
				break;
			case 'G':
				gCount++;
				break;
			case 'T':
				tCount++;
				break;
			}
		}
		int totalCount = 0;
		if(isPossible() == true)
			totalCount++;
		
		for(int i = 0; i < S-P; i++) {
			switch(chars[startIndex++]) {
			case 'A':
				aCount--;
				break;
			case 'C':
				cCount--;
				break;
			case 'G':
				gCount--;
				break;
			case 'T':
				tCount--;
				break;
			}
			switch(chars[++endIndex]) {
			case 'A':
				aCount++;
				break;
			case 'C':
				cCount++;
				break;
			case 'G':
				gCount++;
				break;
			case 'T':
				tCount++;
				break;
			}
			if(isPossible() == true)
				totalCount++;
		}
		System.out.println(totalCount);
		
		bufferedReader.close();
	}
	static boolean isPossible() {
		if(aCount>=DNA.A && cCount>=DNA.C && gCount>=DNA.G && tCount>=DNA.T) {
			return true;
		}
		return false;
	}
	static class Dna {
		int A, C, G, T;
	}
}
