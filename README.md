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
```

## 이외

- 회원가입/로그인 관련해서 너무 허술한거 같은데? 
  : 맞습니다. 다른 데이터원장/계정시스템에 붙이는걸 상정하고 만들었습니다.
  : 일반적으로 작은 도서관에서는 회원DB 를 직접 구축하지 않고 기초자치단체(노원구, 서초구, 강남구 등등..)의 회원데이터를 받아와 쓰기때문입니다
- 로그인방법(사용자)
  ```shell
  
  ```
- 로그인방법(관리자)
  ```shell
  
  ```

## 로컬환경 실행 인프라셋팅
```shell
#DB 실행
docker-compose -f mysql-docker-compose.yml up -d

#DB 상태확인
docker-compose ps

#ES 
docker-compose -f single-es-docker-compose.yml up -d 

```

- 프로퍼티 체크
```properties
spring.datasource.url=jdbc:mysql://{IP 혹은 FQDN}:{PORT}/{DB 이름}?useSSL=false
spring.datasource.username=root
spring.datasource.password={패스워드}
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect
```


## 컨피그파일

- 과거버전
```properties
spring:
  profiles:
    active: test
```
- 2.4+ 에서 적용되는 버전
```properties
spring:
  config:
    activate:
      on-profile: test
```
- 배포빌드할때 `--spring.profiles.active=prod`
- 참고문서
  - https://spring.io/blog/2020/08/14/config-file-processing-in-spring-boot-2-4
  - https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-Config-Data-Migration-Guide#profile-groups

## DDD 관점
- DDD 너무 어려운데.. 그냥 이렇게 정의하고 싶습니다
  : 명사들(도메인) 사이의 동사(메서드)


## ES 붙이기
- https://www.elastic.co/guide/en/elasticsearch/client/java-api-client/current/installation.html

## mysql 풀텍스트서치

- 풀텍스트 인덱스 옵션

```text
SHOW VARIABLES LIKE 'innodb_ft_min_token_size'; -- return:2
SHOW VARIABLES LIKE 'ft_min_word_len';  -- return:2
```

- innodb_ft_min_token_size
  : InnoDB 스토리지 엔진에 특정한 설정 
  : 전체 텍스트 검색을 위해 인덱싱될 단어의 최소 크기를 제어하는 파라미터, 이 값보다 짧은 단어는 인덱싱되지 않음

- ft_min_word_len 
  : MySQL의 전체 텍스트 인덱싱을 위한 일반 설정
  : 전체 텍스트 검색을 위해 인덱싱될 단어의 최소 길이를 제어하는 파라미터, 이 값보다 짧은 단어는 인덱싱되지 않음

- 결론 : InnoDB Only vs MySQL specific  의 차이
  : 두개의 파라미터가 서로 다르면 어떻게 동작하는지, 우선순위가 있는건지는 공식문서에서도 찾을수 없었습니다
  : 그래서 결론은 안전하게 둘다 2 로 맞추자! 라고 결론내렸습니다


### mysql 풀텍스트서치 쿼리문 
: (검색키워드가 "반도체" 일때)

- 풀텍스트서치 LIKE 쿼리 

```sql
SELECT * from BOOK_MASTER where TITLE LIKE '%반도체%';

