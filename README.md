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