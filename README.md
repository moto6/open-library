# open-library


## Java Bean Validation
```text
javax.validation.constraints.
```
- 잘쓰는것만 씀
- 나머지는 여기 참고 : https://beanvalidation.org/2.0/spec/#builtinconstraints
```text
@NotBlank : null, "", " " 모두 불가
@NotEmpty : ""(빈칸)과 null 을 막음, " "화이트스페이스 허용
@NotNull : null만 아니면 됨. ""(빈거), " "(화이트스페이스) 문자는 허용
@Null : null만 허용함
@Size : 민맥스를 지정해줄수 있음
```

## 필요성
- 대한민국에 도서관이 x 개, 그중에서 전산화비율 몇프로
- 대/중/소 비중이 몇프로씩 (장서 권수 10만권/5만권)
- 소규모 도서관 >> 전산화 어려움
- 수기작성 등등 
- 전산화? 콜라스, >> 돈 많이듬
- 콜라시스넷, 책바다, 책이음 이런 서비스들도 있는데, 이거가지고 도서관관리시스템은 별도로 구축
- 니즈는 비슷하잖아요? 수기관리 >> 전산관리 , 관내상호대차서비스. 
- 발표 : 초창기고객은 특별대우해서 요구사항을 플랫폼에 잘 반영해드림, 나쁘게말하면 휘둘리는거고 B2B 숙명?


## 왜 DataTime 류 자료구조 안쓰고 UNIX-TIMESTAMP 썼나요?
- 보기 불편한거 많은데 도서관 특성상 시간순서로 정렬할일이 많은데
- 

## 엔티티 이름 관련
- 빌리다 관련한거 3가지 영단어가 있는데
  - lend 는 도서관이, borrow 는 이용자가, rent 는 안맞음
```text
borrow : 내가 빌리는 경우, 댓가를 지불하지 않고 빌려쓸때 사용하는 경우
  > can i borrow a book, Can I borrow your umbrella?
rent : 임차료, 돈내고 대여하는 경우
  > rent car
lend : 내가 빌려주는 경우. 증여하거나, 타인에게 나중에 나에게 돌려줘야 하는 것을 사용하도록 허락하다
  > lent the car to a friend, She very kindly lent me her bicycle
```

## SQL 컬럼명 규칙
```text
- ###_ID : 프라이머리키이거나, 검색조건, 항상 유니크함, 인덱스는 없을수있음
- IS_### : 불리언저장
- ###_TIMESTAMP : UNIX 타임스테프
- ###_DATETIME : 로컬데이트타임 저장
- ###_COUNT : 횟수세는거임
- 

```
