	/*
	
	[3190] 뱀
	'Dummy' 라는 도스게임이 있다. 이 게임에는 뱀이 나와서 기어다니는데, 사과를 먹으면 뱀 길이가 늘어난다.
	뱀이 이리저리 기어다니다가 벽 또는 자기자신의 몸과 부딪히면 게임이 끝난다.
	게임은 NxN 정사각 보드위에서 진행되고, 몇몇 칸에는 사과가 놓여져 있다. 보드의 상하좌우 끝에 벽이 있다.
	게임이 시작할때 뱀은 맨위 맨좌측에 위치하고 뱀의 길이는 1 이다. 뱀은 처음에 오른쪽을 향한다.
	뱀은 매 초마다 이동을 하는데 다음과 같은 규칙을 따른다.
	- 먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
	- 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
	- 만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.
	사과의 위치와 뱀의 이동경로가 주어질 때 이 게임이 몇 초에 끝나는지 계산하라.
	
	첫째 줄에 게임이 몇 초에 끝나는지 출력한다.
	
	1. 
	
	*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	final static char LEFT = 'L';
	final static char RIGHT = 'D';

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		
		int N = Integer.parseInt(bufferedReader.readLine());

		int numberOfApples = Integer.parseInt(bufferedReader.readLine());
		boolean[][] apples = new boolean[N+1][N+1];
		for (int i = 0; i < numberOfApples; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int Y = Integer.parseInt(stringTokenizer.nextToken());
			int X = Integer.parseInt(stringTokenizer.nextToken());
			apples[Y][X] = true;
			
		}
		int L = Integer.parseInt(bufferedReader.readLine());
		List<Direction> directions = new ArrayList<Direction>();
		int directionIndex = 0;
		for (int i = 0; i < L; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int X = Integer.parseInt(stringTokenizer.nextToken());
			char C = stringTokenizer.nextToken().charAt(0);
			directions.add(new Direction(X, C));
		}
		
		int r = 1, c = 1;
		boolean[][] location = new boolean[N+1][N+1];
		int[] dr = {0, 1, 0, -1};
		int[] dc = {1, 0, -1, 0};
		int currentDirection = 0;
		int time = 0;
		
		Queue<Coordinate> snake = new LinkedList<Coordinate>();
		snake.offer(new Coordinate(r, c));
		location[r][c] = true;
		
		while(true) {
			time++;
			
			r = r + dr[currentDirection];
			c = c + dc[currentDirection];
			if (r<1 || r>=N+1 || c<1 || c>=N+1 || location[r][c]==true) {
				break;
			}
			
			snake.offer(new Coordinate(r, c));
			location[r][c] = true;
			
			if (apples[r][c] == true) {
				numberOfApples--;
				apples[r][c] = false;
			}
			else {
				Coordinate locationHistory = snake.poll();
				location[locationHistory.r][locationHistory.c] = false;
			}
	
			if(directionIndex < L) {
				if (time == directions.get(directionIndex).time) {
					switch (directions.get(directionIndex).direction) {
					case LEFT:
						currentDirection = (currentDirection+3) % 4;
						break;
					case RIGHT:
						currentDirection = (currentDirection+1) % 4;
						break;
					}
					directionIndex++;
				}
			}
			
		}
		System.out.println(time);
		bufferedReader.close();
	}
	
}

class Coordinate {
	int r;
	int c;
	Coordinate(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

class Direction {
	int time;
	char direction;
	
	Direction(int time, char direction){
		this.time = time;
		this.direction = direction;
	}
}
