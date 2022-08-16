	/*
	
	[1931] 회의실 배정
	한 개의 회의실이 있는데 이를 사용하고자 하는 N개의 회의에 대하여 회의실 사용표를 만들려고 한다.
	각 회의 I에 대해 시작시간과 끝나는 시간이 주어져 있고, 각 회의가 겹치지 않게 하면서 회의실을 사용할 수 있는 회의의 최대 개수를 찾아보자.
	단, 회의는 한번 시작하면 중간에 중단될 수 없으며 한 회의가 끝나는 것과 동시에 다음 회의가 시작될 수 있다.
	회의의 시작시간과 끝나는 시간이 같을 수도 있다. 이 경우에는 시작하자마자 끝나는 것으로 생각하면 된다.
	
	첫째 줄에 최대 사용할 수 있는 회의의 최대 개수를 출력한다.
	
	1. 회의 시작시간과 종료시간을 객체로 사용할 수 있도록 클래스 생성 : Meeting
	2. 회의 시작시간과 종료시간을 기준으로 정렬할 수 있도록 Comparble 상속받아 compare() override
		2-1. 종료시간을 기준으로 오름차순 정렬, 종료시간이 같으면 시작시간을 기준으로 오름차순 정렬
	3. 회의실 사용표를 저장할 List를 생성하고 종료시간과 시작시간을 비교하여 가능한 회의 기록 : getSchedule()
	4. 최대 사용할 수 있는 회의의 개수(기록된 회의실 사용표의 크기) 출력
	
	*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		
		// 입력1 : 회의의 수
		int n = Integer.parseInt(bufferedReader.readLine());
		
		// 입력2 : 각 회의의 정보를 입력받아 배열에 저장. start:시작시간, end:종료시간
		Meeting[] meetings = new Meeting[n];
		for (int i = 0; i < n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int start = Integer.parseInt(stringTokenizer.nextToken());
			int end = Integer.parseInt(stringTokenizer.nextToken());
			meetings[i] = new Meeting(start, end);
		}
		
		// 회의실 사용표 만들어 List에 저장
		List<Meeting> result = getSchedule(meetings);
		
		// 출력 : 사용할 수 있는 회의의 최대 개수
		System.out.println(result.size());
	}
	
	private static List<Meeting> getSchedule(Meeting[] meetings) {
		
		// override 해놓은 기준으로 회의 시간 정렬
		Arrays.sort(meetings);
		
		// 회의실 사용표를 담을 List를 만들고 첫 회의 기록
		List<Meeting> result = new ArrayList<Meeting>();
		result.add(meetings[0]);
		
		for (int i = 1; i < meetings.length; i++)
			// 현재 기록된 마지막 회의의 종료시간과 같거나 이후인 회의가 시작 가능하므로 기록
			if (result.get(result.size()-1).end <= meetings[i].start)
				result.add(meetings[i]);
		
		return result;
	}
	
	// 시작시간과 종료시간을 다루기 위한 클래스 생성
	static class Meeting implements Comparable<Meeting> {
		
		int start;
		int end;
		
		public Meeting(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		// 종료시간을 기준으로 오름차순 정렬, 종료시간이 같으면 시작시간을 기준으로 오름차순 정렬
		@Override
		public int compareTo(Meeting o) {
			if (this.end == o.end)
				return this.start < o.start ? -1 : this.start == o.start ? 0 : 1;
			return this.end < o.end ? -1 : this.end == o.end ? 0 : 1;
		}
	}
}
