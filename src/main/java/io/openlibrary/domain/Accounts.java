package io.openlibrary.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Base64;

@NoArgsConstructor
@Getter
@Table(name = "ACCOUNTS", indexes = {
        @Index(name="IDX_IAM_COMDE",columnList = "IAM_COMDE",unique = true)
})
@Entity
public class Accounts {
    @Id
    @GeneratedValue
    @Column(name = "ACCOUNT_ID")
    private Long accountId;
    @Column(name = "ACCOUNT_BARCODE")
    private String accountBarcode;
    @Column(name = "IAM_COMDE")
    private String iamCode;
    @Column
    private String note;

    public Accounts(String iamCode) {
        this.iamCode = iamCode;
    }

    public String issueAccessToken() {
        return Base64.getEncoder().encodeToString(this.iamCode.getBytes());
    }

    // 정보가 너무 없는거 아냐? 생각할수 있겠지만 어차피 진짜 써비스 돌릴려면 개인을 특정할수 있는 모든 정보는 따로관리해야함.
    // 그러니까 "서비스제공을 위한 계정" 과 "계정 주인의 개인정보" 분리 하는 연습 미리해두는걸 추천
    // 나중에 iamCode 가지고 개인정보를 다른시스템/다른계정계/내 서비스지만 별도의 DB인스턴스 에서 긁어와야함
}
