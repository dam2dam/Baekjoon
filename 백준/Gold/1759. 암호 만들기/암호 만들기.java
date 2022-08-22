	/*
	
	[1759] 암호 만들기
	암호는 서로 다른 L개의 알파벳 소문자들로 구성되며 최소 한 개의 모음(a, e, i, o, u)과 최소 두 개의 자음으로 구성되어 있다고 알려져 있다.
	또한 정렬된 문자열을 선호하는 조교들의 성향으로 미루어 보아 암호를 이루는 알파벳이 암호에서 증가하는 순서로 배열되었을 것이라고 추측된다.
	새 보안 시스템에서 조교들이 암호로 사용했을 법한 문자의 종류는 C가지가 있다고 한다. 
	C개의 문자들이 모두 주어졌을 때, 가능성 있는 암호들을 모두 구하는 프로그램을 작성하시오.
	
	각 줄에 하나씩, 사전식으로 가능성 있는 암호를 모두 출력한다.
	
	1. 암호를 구성할 수 있는 알파벳 소문자를 입력받아 배열에 저장하고 오름차순 정렬한다
	2. 암호의 길이만큼의 알파벳 조합이 완성될 때마다 유효성 검사를 하고 암호를 출력한다 : searchPassword()
		2-1. 유효성 검사는 최소 한 개의 모음과 최소 두 개의 자음으로 구성된 암호인지 count한다 : validCheck()
	
	*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int L, C;
	static char[] alphabets, password;
			
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		
		// 입력1 : L:암호 길이, C:암호를 구성할 수 있는 알파벳 소문자의 수
		L = Integer.parseInt(stringTokenizer.nextToken());
		C = Integer.parseInt(stringTokenizer.nextToken());
		
		// 입력2 : 암호를 구성할 수 있는 알파벳 소문자를 입력받아 배열에 저장
		alphabets = new char[C];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 0; i < C; i++)
			alphabets[i] = stringTokenizer.nextToken().charAt(0);
		
		// 암호를 이루는 알파벳은 증가하는 순서로 배열 => 오름차순 정렬
		Arrays.sort(alphabets);
		
		// 암호 조합을 위한 배열
		password = new char[L];
		
		// 암호 조합
		searchPassword(0, 0);
		
		bufferedReader.close();
	}

	/**
	 * 암호를 조합하는 함수
	 * @param start 조합할 요소 배열의 시작점, 순서를 고려하지 않으므로 항상 먼저 선택한 요소의 다음 인덱스부터 시작한다
	 * @param count 조합된 요소 배열의 인덱스
	 */
	static void searchPassword(int start, int count) {
		
		// 암호가 완성되면
		if (count == L) {
			// 유효성 체크 후 암호 출력
			if(validCheck(password) == true) {
				for (int i = 0; i < password.length; i++)
					System.out.print(password[i]);
				System.out.println();
			}
			return;
		}
		
		for (int i = start; i < C; i++) {
			password[count] = alphabets[i];
			searchPassword(i+1, count+1);
		}
	}
	
	/**
	 * 유효성을 체크하는 함수
	 * @param password 유효성을 체크할 배열
	 * @return 최소 한 개의 모음과 최소 두 개의 자음으로 구성된 암호이면 true 
	 */
	static boolean validCheck(char[] password) {
		int vowelCount = 0;	// 모음의 수
		int consonantCount = 0;	// 자음의 수
		
		for (int i = 0; i < password.length; i++) {
			// 암호의 요소가 모음이면 모음 count, 자음이면 자음 count
			if (password[i] == 'a' || password[i] == 'e' || 
				password[i] == 'i' || password[i] == 'o' || password[i] == 'u')
				vowelCount++;
			else
				consonantCount++;
		}
		// 최소 한 개의 모음과 최소 두 개의 자음으로 구성된 암호이면 true 반환
		if (vowelCount >= 1 && consonantCount >=2)
			return true;
		
		return false;
	}

}
