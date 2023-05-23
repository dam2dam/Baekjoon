# -- 코드를 입력하세요
SELECT I.REST_ID REST_ID, 
    I.REST_NAME REST_NAME, 
    I.FOOD_TYPE FOOD_TYPE, 
    I.FAVORITES FAVORITES,
    I.ADDRESS ADDRESS, 
    ROUND(AVG(R.REVIEW_SCORE), 2) SCORE
    
FROM REST_INFO I

LEFT JOIN REST_REVIEW R
    ON I.REST_ID = R.REST_ID
    
WHERE R.REVIEW_SCORE IS NOT NULL
    AND I.ADDRESS LIKE '서울%'

GROUP BY I.REST_ID

ORDER BY AVG(R.REVIEW_SCORE) DESC, 
    I.FAVORITES DESC;



# 식당 ID, 식당 이름, 음식 종류, 즐겨찾기수, 주소, 리뷰 평균 점수


# 서울에 위치한 식당들
# 리뷰 평균점수는 소수점 세 번째 자리에서 반올림
# 결과는 평균점수를 기준으로 내림차순 정렬
# 평균점수가 같다면 즐겨찾기수를 기준으로 내림차순 정렬