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