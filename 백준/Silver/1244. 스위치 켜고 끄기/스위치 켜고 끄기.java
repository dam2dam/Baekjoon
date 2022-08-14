	/*
	
	[1244] 스위치 켜고 끄기
	1부터 연속적으로 번호가 붙어있는 스위치들이 있다.  ‘1’은 스위치가 켜져 있음을, ‘0’은 꺼져 있음을 나타낸다.
	학생 몇 명을 뽑아서, 학생들에게 1 이상이고 스위치 개수 이하인 자연수를 하나씩 나누어주었다. 
	남학생은 스위치 번호가 자기가 받은 수의 배수이면, 그 스위치의 상태를 바꾼다.
	여학생은 자기가 받은 수와 같은 번호가 붙은 스위치를 중심으로 좌우가 대칭이면서 가장 많은 스위치를 포함하는 구간을 찾아서, 그 구간에 속한 스위치의 상태를 모두 바꾼다.
	입력으로 스위치들의 처음 상태가 주어지고, 각 학생의 성별과 받은 수가 주어진다. 
	학생들은 입력되는 순서대로 자기의 성별과 받은 수에 따라 스위치의 상태를 바꾸었을 때, 스위치들의 마지막 상태를 출력하는 프로그램을 작성하시오.
	
	스위치의 상태를 1번 스위치에서 시작하여 마지막 스위치까지 한 줄에 20개씩 출력한다.
	
	1. 남학생과 여학생이 각각 스위치 상태 바꾸는 함수를 생성한다
	2. 남학생이 받은 수의 배수에 해당하는 스위치와, 여학생이 받은 수를 중심으로 좌우 대칭되는 구간까지 해당하는 스위치를 1과 xor연산하여 상태를 바꾼다
	
	*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] switches;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		
		// 입력1 : n:스위치 개수
		int n = Integer.parseInt(bufferedReader.readLine());
		
		// 입력2 : 각 스위치의 상태를 배열에 저장
		switches = new int[n+1];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 1; i <= n; i++) {
			switches[i] = Integer.parseInt(stringTokenizer.nextToken());
		}
		// 입력3 : 학생 수
		int numberOfStudents = Integer.parseInt(bufferedReader.readLine());
		
		// 입력4 : 학생의 성별, 학생이 받은 수를 배열에 저장
		int[][] students = new int[numberOfStudents][2];
		for (int i = 0; i < numberOfStudents; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			students[i][0] = Integer.parseInt(stringTokenizer.nextToken());
			students[i][1] = Integer.parseInt(stringTokenizer.nextToken());
		}
		
		
		for (int i = 0; i < numberOfStudents; i++) {
			// 남학생 스위치
			if (students[i][0] == 1)
				manSwitch(students[i][1]);
			// 여학생 스위치
			if (students[i][0] == 2)
				womanSwitch(students[i][1]);
		}
		// 출력
		for (int i = 1; i < switches.length; i++) {
			System.out.print(switches[i] + " ");
			if (i % 20 == 0)
				System.out.println();
		}
		bufferedReader.close();
	}
	
	static void manSwitch(int number) {
		for (int i = 1; i < switches.length; i++) {
			// 남학생이 받은 수의 배수에 해당하는 스위치 상태 바꾸기
			if (i % number == 0) {
				switches[i] ^= 1;	// 1과 xor연산을 하면 상태가 바뀐다
			}
		}
	}
	
	static void womanSwitch(int number) {
		// 여학생이 받은 수에 해당하는 스위치 상태 바꾸기
		switches[number] ^= 1;
		for (int i = 1; i < switches.length/2; i++) {
			if (number-i < 1 || number+i >= switches.length)
				break;
			
			// 여학생이 받은 수를 중심으로 좌우가 대칭인 구간까지 해당하는 스위치 상태 바꾸기
			if (switches[number-i] == switches[number+i]) {
				switches[number-i] ^= 1;	// 1과 xor연산을 하면 상태가 바뀐다
				switches[number+i] ^= 1;
			}
			else
				break;
		}
	}

}
