class Solution {
    
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();
		StringBuilder gameResult = new StringBuilder();

        // m명의 인원이 t바퀴 진행한 게임의 결과 얻기
		int number = 0;
		while (gameResult.length() < m * t) {
			String numberString = makeNumberToString(number++, n);
			gameResult.append(numberString);
		}
        // 튜브의 차례(p)에 말해야 하는 숫자 모으기
		for (int i = p - 1; i < m * t; i += m) {
			answer.append(gameResult.charAt(i));
		}
        return answer.toString().toUpperCase();
    }
    
    /**
	 * 10진수를 n진수 문자열로 변환하는 메서드
	 * @param number 10진수
	 * @param base 진법
	 * @return n진수 문자열
	 */
	static String makeNumberToString(int number, int base) {
		StringBuilder numberString = new StringBuilder();

		while (number >= base) {
			// 자릿수 연산 (10 ~ 15는 각각 A B C D E F로 표기)
			numberString.append(Integer.toHexString(number % base));
			number /= base;
		}
		// 첫번째 자릿수
		numberString.append(Integer.toHexString(number % base));

		// 문자열을 역순으로 만들었기 때문에 뒤집어서 반환
		return numberString.reverse().toString();
	}
}