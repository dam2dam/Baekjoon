import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	final static int button1 = 1;
	final static int button2 = 2;
	final static int button3 = 3;
	final static int button4 = 4;
	
	static String[] channel;
	static int indexofKBS1;
	static int indexofKBS2;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(bReader.readLine());
		channel = new String[n];
		for (int i = 0; i < n; i++)
			channel[i] = bReader.readLine();
		
		//KBS1과 KBS2의 현재 인덱스 찾기
		searchChannelIndex();
		
		//해당 인덱스를 타겟 인덱스로 바꾸기
		changeIndex(n, indexofKBS1, 0);
		changeIndex(n, indexofKBS2, 1);
		
	}
	static void changeIndex(int n, int indexToReplace, int targetIndex) {
		
		for (int i = 0; i < n; i++) {
			int temp = 0;
			
			//바꿀 대상이 아니면 1번 버튼을 누르고 내려가기
			if (i != indexToReplace)		
				System.out.print(button1);
			
			//바꿀 대상 인덱스를 타겟 인덱스까지 바꾸기
			if(i == indexToReplace) {
				for (int j = i; j > targetIndex; j--) {
					channel[temp] = channel[j];
					channel[j] = channel[j-1];
					channel[j-1] = channel[temp];
					System.out.print(button4);
				}
				searchChannelIndex();	//채널 리스트의 순서 변경 후 KBS1과 KBS2의 현재 인덱스 찾기
				break;
			}
		}
	}
	static void searchChannelIndex() {
		indexofKBS1 = Arrays.asList(channel).indexOf("KBS1");
		indexofKBS2 = Arrays.asList(channel).indexOf("KBS2");
	}
	// 자바 array에서는 indexOf()를 지원하지 않는다고 하여 asList()를 통해 변환시켜 ArrayList의 indexOf()를 사용
}
