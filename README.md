# 카페 

### 기능 요구사항

- 합정역에서 카페를 하는 민석이를 위한 POS 프로그램을 구현한다.
  - 주문, 결제, 종료 기능을 구현한다.
- 한 테이블에 주문 할 수 있는 한 메뉴의 수량은 최대 30개이다.
- 주문이 등록된 테이블은 결제가 이루어지기 전까지 테이블 목록에 별도로 표시한다.
- 결제 유형에 따라 할인율이 달라진다.
  - 케이크 종류 메뉴의 수량의 합이 3개가 넘어가면  3000원 씩 할인이 된다.
    - 3개면 3000원 할인, 6개면 6000원 할인
  - 현금 결제의 경우 10% 할인이 되며, 할인된 금액에서 한 번 더 할인이 가능하다.
- 주문 혹은 결제가 불가능한 경우에는 그 이유를 보여준다.
- 최종 결제 금액을 보여준다.
- 프로그램은 종료를 입력하기 전까지 종료되어서는 안된다.

### 프로그래밍 요구사항

- 기존 클래스의 코드를 수정가능하다.
  - 단 Repository 클래스의 코드는 수정 불가능하다.
    - 메서드 이름의 수정은 가능하다.
  - 단 새로운 멤버변수를 추가하는 것은 불가능하다.
    - 메서드의 추가는 가능하다.

### 예시 화면

```java
## 메인화면
1 - 주문등록
2 - 결제하기
3 - 종료
  
## 기능 선택
1
  
## 테이블 목록
┌ ─ ┐┌ ─ ┐┌ ─ ┐┌ ─ ┐┌ ─ ┐┌ ─ ┐
| 1 || 2 || 3 || 5 || 6 || 8 |
└ ─ ┘└ ─ ┘└ ─ ┘└ ─ ┘└ ─ ┘└ ─ ┘
## 주문할 테이블을 선택하세요.
1
[케이크] 1 - 가나슈 : 7000원
[케이크] 2 - 당근 케이크 : 6500원
[케이크] 3 - 아이스 박스 : 8000원
[케이크] 4 - 티라미수 : 5500원
[케이크] 5 - 치즈 케이크 : 7000원
[케이크] 6 - 얼그레이 케이크 : 7000원
[음료] 21 - 아메리카노 : 4500원
[음료] 22 - 에스프레소 : 4000원
[음료] 23 - 카푸치노 : 5000원
[음료] 24 - 그린티 라떼 : 6000원
  
## 메뉴 선택
1
  
## 수량 선택
1

## 메인화면
1 - 주문등록
2 - 결제하기
3 - 종료
  
## 기능 선택
2
  
## 테이블 목록
┌ ─ ┐┌ ─ ┐┌ ─ ┐┌ ─ ┐┌ ─ ┐┌ ─ ┐
| 1 || 2 || 3 || 5 || 6 || 8 |
└ $ ┘└ ─ ┘└ ─ ┘└ ─ ┘└ ─ ┘└ ─ ┘
  
## 테이블 선택
1
  
## 주문 내역
메뉴 수량 금액
가나슈 1 7000

## 1번 테이블의 결제를 진행합니다. 
## 신용 카드는 1번, 현금 결제는 2번 
1
  
## 최종 결제 금액
7000
  
## 메인화면
1 - 주문등록
2 - 결제하기
3 - 종료
```



### 제출시

기능 요구사항을 반드시 작성해주세요.

깃 커밋은 다음과 같은 템플릿을 이용해주세요.
```
<type>: <subject>
<BLANK LINE>
<body>
```
type: feat(기능추가),docs(문서작성),refactor(수정)  
subject: 해당 커밋의 제목  
body: 해당 커밋의 상세 내용  

PR 생성시 클래스 다이어그램을 설명과 함께 첨부해 주세요.

미션은 **step1/[자신의 이니셜]** 브랜치를 생성하고 진행해주세요.

**PR은 step1/[자신의 이니셜]에서 [자신의 이니셜] 브랜치로** 보내주세요.



# 2차

`front` 브랜치에 있는 `static` 디렉토리에 위치한 페이지를 이용합니다.

필요한 요구사항을 충족하기 위해 html, js 파일을 수정해야 합니다.

최소한의 도메인 변경으로 요구사항을 만족해주세요.

## 기능 요구사항

콘솔로 만든 POS 시스템을 WEB으로 옮깁니다.

- 메인
  - 결제가능한 테이블은 별도의 표시(`$$$$`)를 해야합니다.
  - 테이블을 클릭하여 주문, 결제 할 수 있습니다.

![image](https://user-images.githubusercontent.com/13347548/87826171-5d67b400-c8b3-11ea-9459-1036618206d7.png)

![image](https://user-images.githubusercontent.com/13347548/87826327-a91a5d80-c8b3-11ea-82ff-f8b1297f5ca3.png)

- 메뉴 관리 항목
  - 새로운 메뉴를 추가 할 수 있습니다.
  - 기존 메뉴를 삭제할 수 있습니다.

![image](https://user-images.githubusercontent.com/13347548/87826223-75d7ce80-c8b3-11ea-8005-2fbbe02274fb.png)

- 테이블 관리
  - 새로운 테이블을 추가 할 수 있습니다.
  - 기존 테이블을 삭제 할 수 있습니다.

![image](https://user-images.githubusercontent.com/13347548/87826266-88520800-c8b3-11ea-926d-12ee492ecf7f.png)

- 매출 관리
  - 지금까지 판매한 모든 매출 항목을 나열합니다.

![image](https://user-images.githubusercontent.com/13347548/87826286-92740680-c8b3-11ea-99b4-f4b3d53238ea.png)

### 요구 기술 스택

1. Spring boot
2. Spring data JPA
3. H2 database

### 제출시

**[자신의 이니셜]** 브랜치에 **`front` 브랜치를 먼저 `merge`해주세요.**

미션은 **step2/[자신의 이니셜]** 브랜치를 생성하고 진행해주세요.

**PR은 step2/[자신의 이니셜]에서 [자신의 이니셜] 브랜치로** 보내주세요.



