import java.util.ArrayDeque;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {

	public int solution(int[] priorities, int location) {
		int answer = 0;
		// 우선순위 (내림차순)
		PriorityQueue<Integer> prioritiesPQ = new PriorityQueue<>(Collections.reverseOrder());
		// 실행 대기 큐
		Queue<Process> processes = new ArrayDeque<>();
		for (int i = 0; i < priorities.length; i++) {
			int priority = priorities[i];
			prioritiesPQ.offer(priority);
			processes.offer(new Process(i, priority));
		}

		int order = 0;
		while (!processes.isEmpty()) {
			Process process = processes.poll();
			// 현재 프로세스의 우선순위보다 높은 우선순위를 가진 프로세스가 있다면
			if (process.priority < prioritiesPQ.peek()) {
				// 프로세스를 다시 실행 대기 큐에 넣음
				processes.offer(process);
			} else {
				// 프로세스 실행
				prioritiesPQ.poll();
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