-- 검증
EXPLAIN SELECT * from BOOK_MASTER where TITLE LIKE '%반도체%';
```

- 풀텍스트 인덱스를 타는 쿼리 

```SQL
SELECT * FROM BOOK_MASTER where match(TITLE) AGAINST('*반도체*' IN BOOLEAN MODE);
EXPLAIN SELECT * FROM BOOK_MASTER where match(TITLE) AGAINST('*반도체*' IN BOOLEAN MODE);
```





## 트러블슈팅

### SQLException  Connection is read-only 문제

- 증상(에러로그)

  ```log
  2023-01-29 10:36:51.192  WARN 43832 --- [nio-8080-exec-1] o.h.engine.jdbc.spi.SqlExceptionHelper   : SQL Error: 0, SQLState: S1009
  2023-01-29 10:36:51.192 ERROR 43832 --- [nio-8080-exec-1] o.h.engine.jdbc.spi.SqlExceptionHelper   : Connection is read-only. Queries leading to data modification are not allowed
  2023-01-29 10:36:51.212 ERROR 43832 --- [nio-8080-exec-1] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is org.springframework.orm.jpa.JpaSystemException: could not execute statement; nested exception is org.hibernate.exception.GenericJDBCException: could not execute statement] with root cause
  
  java.sql.SQLException: Connection is read-only. Queries leading to data modification are not allowed
      at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:129) ~[mysql-connector-j-8.0.31.jar:8.0.31]
      at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:97) ~[mysql-connector-j-8.0.31.jar:8.0.31]
      at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:89) ~[mysql-connector-j-8.0.31.jar:8.0.31]
      at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:63) ~[mysql-connector-j-8.0.31.jar:8.0.31]
      ....
      at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
      at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
      at org.springframework.orm.jpa.SharedEntityManagerCreator$SharedEntityManagerInvocationHandler.invoke(SharedEntityManagerCreator.java:311) ~[spring-orm-5.3.24.jar:5.3.24]
      at com.sun.proxy.$Proxy124.persist(Unknown Source) ~[na:na]
  ```
- 해결책
  - before
  ```java
  @Transactional(isolation = Isolation.READ_UNCOMMITTED, readOnly = true) // readOnly를 false 로 수정한다
  public List<BookMaster> searchByTitleV0Like(String keyword) {
      return bookMasterRepository.findAllByTitleLike("%"+ keyword+ "%");
  }
  ```
- 원인
  : AOP 로 로깅하는 부분이 있는데, 로깅자체도 트랜잭션의 범위에 포함되고 save 동작이기 때문에 해당 에러가 발생.
  : 즉, 메인로직만 보면 insert, update 가 없지만 세컨더리 로직(AOP 로 돌아가는) 에서 insert 쿼리가 날라가기때문에 발생한 문제 


## 데이터셋 이야기

### 데이터셋 구하는 법 공유

- 데이터셋만 추가하지 말고 물고기잡는법도 적어놓고 싶습니다..
- 도서관 관련 데이터셋은 여기 링크에서 구했습니다 : https://www.data4library.kr
- 이게 도서관 규모별로 장서 수량에 차이가 좀 있습니다. 수도권 자치단체(도봉구, 분당구..) 중앙도서관급은 평균적으로 20먄권(약 30MB) 장서를 보유
- 근데 이게 생각해보면 자치단체 중앙도서관별 장서 목록은 많은 부분이 겹칠꺼라고 예상됩니다. 따라서 의미있는 그러니까 여러가지 장서데이터를 얻기 위해서는 작은 도서관 여러개보다는 대형도서관 한두곳의 장서정보를 추가하는편이 적절하다고 생각합니다.
- 대형도서관들을 검색하는 키워드는 아래와 같습니다
  - : `중앙, 국립, 시립, 광역시`
- 이 repository 의 resource/dataset 디텍토리에도 해당 키워드로 검색해서 장서수 20만권 이상의 도서관들의 데이터를 수잡하였습니 

### 데이터셋 관련 트러블슈팅1 : 깃허브 최대파일용량
- 깃허브로 코드만 공유하면 이런일은 발생하지 않지만... 데이터셋을 공유하면 아래같은 문제가 발생하는데요
```text
remote: Resolving deltas: 100% (6/6), completed with 5 local objects.
remote: warning: See http://git.io/iEPt8g for more information.
remote: warning: File src/main/resources/dataset/안산시 중앙도서관 장서 대출목록 (2022년 12월).csv is 50.32 MB; this is larger than GitHub's recommended maximum file size of 50.00 MB
remote: warning: GH001: Large files detected. You may want to try Git Large File Storage - https://git-lfs.github.com.
```
- 50MB 이상의 파일은 업로드가 불가능하다는것..
  - 10개 정도 데이터셋이 문제가 되는데요 3개만 예를들면 
  ```text
  -rw-r--r--@ 1 dong  staff    89M  1 29 20:21 NationalSejong-202212.csv
  -rw-r--r--@ 1 dong  staff    50M  1 29 20:20 Ansan-202212.csv
  -rw-r--r--@ 1 dong  staff    69M  1 29 20:19 Gyeonggi-202212.csv
  -rw-r--r--@ 1 dong  staff    54M  1 29 20:19 Wonju-202212.csv
  ```
  - 여기서 깃허브 파일업로드사이즈 제한인 50메가를 아슬하게 넘게는 `Ansan-202212.csv` 은 trim으로, Gyeonggi, NationalSejong 두군대는 2개의 파일로 쪼개서 50메가 제한을 회피하였습니다.

#### 데이터셋 관련 트러블슈팅1 : 깃허브 최대파일용량 > 손으로 쪼개는것도 보통일이 아니다.

- 처음 계획은 54MB 정도의 파일들은 text editor 로 49.9MB 로 trim 한다음, 나머지는 다른 파일에다가 따로 모아서 저장하려고 했는데요 
- jaturi.csv 라는 이름으로 저장해서 파일 두개정도 쪼개보니까 이런 데이터셋을 텍스트에디터로 쪼개는일도 사람이 할짓이 못된다는걸 알아차렸습니다..
  - 드래그만 몇분째 해야하는 이 지독한 노동.. 
- 이 문제를 해결하기 위해 찾아보니 `split` 이라는 명령어가 있었습니다.. (유닉스 만세..)
```bash
split -b 30m data.csv data-split_ #30MB 단위로 파일을 분할하는 명령어 
```
- 위 명령어를 가지고 용량제한에 걸리는 파일들을 쪼개서 데이터셋을 올렸습니다
- 이제 파일수가 많아졌으니까.. 기존에 copy&paste 로 datainsert 를 하지말고, 파일명으로 데이터셋업을 해야겠죠..? 
```bash
ls -l | awk '{ print $9 }' #맥에서 현 위치에서 파일명만 출력하는 명령어
```


### 데이터셋 관련 트러블슈팅2 : 파일인코딩
- [도서관 정보나루](https://www.data4library.kr) 에서 csv 파일을 다운로드 받으면 기본 ecu-kr 인코딩이라 그냥 열면 한글데이터가 깨져서 보입니다 

```text
��ȣ,������,����,���ǻ�,����⵵,ISBN,��Ʈ ISBN,�ΰ���ȣ,��,�����з���ȣ,�����Ǽ�,����Ǽ�,�������,
"1","We need to talk  how to have conversations that matter","Headlee, Celeste","Harperwave","2017","9780062669018","","","","802.56","1","0","2022-12-30",
"6","��Ƽ �ȼ�, ���� ��� ��� ��ʴϱ�? [��Ȱ�ں�]","������","�Ѱܷ���","2022","9791160408577","","","","813.6","1","0","2022-12-30",
"7","��� ������ ���� [��Ȱ�ں�] : ���ʿ� �Ҽ���","���ʿ�","�Ѱܷ���","2022","9791160406962","","","","813.6","1","0","2022-12-30",
```

- 그래서 데이터셋들의 인코딩을 모두 UTF-8로 변환해주는 전처리작업을 진행해두었습니다