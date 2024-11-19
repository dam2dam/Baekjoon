class Solution {
    
    static final String LEFT_HANDED = "left";
    static final String RIGHT_HANDED = "right";
    static final String LEFT = "L";
    static final String RIGHT = "R";
    
    public String solution(int[] numbers, String hand) {
        StringBuilder answer = new StringBuilder();
        
        // 왼손 엄지손가락의 초기 위치 * 좌표
        int leftR = 3;
        int leftC = 0;
        // 오른손 엄지손가락의 초기 위치 # 좌표
        int rightR = 3;
        int rightC = 2;
        
        // 2 5 8 0
        int centerLineR;
        int centerLineC = 1;
        
        for (int number : numbers) {
            switch (number) {
                case 1:
                case 4:
                case 7:
                    leftR = (number - 1) / 3;
                    leftC = 0;
                    answer.append(LEFT);
                    break;
                case 3:
                case 6:
                case 9:
                    rightR = (number - 1) / 3;
                    rightC = 2;
                    answer.append(RIGHT);
                    break; 
                // 0일 때 number 재설정 (number로 좌표를 계산하기 위함)
                case 0:
                    number = 11;
                case 2:
                case 5:
                case 8:
                    centerLineR = (number - 1) / 3;
                    int leftLength = Math.abs(centerLineR - leftR) + Math.abs(centerLineC - leftC);
                    int rightLength = Math.abs(centerLineR - rightR) + Math.abs(centerLineC - rightC);
                    // 누를 번호가 왼쪽 엄지손가락과 더 가깝다면
                    if (leftLength < rightLength) {
                        leftR = centerLineR;
                        leftC = centerLineC;
                        answer.append(LEFT);
                    // 누를 번호가 오른쪽 엄지 손가락과 더 가깝다면
                    } else if (leftLength > rightLength) {
                        rightR = centerLineR;
                        rightC = centerLineC;
                        answer.append(RIGHT);
                    // 두 엄지손가락의 거리가 같다면
                    } else {
                        // 왼손잡이
                        if (hand.equals(LEFT_HANDED)) {
                            leftR = centerLineR;
                            leftC = centerLineC;
                            answer.append(LEFT);
                        // 오른손잡이
                        } else if (hand.equals(RIGHT_HANDED)) {
                            rightR = centerLineR;
                            rightC = centerLineC;
                            answer.append(RIGHT);
                        }
                    }
                    break;
            }
        }
        return answer.toString();
    }
}