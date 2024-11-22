import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {

	public int solution(int[] priorities, int location) {
		int answer = 0;
		// 실행 대기 큐
		Queue<Process> processes = new ArrayDeque<>();
		for (int i = 0; i < priorities.length; i++) {
			processes.offer(new Process(i, priorities[i]));
		}
        // 우선순위 정렬(오름차순)
        Arrays.sort(priorities);
        // 높은 우선순위의 존재여부가 중요하기 때문에 뒤에서부터 확인
        int priorityIndex = priorities.length - 1;

		int order = 0;
		while (!processes.isEmpty()) {
			Process process = processes.poll();
			// 현재 프로세스의 우선순위보다 높은 우선순위를 가진 프로세스가 있다면
			if (process.priority < priorities[priorityIndex]) {
				// 프로세스를 다시 실행 대기 큐에 넣음
				processes.offer(process);
			} else {
				// 프로세스 실행
                priorityIndex--;
				order++;
				// 현재 프로세스의 위치와 몇 번째로 실행되는지 알고싶은 프로세스의 위치가 같다면
				if (process.location == location) {
					answer = order;
					break;
				}
			}
		}
		return answer;
	}

	static class Process {
		int location;
		int priority;

		public Process(int location, int priority) {
			this.location = location;
			this.priority = priority;
		}
	}
}
