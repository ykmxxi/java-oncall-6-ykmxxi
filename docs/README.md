# 기능 목록

## 입력

- [x] 월과 시작 요일 정보를 입력받는다
  - 월(숫자)
  - 요일(일, 월, 화, 수, 목, 금, 토)
  - 연도는 고려하지 않음, 모든 연도는 2월 28일 까지 있다고 가정
  - 예외: 입력이 공백인 경우(빈 값, `null` 포함)
  - 예외: 쉼표로 구분하지 않은 경우
- [x] 평일 비상 근무 순서와 휴일 비상 근무 순서를 입력받는다
  - 둘은 함께 관리, 즉 휴일 비상 근무 순서가 이상하면 평일 비상 근무 순서부터 다시 입력 
  - 예외: 입력이 공백인 경우(빈 값, `null` 포함)
  - 예외: 쉼표로 구분하지 않은 경우

```text
비상 근무를 배정할 월과 시작 요일을 입력하세요> 5,월
평일 비상 근무 순번대로 사원 닉네임을 입력하세요> 준팍,도밥,고니,수아,루루,글로,솔로스타,우코,슬링키,참새,도리
휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> 수아,루루,글로,솔로스타,우코,슬링키,참새,도리,준팍,도밥,고니
```

## 출력
- [x] 에러 메시지를 출력한다
  - `[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.`
- [x] 비상 근무표를 출력한다
  - 평일이면서 공휴일이면 요일 뒤에 `(휴일)` 표기
```text
5월 4일 목 수아
5월 5일 금(휴일) 루루
5월 6일 토 수아
...
```

## 도메인

- [x] 근무 배정 월을 생성한다
  - 예외: 1 ~ 12월이 아닌 경우
  - 예외: 일 ~ 토가 아닌 경우
- [x] 휴일인지 확인한다
  - 주말 or 공휴일
- [x] 근무자를 생성한다
  - 근무자는 이름과 평일/휴일 순번 값을 갖고 있는다 
  - 예외: 이름이 5자 초과인 경우
  - 예외: 이름 중간에 공백이 포함된 경우
  - 예외: 근무 순번이 35를 초과한 경우
- [x] 비상 근무 스케줄을 생성한다
  - 예외: 같은 사람이 2번 근무하는 경우
  - 예외: 총 근무자가 5명 미만인 경우
  - 예외: 평일과 휴일 순번의 총 근무자가 다른 경우
    - ex) 평일 A B C D E / 휴일 B E C D F -> 평일에는 A, 휴일에는 F 가 따로 존재
- [x] 비상 근무표를 생성한다

# 개념과 격벽

- 근무자
  - 상태: 이름, 근무 순서
- 근무 순서
  - 상태: 순번, 근무 횟수 
  - 휴일 근무 순서: 휴일 순번, 휴일 근무 횟수
  - 평일 근무 순서: 평일 순번, 평일 군무 횟수
- 근무표
  - 상태: 근무월
  - 행위: 근무 순서에 따라 근무표를 짠다
- 공휴일
  - 상태: 공휴일(1/1, 3/1, 5/5, 6/6, 8/15, 10/3, 10/9, 12/25) -> 상수
  - 행위: 공휴일인지 확인한다

# 기능 분석

- 효율적으로 인력을 배치하여 장애를 탐지하고 신속히 대응
- 먼저 담당 개발자가 가장 빠르게 대응할 수 있도록, '월별 비상근무표'를 편성
- 비상근무표 생성 프로그램 구현
```text
비상 근무를 배정할 월과 시작 요일을 입력하세요> 5,월
평일 비상 근무 순번대로 사원 닉네임을 입력하세요> 준팍,도밥,고니,수아,루루,글로,솔로스타,우코,슬링키,참새,도리
휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> 수아,루루,글로,솔로스타,우코,슬링키,참새,도리,준팍,도밥,고니

5월 1일 월 준팍
5월 2일 화 도밥
5월 3일 수 고니
5월 4일 목 수아
...
```
- 순번에 따라 비상 근무일을 배정(FIFO 구조, 차례를 지키며)
- 평일과 휴일(토요일, 일요일, 공휴일) 비상 근무 순번을 다르게 운영
  - 각 비상 근무 순번은 다를 수 있음
- 평일 1번, 휴일 1번 편성
  - 평일 or 휴일에 2번 편성된 경우 잘못된 것임
- 어떤 경우에도 연속 2일 근무 불가능. 연속 2일 근무하게 되는 상황에서는 다음 근무자와 순서를 교체
  - 순서를 바꾸고 다음 근무 때 앞에 근무할 사람이 존재하면 앞 사람이 근무
  - A -> B -> C -> D -> 원래 D(휴일), E가 대체 근무 -> D(휴일) -> E 다음 순번 사람(휴일)
- 다음 근무자와 순서를 바꿔야 하는 경우에는, 앞의 날짜부터 순서를 변경
- 근무자 이름은 중복 불가, 최대 5자, 최소 5명의 근무자 필요, 최대 35명 허용
- 공휴일(1/1, 3/1, 5/5, 6/6, 8/15, 10/3, 10/9, 12/25)
